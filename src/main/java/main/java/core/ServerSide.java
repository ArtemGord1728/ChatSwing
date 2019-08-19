package main.java.core;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ServerSide implements Runnable
{
	private ServerSocket serverSocket;
	private Socket client;
	private DataInputStream inputStream;	
	private DataOutputStream outputStream;
    private int port;
    private Thread runServer, receiveData;
    private List<User> clients = new ArrayList<User>();
    private boolean running = false;
    
    public ServerSide(int port) {
        this.port = port;
        try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
        runServer = new Thread(this, "ServerSide");
        runServer.start();
    }
    
    private void init() {
    	try {
    		client = serverSocket.accept();
			inputStream = new DataInputStream(client.getInputStream());
			outputStream = new DataOutputStream(client.getOutputStream());
			
			if(client.isConnected())
				System.out.println("Client connected with port - " + this.port);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @Override
	public void run() {
		running = true;
		init();
		receive();
	}
    
    private void receive() {
    	receiveData = new Thread(new Runnable() {
			@Override
			public void run() {
					try {
						String dataFromClient = inputStream.readUTF();
						System.out.println("Client: " + dataFromClient);
						
						outputStream.writeUTF("Server reply " + dataFromClient);
						
						if(dataFromClient.equalsIgnoreCase("quit")) {
							System.out.println("Server will be close");
							stopServer();	
							running = false;
							return;
						}
						
						outputStream.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		});
    	receiveData.start();
    }
    
    private void stopServer() {
    	try {
    		serverSocket.close();
    		client.close();
			inputStream.close();
			outputStream.close();
			clients.clear();
			port = 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
