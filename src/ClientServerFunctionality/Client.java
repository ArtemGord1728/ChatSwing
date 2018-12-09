package ClientPack;

import java.net.*;

public class Client {
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
    }


}