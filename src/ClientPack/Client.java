package ClientPack;

import ServerClientsFunctionality.ClientFunctionality;

import java.io.IOException;
import java.net.*;

public class Client implements ClientFunctionality {
    private DatagramSocket socket;
    private String address, name;
    private InetAddress ipAddress = null;
    private Thread send;
    private int port;
    private boolean isConnect;

    public Client(String address, String name, int port) {
        this.address = address;
        this.name = name;
        this.port = port;

        isConnect = openConnection(address);

        if(!isConnect)
            return;
    }

    @Override
    public boolean openConnection(String host) {
        try {
            socket = new DatagramSocket();
            ipAddress = InetAddress.getByName(host);
        } catch (SocketException e) {
            e.printStackTrace();
            return false;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void sendMessage(byte[] message) {
        send = new Thread(() -> {
            DatagramPacket packet = new DatagramPacket(message, message.length, ipAddress, port);
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}