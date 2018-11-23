package ServerClientsFunctionality;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public interface ServerFunctionality
{
    boolean openConnection(String address);
    void receive();
}
