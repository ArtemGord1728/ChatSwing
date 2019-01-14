package main.java.ClientServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide
{
    private ServerSocket server;
    private InetAddress ip;
    private int port;
    private Socket socket;
    private Thread runServer;

    public ServerSide(int port, String host) {
        this.port = port;
        try {
            server = new ServerSocket(port);
            ip = InetAddress.getByName(host);
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
        });
    }
}
