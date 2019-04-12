package main.java.artem.serverswing;

import main.java.CoreApp.ServerSide;
import main.java.Windows.AuthorizationWindow;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Scanner;

public class ServerMain {

	private int port;

	public ServerMain(int port) {
		this.port = port;
		new ServerSide(port);
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException, UnknownHostException {
		int port;
		String str;

		Scanner sc = new Scanner(System.in);
		port = sc.nextInt();
		new ServerMain(port);

		
	}
}
