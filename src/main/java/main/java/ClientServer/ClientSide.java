package main.java.ClientServer;


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


    public ClientSide(String name, int port, String hostCompanion) {
        try {
            clientSocket = new Socket("localhost", port);
            address = InetAddress.getByName(hostCompanion);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.port = port;
        start();
    }

    public void sendTextMessage(byte[] message) {
        packet = new DatagramPacket(message, message.length, address, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}