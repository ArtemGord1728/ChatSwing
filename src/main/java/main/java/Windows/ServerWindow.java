package main.java.Windows;

import main.java.ClientServer.ServerSide;
import main.java.ClientServer.User;
import main.java.InterfacePack.Layer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class ServerWindow extends Canvas implements Layer {
    private BufferStrategy buffer;
    private JFrame frame;
    private Graphics graphics;
    private int client;
    public static JTextArea textArea;
    public ServerSide serverSide;
    private ArrayList<User> userList;


    public ServerWindow(String name, int width, int height, int port) {
        setPreferredSize(new Dimension(width, height));
        initWindow(name);
        renderBuffer();
        serverSide = new ServerSide(port, AuthorizationWindow.portStr);
    }

    public void showLabels() {
        JLabel connectionClientsAmount = new JLabel("Connection clients:  " + client);
        connectionClientsAmount.setBounds(360, -80, 180, 200);
        frame.add(connectionClientsAmount);

        JLabel messagesFromClients = new JLabel("Messages");
        messagesFromClients.setBounds(JLabel.LEFT, -80, 180, 200);
        frame.add(messagesFromClients);
    }

    public void showTextLayer() {
        textArea = new JTextArea();
        textArea.setVisible(true);
        textArea.setLineWrap(true);
        textArea.setEnabled(false);
        textArea.setWrapStyleWord(true);
        textArea.setBounds(10, 40, getWidth(), getHeight());
        textArea.setSize(new Dimension(250, 350));
        frame.add(textArea);
    }

    public void renderBuffer() {
        if (buffer == null)
            createBufferStrategy(3);

        graphics = getGraphics();
        buffer = getBufferStrategy();
        buffer.getDrawGraphics();
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(Color.WHITE);
        graphics.dispose();
        buffer.show();
    }

    public void initWindow(String name) {
        frame = new JFrame(name);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        showTextLayer();
        showLabels();
        frame.add(this);
        frame.pack();
    }

    @Override
    public void showButton() {

    }
}