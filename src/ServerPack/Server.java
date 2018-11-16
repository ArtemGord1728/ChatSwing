package ServerPack;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    ServerSocket serverSocket;
    Socket client;
    int countClient = 0;


    public void addClient(int countClient)
    {
        try {
            serverSocket = new ServerSocket(8493);
            client = serverSocket.accept();
            while (!client.isClosed())
            {
                if(client.isConnected())
                {
                    this.countClient = countClient;
                    countClient++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}