package main.java.artem.serverswing;

import main.java.Windows.AuthorizationWindow;
import main.java.Windows.ClientWindow;


public class Main {
    public static void main(String[] args) {
        new AuthorizationWindow("Auth", 200, 400);

        if(AuthorizationWindow.isRegistration)
        {
            new ClientWindow("Client", 500, 500);
        }
    }
}
