package main.java.AppManager;

import main.java.InterfacePack.Layer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Manager extends Canvas implements Layer {
    private JLabel label;
    private Graphics graphics;
    private JTextArea textArea;
    private BufferStrategy buffer;
    private JFrame frame;
    private ManagerController controller;

    public Manager() {
        setPreferredSize(new Dimension(500, 500));
        initWindow("Manager");
        renderBuffer();
        controller = new ManagerController();
    }

    @Override
    public void showLabels() {
        label = new JLabel("Information");
        label.setBounds(10, -65, 180, 180);
        frame.add(label);
    }

    @Override
    public void showTextLayer() {
        textArea = new JTextArea();
        textArea.setVisible(true);
        textArea.setLineWrap(true);
        textArea.setEnabled(false);
        textArea.setWrapStyleWord(true);
        textArea.setBounds(10, 40, getWidth(), getHeight());
        textArea.setSize(new Dimension(450, 450));
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

    }
}