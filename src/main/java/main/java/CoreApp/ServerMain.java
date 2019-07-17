package main.java.CoreApp;

import java.util.Scanner;

public class ServerMain {
	public static void main(String[] args) {
		int port;
		String host;

		Scanner sc = new Scanner(System.in);
		port = sc.nextInt();
		host = sc.next();
		new ServerSide(port, host);
		
	}
}
