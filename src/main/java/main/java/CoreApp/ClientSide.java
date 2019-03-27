package main.java.CoreApp;


import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSide extends Thread {
    private Socket clientSocket;
    private InetAddress address;
    private int port;
    private DatagramPacket packet;
    private DatagramSocket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public ClientSide(){}


    public ClientSide(String name, int port) {
        try {
            clientSocket = new Socket("localhost", port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.port = port;
        start();
    }

    public String sendTextMessage(byte[] message) {
        packet = new DatagramPacket(message, message.length, address, port);
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
                packet = new DatagramPacket(message, message.length, address, port);
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