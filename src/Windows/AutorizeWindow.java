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
        loginLabel.setBounds(80, -20, 180, 200);
        frame.add(loginLabel);
    }

    @Override
    public void showTextLayer() {

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
