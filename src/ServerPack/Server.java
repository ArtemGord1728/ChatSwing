package ServerPack;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    static ServerSocket serverSocket;
    private static Socket client;
    int clients;
    public static boolean isEntryToServer;

    @Override
    public void run() {
        isAddingToServer();
    }



    public static boolean isAddingToServer() {
        try {
            serverSocket = new ServerSocket(8493);
            client = serverSocket.accept();
            if(client.isConnected())
                isEntryToServer = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isEntryToServer;
    }
}