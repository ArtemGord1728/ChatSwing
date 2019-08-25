package main.java.core;

import java.util.Scanner;

public class ServerMain {
	public static void main(String[] args) {
		int port;
		
		Scanner sc = new Scanner(System.in);
		port = sc.nextInt();
		new ServerSide(port);
	}
}
