package main.java.CoreApp;


import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientSide {
    private InetAddress ip;
    private int port;
    private String name, host;
    private DatagramPacket packet;
    private DatagramSocket socket;


    public ClientSide(String name, int port, String host) {
    	this.port = port;
    	this.name = name;
    	this.host = host;
    	
    	connectToIpAndSocket();
    }
    
    public ClientSide(int port, String host) {
    	this.port = port;
    	this.host = host;
    	
		connectToIpAndSocket();
	}
    
    private void connectToIpAndSocket() {
    	try {
    		socket = new DatagramSocket();
        	ip = InetAddress.getByName(host);
        	socket = new DatagramSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
    public void sendTextMessage(byte[] message) {
        packet = new DatagramPacket(message, message.length, ip, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFileMessage(byte[] message, String fileName) {
        try {
            FileInputStream fileStream = new FileInputStream(fileName);
            while (fileStream.read() != -1) {
                packet = new DatagramPacket(message, message.length, ip, port);
                socket.send(packet);
            }
            fileStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getHost() {
		return host;
	}
    
    public String getName() {
		return name;
	}
    
    public int getPort() {
		return port;
	}
}