package main.java.ClientServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private ServerSocket server;
    private InetAddress ip;
    private int port;
    private Socket socket;
    private Thread runServer;

    public Server(int port, String host) {
        this.port = port;
        try {
            server = new ServerSocket(port);
            ip = InetAddress.getByName(host);
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            activateServer();
        }
    }

    private void activateServer() {
        runServer = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    public boolean isClientConnection() {
        if(socket.isConnected())
            return true;
        else
            return false;
    }
}