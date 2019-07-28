package main.java.windows;

import main.java.interface_pack.Layer;
import main.java.sql_manager.SQLHelper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.net.UnknownHostException;
import java.sql.SQLException;

public class AuthorizationWindow extends Canvas implements Layer {
    private BufferStrategy buffer;
    private JFrame frame;
    public static boolean isAuthorization = false;
    private Graphics graphics;
    private static JTextField loginInput, portInput, hostInput;
    private JButton btn_reg;
    private static final int width = 180;
    private static final int height = 200;
    //private static SQLHelper sqlHelper;
    public static String nameStr, hostStr;
    public static int portStr;

    public AuthorizationWindow(String name, int width, int height) throws SQLException, ClassNotFoundException {
        setPreferredSize(new Dimension(width, height));
        //sqlHelper = new SQLHelper();
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
            try {
				checkNull();
				createNewUser();
			} catch (UnknownHostException message) {
				message.printStackTrace();
				return;
			}
        }
        
        public void keyPressed(KeyEvent e) {
        	if(e.getKeyCode() == KeyEvent.VK_ENTER) {
        		try {
					checkNull();
					createNewUser();
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				}
        		
        	}
        }
        
        private void checkNull() throws UnknownHostException {
        	nameStr = loginInput.getText();
            portStr = Integer.parseInt(portInput.getText());
            hostStr = hostInput.getText();
        	if(loginInput.getText().equals("") || 
        	   portInput.getText().equals("") || 
        	   hostInput.getText().equals("")) {
        		System.out.println("Some field isn't filled. Check for data in the fields");
        		return;
            }
        }
    }
     
    private void createNewUser() {
    	loginUser(nameStr, portStr, hostStr);
    	isAuthorization = true;
		//sqlHelper.insert(nameStr, portStr, hostStr);
    }
    
    private void loginUser(String name, int port, String host) {
    	frame.dispose();
    	try {
        	new ClientWindow(name, port, host);
    	}
    	catch(UnknownHostException e) {
    		System.out.println(e.getMessage());
    	}
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
