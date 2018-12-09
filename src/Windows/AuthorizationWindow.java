package Windows;

import InterfacePack.InterfaceElements.Layer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class AutorizeWindow extends Canvas implements Layer
{
    private BufferStrategy buffer;
    private JFrame frame;
    private JLabel login, host, password;
    private Graphics graphics;

    public AutorizeWindow(String name, int width, int height)
    {
        setPreferredSize(new Dimension(width, height));
        initWindow(name);
        renderBuffer();
    }


    @Override
    public void showLabels() {
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(80, -40, 180, 200);
        frame.add(loginLabel);

        JLabel hostLabel = new JLabel("Host");
        hostLabel.setBounds(83, 30, 180, 200);
        frame.add(hostLabel);

        JLabel ageLabel = new JLabel("Age");
        ageLabel.setBounds(85, 90, 180, 200);
        frame.add(ageLabel);
    }

    @Override
    public void showTextLayer() {
        JTextField loginInput = new JTextField();
        loginInput.setVisible(true);
        loginInput.setBounds(50, 80, 180, 200);
        loginInput.setSize(new Dimension(100, 30));
        frame.add(loginInput);

        JTextField hostInput = new JTextField();
        hostInput.setVisible(true);
        hostInput.setBounds(50, 140, 180, 200);
        hostInput.setSize(new Dimension(100, 30));
        frame.add(hostInput);

        JTextField ageInput = new JTextField();
        ageInput.setVisible(true);
        ageInput.setBounds(50, 200, 180, 200);
        ageInput.setSize(new Dimension(100, 30));
        frame.add(ageInput);
    }

    @Override
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

    @Override
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
}
