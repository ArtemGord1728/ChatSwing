package ServerPack;

import InterfacePack.Window;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    ServerSocket serverSocket;
    private Socket client;
    DataInputStream is;
    DataOutputStream os;
    public static boolean isEntryToServer;

    @Override
    public void run() {
        runServer(Window.textArea);
    }



    public void runServer(JTextArea jTextArea) {
        try {
            serverSocket = new ServerSocket(8493);
            client = serverSocket.accept();
            is = new DataInputStream(client.getInputStream());
            os = new DataOutputStream(client.getOutputStream());
            os.flush();

            String message = is.readUTF();
            jTextArea.setText(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}