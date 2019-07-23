package main.java.core;

import java.util.UUID;

public class User 
{
	private int port;
	private String host;
	private UUID authKey;
	
	public User(int port, String host, UUID authKey) {
		this.port = port;
		this.host = host;
		this.authKey = authKey;
	}
	
	public int getPort() {
		return port;
	}
	
	public String getHost() {
		return host;
	}
	
	public UUID getAuthKey() {
		return authKey;
	}
}
