package main.java.core;


public class ServerMain {
	public static void main(String[] args) {
		int port;
		String host;

		if(args.length == 0) {
			System.out.println("Usage: java -jar Server.jar <port> <host>");
		}
		
		port = Integer.parseInt(args[0]);
		host = args[1];
		
		new ServerSide(port, host);
	}
}
