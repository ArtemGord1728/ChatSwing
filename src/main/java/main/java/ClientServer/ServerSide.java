package main.java.ClientServer;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide {
    private Socket socket;
    private ServerSocket server;
    private InetAddress ip;
    private int port;
    private Thread runServer;

    public ServerSide(int port, @NotNull String host) {
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
        }, "runServer");
        runServer.start();
    }
}
