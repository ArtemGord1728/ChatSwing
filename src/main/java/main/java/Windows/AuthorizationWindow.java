package main.java.Windows;

import main.java.InterfacePack.Layer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

public class AuthorizationWindow extends Canvas implements Layer
{
    private BufferStrategy buffer;
    private JFrame frame;
    private Graphics graphics;
    private JButton btn_reg;
    public static String nameStr;
    public static String hostStr;
    public static int ageStr;
    public static boolean isRegistration;

    public AuthorizationWindow(String name, int width, int height)
    {
        setPreferredSize(new Dimension(width, height));
        initWindow(name);
        renderBuffer();
    }


    public void showLabels() {
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(80, -40, 180, 200);
        frame.add(loginLabel);

        JLabel hostLabel = new JLabel("Host");
        hostLabel.setBounds(83, 25, 180, 200);
        frame.add(hostLabel);

        JLabel ageLabel = new JLabel("Age");
        ageLabel.setBounds(85, 90, 180, 200);
        frame.add(ageLabel);
    }

    public void showTextLayer() {
        JTextField loginInput = new JTextField();
        loginInput.setVisible(true);
        loginInput.setBounds(50, 70, 180, 200);
        loginInput.setSize(new Dimension(100, 30));
        frame.add(loginInput);

        JTextField hostInput = new JTextField();
        hostInput.setVisible(true);
        hostInput.setBounds(50, 135, 180, 200);
        hostInput.setSize(new Dimension(100, 30));
        frame.add(hostInput);

        JTextField ageInput = new JTextField();
        ageInput.setVisible(true);
        ageInput.setBounds(100, 100, 180, 200);
        ageInput.setSize(new Dimension(100, 30));
        frame.add(ageInput);
    }

    public void renderBuffer() {
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

    public void initWindow(String name) {
        frame = new JFrame(name);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showLabels();
        showTextLayer();
        showButton();
        frame.setVisible(true);
        frame.add(this);
        frame.pack();
    }

    public void showButton() {
        btn_reg = new JButton("Registration");
        btn_reg.setBounds(10, 10, 50, 50);
        btn_reg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRegistration = true;
            }
        });
    }
}
