package ServerPack;

import ServerClientsFunctionality.ServerFunctionality;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Date;

public class Server implements ServerFunctionality
{
    DatagramPacket packetReceive;
    DatagramSocket socket;
    int port;
    Thread receiveMessages;

    public Server(int port) {
        this.port = port;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receive() {
        receiveMessages = new Thread(() -> {
            byte[] mess = new byte[1024];
            packetReceive = new DatagramPacket(mess, mess.length);
            try {
                socket.receive(packetReceive);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void gettingMessage() {

    }

    @Override
    public void structMessage(Date date, String message) {
        System.out.print(date.getTime() + " " + message);
    }
}