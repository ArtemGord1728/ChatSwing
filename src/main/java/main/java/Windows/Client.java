package main.java.Windows;

import main.java.CoreApp.ClientSide;
import main.java.InterfacePack.Layer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Client extends Canvas implements Layer
{

    private static JTextArea textArea;
    private static JTextField dataInput;
    private JFrame frame;
    private InetAddress ip;
    private int port;
    private String name, address;
    private JButton btn_send;
    private Graphics graphics;
    private BufferStrategy buffer;
    private static ClientSide clientSide;


    public Client(String name, int port) throws UnknownHostException {
        this.name = name;
        this.port = port;
        ip = InetAddress.getByName(address);
        setPreferredSize(new Dimension(500, 500));
        initWindow("Client");
        renderBuffer();
        textInArea("Hello Client");
    }

    @Override
    public void showLabels() {

    }

    @Override
    public void showTextLayer() {
        textArea = new JTextArea();
        textArea.setVisible(true);
        textArea.setLineWrap(true);
        textArea.setEnabled(false);
        textArea.setWrapStyleWord(true);
        textArea.setBounds(10, 20, getWidth(), getHeight());
        textArea.setSize(new Dimension(450, 450));

        dataInput = new JTextField();
        frame.getContentPane().add(BorderLayout.SOUTH, dataInput);
        frame.add(textArea);
    }

    @Override
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

    @Override
    public void initWindow(String name) {
        frame = new JFrame(name);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        showTextLayer();
        showLabels();
        showButton();
        frame.add(this);
        frame.pack();
    }

    @Override
    public void showButton() {
        btn_send = new JButton("Send");
        frame.add(btn_send);
    }

    private void textInArea(String someText){
        textArea.setText(someText);
    }

    class ActionEvent extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                clientSide.sendTextMessage(dataInput.getText().getBytes());
            }
        }
    }
}