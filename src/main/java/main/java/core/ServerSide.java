package main.java.core;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ServerSide
{
	private ServerSocket serverSocket;
	private Socket client;
	private DataInputStream inputStream;	
	private DataOutputStream outputStream;
    private int port;
    private List<User> clients = new ArrayList<User>();
    private boolean running = false;
    
    public ServerSide(int port) {
        this.port = port;
        try {
			serverSocket = new ServerSocket(port);
			 init();
		     receive();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void init() {
    	try {
    		client = serverSocket.accept();
			inputStream = new DataInputStream(client.getInputStream());
			outputStream = new DataOutputStream(client.getOutputStream());
			running = true;
			if(client.isConnected())
				System.out.println("Client connected with port - " + this.port);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    private void receive() {
			try {
				while(inputStream.available() > 0) {
					String dataFromClient = inputStream.readUTF();
					System.out.println("Client: " + dataFromClient);
					outputStream.writeUTF("Server reply " + dataFromClient);
					outputStream.flush();
					
					if(dataFromClient.equalsIgnoreCase("quit") && running) {
						System.out.println("Server will be close");
						stopServer();
						running = false;
						return;
					}
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
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
			e.printStackTrace();
		}
    }
    
}
