package main.java.core;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerSide
{
    private static final int MAX_CLIENTS_ON_SERVER = 3;
    private int port;
    private String host;
    private Thread runServer;
    private Runnable waiting;
    private DatagramSocket socket;
    private DatagramPacket packet;
    private ExecutorService executorServer;
    private List<User> clients;
    private boolean running = false;
	private UUID authKey;
    
    public ServerSide(int port, String host) {
        this.port = port;
        this.host = host;
        System.out.println("Server started on port - " + port);
        System.out.println("Waiting for user...");        
        init();
    }
    
    private void init() {
    	try {
    		clients = new ArrayList<User>();
    		socket = new DatagramSocket(port);
	        executorServer = Executors.newFixedThreadPool(MAX_CLIENTS_ON_SERVER);
	        
	        runServer = new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(running) {
						receive();
					}
				}
			});
	                
	        executorServer.execute(() -> new ClientSide(port, host));
	        
		} catch (SocketException e) {
			e.printStackTrace();
		}
    }
  
    
    private void receive() {
    	waiting = new Thread(new Runnable() {
			@Override
			public void run() {
				while (running) {
		            byte[] data = new byte[1024];
		            packet = new DatagramPacket(data, data.length);
		            try {
		            	socket.receive(packet);
		            } 
		            catch (IOException e) {
		                e.printStackTrace();
		            }
		            clients.add(new User(port, host, authKey));
	            	System.out.println(clients.get(0).getAuthKey() + ":" + clients.get(0).getPort());
		        }
				processs(packet);
			}
		});
		waiting.run();
    }
    
    private void processs(DatagramPacket packet) {
    	//String data = new String(packet.getData());
    }
    
    private void disconnect(UUID clientId, boolean status) {
    	User user;
    	for (int i = 0; i < clients.size(); i++) {
			if(clients.get(i).getAuthKey() == clientId) {
				user = clients.get(i);
				clients.remove(user);
			}
		}
    	running = false;
    }
    
    private void quit() {
    	for (int i = 0; i < clients.size(); i++) {
			disconnect(clients.get(i).getAuthKey(), true);
		}
    	running = false;
    	socket.close();
    }
}
