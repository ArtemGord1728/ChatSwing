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
    private static JTextField loginInput, portInput, hostCompanionInput;
    private JButton btn_reg;
    private static final int width = 180;
    private static final int height = 200;
    public static String nameStr;
    public static int portStr;

    public AuthorizationWindow(String name, int width, int height) {
        setPreferredSize(new Dimension(width, height));
        initWindow(name);
        renderBuffer();
    }


    public void showLabels() {
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(80, -40, width, height);

        JLabel hostLabel = new JLabel("Port");
        hostLabel.setBounds(85, 25, width, height);

        JLabel hostCompanionLabel = new JLabel("Host of Companion");
        hostCompanionLabel.setBounds(45, 80, width, height);

        frame.add(hostCompanionLabel);
        frame.add(loginLabel);
        frame.add(hostLabel);
    }

    public void showTextLayer() {
        loginInput = new JTextField();
        loginInput.setBounds(50, 70, 100, 30);

        portInput = new JTextField();
        portInput.setBounds(50, 130, 100, 30);

        hostCompanionInput = new JTextField();
        hostCompanionInput.setBounds(50, 190, 100, 30);

        frame.add(loginInput);
        frame.add(portInput);
        frame.add(hostCompanionInput);
        loginInput.addKeyListener(new ActionsListeners());
        portInput.addKeyListener(new ActionsListeners());
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


    class ActionsListeners extends KeyAdapter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            nameStr = loginInput.getText();
            portStr = Integer.parseInt(portInput.getText());
            if(nameStr.equals("") || portInput.getText().equals("")) {
                return;
            }
            new Client(nameStr, portStr, "500");
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                new Client("Client", 500, "500");
            }
            else if(loginInput.getText().equals("") || portInput.getText().equals("")) {
                return;
            }
        }
    }
}
