package main.java.core;


import java.io.*;
import java.net.Socket;

public class ClientSide implements Runnable{
    private int port;
    private Thread sendMsg, runClient;
    private BufferedReader br;
	private Socket socket;
	private DataInputStream inStream;
	private DataOutputStream outStream;

    public ClientSide(int port) {
    	this.port = port;
    	try {
			socket = new Socket("localhost", port);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	runClient = new Thread(this, "ClientSide");
    	runClient.start();
	}
    
    private void init() {
    	try {
			inStream = new DataInputStream(socket.getInputStream());
			outStream = new DataOutputStream(socket.getOutputStream());
			br = new BufferedReader(new InputStreamReader(System.in));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void send(String msg) {
    	sendMsg = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while(!socket.isOutputShutdown()) {
						outStream.writeUTF(msg);
						outStream.flush();
						System.out.println("Client send message - " + msg);
						Thread.sleep(1000);
					}
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
    	sendMsg.start();
    }
    
	@Override
	public void run() {
		init();
	}
	
	private void stop() {
		try {
			inStream.close();
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}