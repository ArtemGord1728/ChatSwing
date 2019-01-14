package main.java.ClientServer;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

public class ClientSide {
    private Socket clientSocket;
    private int port;
    private DatagramPacket packet;
    private DatagramSocket socket;

    public ClientSide(int port) {
        clientSocket = new Socket();
        this.port = port;
    }

    public void sendMessage(byte[] message) {

    }
}