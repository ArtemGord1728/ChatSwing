package main.java.Windows;

import main.java.CoreApp.ClientSide;
import main.java.InterfacePack.BuilderGUI;
import main.java.InterfacePack.Layer;
import main.java.SQLPack.SQLHelper;

import javax.swing.*;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.UUID;

public class AuthorizationWindow extends Canvas implements Layer {
    private BufferStrategy buffer;
    private JFrame frame;
    private Graphics graphics;
    private static JTextField loginInput, portInput, hostInput;
    private JButton btn_reg;
    private static final int width = 180;
    private static final int height = 200;
    private static SQLHelper sqlHelper;
    public static String nameStr, hostStr;
    public static int portStr;

    public AuthorizationWindow(String name, int width, int height) throws SQLException, ClassNotFoundException {
        setPreferredSize(new Dimension(width, height));
        sqlHelper = new SQLHelper();
        initWindow(name);
        renderBuffer();
        loginInput.setText("user");
    }


    public void showLabels() {
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(80, -40, width, height);

        JLabel portLabel = new JLabel("Port");
        portLabel.setBounds(85, 25, width, height);

        JLabel hostLabel = new JLabel("Host");
        hostLabel.setBounds(84, 92, width, height);
        
        frame.add(loginLabel);
        frame.add(portLabel);
        frame.add(hostLabel);
    }

    public void showTextLayer() {
        loginInput = new JTextField();
        loginInput.setBounds(50, 70, 100, 30);

        portInput = new JTextField();
        portInput.setBounds(50, 130, 100, 30);

        hostInput = new JTextField();
        hostInput.setBounds(50, 200, 100, 30);
        
        frame.add(loginInput);
        frame.add(portInput);
        frame.add(hostInput);
        loginInput.addKeyListener(new ActionsListeners());
        portInput.addKeyListener(new ActionsListeners());
        hostInput.addKeyListener(new ActionsListeners());
    }

    public void showButton() {
        loginInput.addKeyListener(new ActionsListeners());
        btn_reg = new JButton("Registration");
        btn_reg.addActionListener(new ActionsListeners());
        frame.getContentPane().add(BorderLayout.SOUTH, btn_reg);
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
    
     class ActionsListeners extends KeyAdapter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            nameStr = loginInput.getText();
            portStr = Integer.parseInt(portInput.getText());
            hostStr = hostInput.getText();
            if(loginInput.getText().equals("") || portInput.getText().equals("") || hostInput.getText().equals("")) {
                return;
            }
            
            try {
				loginUser(nameStr, portStr, hostStr);
				sqlHelper.insert(nameStr, portStr, hostStr);
			} catch (UnknownHostException message) {
				message.printStackTrace();
				return;
			}
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
        	if(e.getKeyCode() == KeyEvent.VK_ENTER) {
        		try {
    				loginUser(nameStr, portStr, hostStr);
    				sqlHelper.insert(nameStr, portStr, hostStr);
    			} catch (UnknownHostException message) {
    				message.printStackTrace();
    				return;
    			}
        	}
        }
    }
    
    private void loginUser(String name, int port, String host) throws UnknownHostException {
    	frame.dispose();
    	new ClientWindow(name, port, host);
    }
    
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new AuthorizationWindow("Auth", 200, 300);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
