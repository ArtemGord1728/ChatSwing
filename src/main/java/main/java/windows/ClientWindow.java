package main.java.windows;

import main.java.core.ClientSide;
import main.java.interface_pack.Layer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientWindow extends Canvas implements Layer
{
    private static JTextArea textArea;
    private static JTextField dataInput;
    private JFrame frame;
    private InetAddress ip;
    private int port;
    private String name, host;
    private JButton btn_send;
    private Graphics graphics;
    private BufferStrategy buffer;
    private static ClientSide clientSide;


    public ClientWindow(String name, int port, String host) throws UnknownHostException {
        this.name = name;
        this.port = port;
        this.host = host;
        setPreferredSize(new Dimension(500, 500));
        initWindow(name);
        renderBuffer();
        clientSide = new ClientSide(name, port, host);
        System.out.println("Client name: " + name + ", port: " + port);
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
        dataInput.setText(null);
        frame.getContentPane().add(BorderLayout.SOUTH, dataInput);
        frame.add(textArea);
    }


    @Override
    public void showButton() {
        btn_send = new JButton("Send File");
        btn_send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
        frame.add(btn_send);
    }
    
    private void send(String msg) {
    	String message = name + ": " + msg;
    	textInArea(message);
    	System.out.println();
    	
    }

    private void textInArea(String someText){
    	textArea.setCaretPosition(textArea.getDocument().getLength());
        textArea.append(someText + "\n\r");
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
        frame = new JFrame("@" + name);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        showTextLayer();
        showLabels();
        showButton();
        frame.add(this);
        frame.pack();
    }
}