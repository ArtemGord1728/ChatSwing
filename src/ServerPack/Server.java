package ServerPack;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread
{
    ServerSocket serverSocket;
    Socket client;
    int countClient = 0;

    @Override
    public void run() {
        isAddingToServer();
    }

    public boolean isAddingToServer()
    {
        try {
            serverSocket = new ServerSocket(8493);
            client = serverSocket.accept();
            while (!client.isClosed())
            {
                if(client.isConnected())
                {
                    System.out.println("Client connect to server!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}