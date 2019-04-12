package main.java.AppManager;

import main.java.CoreApp.ClientSide;
import main.java.Windows.AuthorizationWindow;
import javax.swing.*;

public class ManagerController
{
    private ClientSide clientInfo;

    public void info(JTextArea txtArea){
        txtArea.append("New user!" + "\n");
        txtArea.append("Login - " + AuthorizationWindow.nameStr + "\n");
        txtArea.append("Port - " + AuthorizationWindow.portStr + "\n");
    }

    //TODO: do anything else
}
