package main.java.ClientServer;


import java.io.IOException;
import java.net.*;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSide extends Thread {
    private Socket socket;
    private ServerSocket server;
    private static final int MAX_CLIENTS_ON_SERVER = 3;
    private int port;
    private Thread runServer;
    private DatagramSocket datagramSocket;
    private ExecutorService executorServer;
    private LinkedList<ClientSide> clients;
    private boolean running = false;

    public ServerSide(int port) {
        this.port = port;
        try {
            server = new ServerSocket(port);
            datagramSocket = new DatagramSocket(port);
            executorServer = Executors.newFixedThreadPool(MAX_CLIENTS_ON_SERVER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        runServer = new Thread(new Runnable() {
            @Override
            public void run() {
                running = true;
                waitingForData();
                activateServer();
            }
        });
    }

    private void activateServer() {
        runServer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = server.accept();
                    executorServer.execute(new ClientSide());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "runServer");
        runServer.start();
    }

    public void sendMessage(byte[] data) {

    }

    private void waitingForData() {
        while (running) {
            byte[] data = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
            try {
                datagramSocket.receive(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
