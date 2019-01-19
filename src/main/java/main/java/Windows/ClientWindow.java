package main.java.Windows;

import main.java.InterfacePack.Layer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class ClientWindow extends Canvas implements Layer {

    private JTextArea textArea;
    private JFrame frame;
    private Graphics graphics;
    private BufferStrategy buffer;

    public ClientWindow(String name, int width, int height) {
        setPreferredSize(new Dimension(width, height));
        initWindow(name);
        renderBuffer();
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
        initWindow("Client");
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

    }
}

