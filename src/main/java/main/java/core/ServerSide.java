package main.java.core;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerSide
{
    private static final int MAX_CLIENTS_ON_SERVER = 3;
    private int port;
    private String host;
    private Thread runServer, receiveData;
    private Runnable runnable;
    private DatagramSocket socket;
    private DatagramPacket packet;
    private ExecutorService executorServer;
    private List<User> clients;
    private boolean running = false;
	private UUID authKey;
    
    public ServerSide(int port, String host) {
        this.port = port;
        this.host = host;
        init();
        System.out.println(new Date() + " : " + "Server started on port - " + port);
        System.out.println("Waiting for user...");
        
        runService();
    }
    
    private void init() {
    	try {
    		clients = new ArrayList<User>();
    		socket = new DatagramSocket(port);
	        executorServer = Executors.newFixedThreadPool(MAX_CLIENTS_ON_SERVER);
		} catch (SocketException e) {
			e.printStackTrace();
		}
    }
    
    private void runService() {
    	runServer = new Thread(() -> run());
        runServer.start();
        
        executorServer.execute(() -> new ClientSide(port, host));
        
        receiveData = new Thread(() -> receive());
        receiveData.start();
    }

    
    private void run() {
    	running = true;
    	receive();
    }
    
    private void receive() {
    	runnable = new Runnable() {
			
			@Override
			public void run() {
				while (running) {
		            byte[] data = new byte[1024];
		            packet = new DatagramPacket(data, data.length);
		            try {
		            	socket.receive(packet);
		                clients.add(new User(port, host, authKey));
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
				processs(packet);
			}
		};
    }
    
    private void processs(DatagramPacket packet) {
    	
    }
    
    private void disconnect(UUID uuid, boolean status) {
    	
    }
    
    private void quit() {
    	for (int i = 0; i < clients.size(); i++) {
			disconnect(clients.get(i).getAuthKey(), true);
		}
    	socket.close();
    }
}
