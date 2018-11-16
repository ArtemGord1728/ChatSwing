package InterfacePack;

import InterfacePack.InterfaceElements.Labels;
import ServerPack.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;

public class Window extends Canvas implements Labels
{
    private BufferStrategy buffer;
    private JFrame frame;
    private Graphics graphics;
    private int client = 0;

    public Window(String name, int width, int height)
    {
        setPreferredSize(new Dimension(width, height));
        initWindow(name);
        renderBuffer();
    }

    @Override
    public void showLabels() {
        //TODO fix problem with connect to server
//        Server s = new Server();
//        s.addClient(client);
        JLabel connectionClientsAmount = new JLabel("Connection clients:  ");
        connectionClientsAmount.setBounds(250, -80, 180, 200);
        frame.add(connectionClientsAmount);
    }

    private void renderBuffer() {
        if(buffer == null)
            createBufferStrategy(3);

        buffer = getBufferStrategy();
        graphics = buffer.getDrawGraphics();
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(Color.WHITE);
        graphics.dispose();
        buffer.show();
    }

    private void initWindow(String name) {
        frame = new JFrame(name);

        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        showLabels();
        frame.add(this);
        frame.pack();
    }
}