package InterfacePack;

import ClientServer.Server;
import InterfacePack.InterfaceElements.Layer;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window extends Canvas implements Layer
{
    private BufferStrategy buffer;
    private JFrame frame;
    private Graphics graphics;
    private boolean isConnect;
    private int client = 0;
    public static JTextArea textArea;
    public Server server;


    public Window(String name, int width, int height, int port)
    {
        setPreferredSize(new Dimension(width, height));
        initWindow(name);
        renderBuffer();
        server = new Server(port);
        isConnect = server.connect();
    }

    @Override
    public void showLabels() {
        JLabel connectionClientsAmount = new JLabel("Connection clients:  " + client);
        connectionClientsAmount.setBounds(360, -80, 180, 200);
        frame.add(connectionClientsAmount);

        JLabel messagesFromClients = new JLabel("Messages" );
        messagesFromClients.setBounds(JLabel.LEFT, -80, 180, 200);
        frame.add(messagesFromClients);
    }

    @Override
    public void showTextLayer() {
        textArea = new JTextArea();
        textArea.setVisible(true);
        textArea.setLineWrap(true);
        textArea.setEnabled(true);
        textArea.setWrapStyleWord(true);
        textArea.setBounds(10, 40, getWidth(), getHeight());
        textArea.setSize(new Dimension(150, 50));
        frame.add(textArea);
    }


    private void renderBuffer() {
        if(buffer == null)
            createBufferStrategy(3);

        graphics = getGraphics();
        buffer = getBufferStrategy();
        buffer.getDrawGraphics();
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(Color.WHITE);
        graphics.dispose();
        buffer.show();
    }

    private void initWindow(String name) {
        frame = new JFrame(name);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        showTextLayer();
        showLabels();
        frame.add(this);
        frame.pack();
    }
}