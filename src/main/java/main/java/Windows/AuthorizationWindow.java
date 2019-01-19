package main.java.Windows;

import main.java.InterfacePack.Layer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class AuthorizationWindow extends Canvas implements Layer {
    private BufferStrategy buffer;
    private JFrame frame;
    private Graphics graphics;
    private static JTextField loginInput, portInput;
    private JButton btn_reg;
    public static String nameStr;
    public static String portStr;

    public AuthorizationWindow(String name, int width, int height) {
        setPreferredSize(new Dimension(width, height));
        initWindow(name);
        renderBuffer();
    }


    public void showLabels() {
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(80, -40, 180, 200);

        JLabel hostLabel = new JLabel("Port");
        hostLabel.setBounds(85, 25, 180, 200);
        frame.add(loginLabel);
        frame.add(hostLabel);
    }

    public void showTextLayer() {
        loginInput = new JTextField();
        loginInput.setBounds(50, 70, 180, 200);
        loginInput.setSize(new Dimension(100, 30));
        frame.add(loginInput);

        portInput = new JTextField();
        portInput.setBounds(50, 130, 180, 200);
        portInput.setSize(new Dimension(100, 30));
        frame.add(portInput);

        portInput.addKeyListener(new ActionsListeners());
        loginInput.addKeyListener(new ActionsListeners());
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
        showButton();
        showLabels();
        frame.add(this);
        frame.pack();
    }

    public void showButton() {

        loginInput.addKeyListener(new ActionsListeners());
        btn_reg = new JButton("Registration");
        btn_reg.addActionListener(new ActionsListeners());
        frame.getContentPane().add(BorderLayout.SOUTH, btn_reg);
    }


    static class ActionsListeners extends KeyAdapter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(loginInput.getText().equals("") || portInput.getText().equals("")) {
                return;
            }
            new ClientWindow("Client", 500, 500);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                new ClientWindow("Client", 500, 500);
            }
            else if(loginInput.getText().equals("") || portInput.getText().equals("")) {
                return;
            }
        }
    }
}
