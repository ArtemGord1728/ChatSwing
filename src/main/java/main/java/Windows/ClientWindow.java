package main.java.Windows;

import main.java.CoreApp.ClientSide;
import main.java.InterfacePack.Layer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
        clientSide = new ClientSide(name, port);
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
        dataInput.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
        			send(dataInput.getText());
        		}
        	}
		});
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
        btn_send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				send(dataInput.getText());
			}
		});
        frame.add(btn_send);
    }
    
    private void send(String msg) {
    	String message = name + ":" + msg;
    	textInArea(msg);
    	clientSide.sendTextMessage(message.getBytes());
    	dataInput.setText("");
    }

    private void textInArea(String someText){
    	textArea.setCaretPosition(textArea.getDocument().getLength());
        textArea.append(someText + "\n\r");
    }
}