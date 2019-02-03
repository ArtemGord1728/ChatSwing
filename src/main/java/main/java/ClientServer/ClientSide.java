package main.java.ClientServer;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
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

    public ClientSide(int port) {
        try {
            clientSocket = new Socket("localhost", port);
            address = InetAddress.getLocalHost();
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

    public void sendFileMessage(byte[] message, File file){

    }
}