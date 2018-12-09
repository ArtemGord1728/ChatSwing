package ServerPack;

import ClientPack.User;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Server
{
    DatagramPacket packetReceive;
    DatagramSocket socket;
    int port;
    List<User> connectUser;
    Thread receiveMessages;

    public Server(int port) {
        this.port = port;
        connectUser = new ArrayList<>();
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}