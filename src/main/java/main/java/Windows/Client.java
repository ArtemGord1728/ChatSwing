package main.java.Windows;

import main.java.CoreApp.ClientSide;
import main.java.InterfacePack.Layer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

public class Client extends Canvas implements Layer {

    private JTextArea textArea;
    private JTextField dataInput;
    private JFrame frame;
    private JButton btn_send;
    private Graphics graphics;
    private BufferStrategy buffer;
    private ClientSide clientSide;

    public Client(String name, int port, String hostCompanion) {
        setPreferredSize(new Dimension(500, 500));
        initWindow("Client");
        renderBuffer();
        clientSide = new ClientSide(name, port, hostCompanion);
    }

    @Override
    public void showLabels() {

    }

    public void showTextLayer() {
        textArea = new JTextArea();
        textArea.setVisible(true);
        textArea.setLineWrap(true);
        textArea.setEnabled(false);
        textArea.setWrapStyleWord(true);
        textArea.setBounds(10, 40, getWidth(), getHeight());
        textArea.setSize(new Dimension(250, 350));

        dataInput = new JTextField();
        frame.getContentPane().add(BorderLayout.SOUTH, dataInput);
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
        btn_send = new JButton("Send");
        btn_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientSide.sendTextMessage(dataInput.getText().getBytes());
            }
        });
    }
}

