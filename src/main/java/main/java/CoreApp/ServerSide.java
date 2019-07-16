package main.java.CoreApp;


import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import main.java.LogPack.*;

public class ServerSide
{
    private static final int MAX_CLIENTS_ON_SERVER = 3;
    private int port;
    private Thread runServer, receiveData;
    private DatagramSocket datagramSocket;
    private ExecutorService executorServer;
    private List<ClientSide> clients;
    private boolean running = false;

    public ServerSide(int port) {
        this.port = port;
        init();        
        System.out.println(new Date() + " : " + "Server started on port: " + port);
        
        runAndReceive();
    }
    

    private void init() {
    	try {
        	clients = new ArrayList<ClientSide>();
			datagramSocket = new DatagramSocket(port);
	        executorServer = Executors.newFixedThreadPool(MAX_CLIENTS_ON_SERVER);
	        
		} catch (SocketException e) {
			e.printStackTrace();
		}
    }
    

    public void sendMessage(byte[] data) {

    }

    private void waitingForData() {
        
    }
    
    private void run() {
    	running = true;
        waitingForData();
    }
    
    private void receive() {
    	while (running) {
            byte[] data = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
            try {
                datagramSocket.receive(datagramPacket);
                System.out.println(new String(datagramPacket.getData()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void runAndReceive() {
    	runServer = new Thread(() -> run());
        runServer.start();
        
        receiveData = new Thread(() -> receive());
        receiveData.start();
    }
}
