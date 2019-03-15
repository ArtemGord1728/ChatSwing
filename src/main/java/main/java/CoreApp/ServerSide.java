package main.java.CoreApp;


import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSide
{
    private Socket socket;
    private ServerSocket server;
    private static final int MAX_CLIENTS_ON_SERVER = 3;
    private int port;
    private Thread activate, runServer, receiveData;
    private DatagramSocket datagramSocket;
    private ExecutorService executorServer;
    private List<ClientSide> clients;
    private boolean running = false;

    public ServerSide(int port) {
        this.port = port;
        try {
            server = new ServerSocket(port);
            datagramSocket = new DatagramSocket(port);
            executorServer = Executors.newFixedThreadPool(MAX_CLIENTS_ON_SERVER);
            System.out.println("Server started on port: " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        runServer = new Thread(new Runnable() {
            @Override
            public void run() {
                running = true;
                activateServer();
                waitingForData();
            }
        });
    }

    private void activateServer() {
        activate = new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    try {
                        socket = server.accept();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "runServer");
        runServer.start();
    }

   public void sendMessage(byte[] data) {

    }

    private void waitingForData() {
        receiveData = new Thread(new Runnable() {
            @Override
            public void run() {
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
        });
    }
}
