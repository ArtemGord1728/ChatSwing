package InterfacePack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

public class Window extends Canvas
{
    private BufferStrategy buffer;
    private JFrame frame;
    private Graphics graphics;

    public Window(String name, int width, int height)
    {
        setPreferredSize(new Dimension(width, height));
        initWindow(name);
        renderBuffer();
    }

    private void renderBuffer()
    {
        if(buffer == null)
            createBufferStrategy(3);

        buffer = getBufferStrategy();
        graphics = buffer.getDrawGraphics();
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(Color.WHITE);
        graphics.dispose();
        buffer.show();
    }

    private void initWindow(String name)
    {
        frame = new JFrame(name);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(this);
        frame.pack();
    }

}
