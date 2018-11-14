package InterfacePack;

import javax.swing.*;
import java.awt.*;
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
            createBufferStrategy(6);

        buffer = getBufferStrategy();
        graphics = buffer.getDrawGraphics();
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(Color.WHITE);
        graphics.dispose();
    }

    private void initWindow(String name)
    {
        frame.setResizable(true);
        frame.add(this);
        frame.setVisible(true);
        frame.setTitle(name);
        frame.pack();
    }

}
