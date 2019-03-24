package main.java.artem.serverswing;

import main.java.AppManager.Manager;
import main.java.CoreApp.ServerSide;
import main.java.Windows.AuthorizationWindow;
import main.java.Windows.Client;

import java.sql.SQLException;

public class Main {

    private int port;
    private ServerSide serverSide;

    public Main(int port){
        this.port = port;
        new ServerSide(port);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        int port;
        if(args.length != 1) {
            System.out.println("Usage: java -jar program.jar <port>");
            System.exit(1);
        }
        if(args[0].equalsIgnoreCase("/exit")){
            System.out.println("Exit from server!");
            System.exit(1);
        }
        port = Integer.parseInt(args[0]);
        new ServerSide(port);

//        new AuthorizationWindow("name", 300, 300);
//        new Manager();


    }
}
