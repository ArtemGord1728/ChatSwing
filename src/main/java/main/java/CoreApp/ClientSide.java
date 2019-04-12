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
        try {
        	ip = InetAddress.getByName(host);
        	socket = new DatagramSocket(port);
        	
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    public String sendTextMessage(byte[] message) {
        packet = new DatagramPacket(message, message.length, ip, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(packet.getData());
    }

    public void sendFileMessage(byte[] message, String fileName){
        try {
            FileInputStream fileStream = new FileInputStream(fileName);
            while (fileStream.read() != -1){
                packet = new DatagramPacket(message, message.length, ip, port);
                socket.send(packet);
            }
            fileStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMessage(){
        return new String(packet.getData());
    }
    
}