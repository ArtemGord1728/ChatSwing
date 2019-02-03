package main.java.ClientServer;


import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSide
{
    private Socket socket;
    private ServerSocket server;
    private InetAddress ip;
    private static final int MAX_CLIENTS_ON_SERVER = 10;
    private int port;
    private Thread runServer;
    private DatagramSocket datagramSocket;
    private ExecutorService executorServer;
    private DatagramPacket datagramPacket;

    public ServerSide(int port, @NotNull String host) {
        this.port = port;
        try {
            server = new ServerSocket(port);
            ip = InetAddress.getByName(host);
            datagramSocket = new DatagramSocket(port);
            executorServer = Executors.newFixedThreadPool(MAX_CLIENTS_ON_SERVER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        activateServer();
    }

    private void activateServer() {
        runServer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = server.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "runServer");
        runServer.start();
    }

    private void waitingForData(byte[] data){
        datagramPacket = new DatagramPacket(data, data.length);
        try {
            datagramSocket.receive(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
