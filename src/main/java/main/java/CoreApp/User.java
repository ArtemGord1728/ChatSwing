package main.java.CoreApp;

import java.util.UUID;

public class User 
{
	private int port;
	private String host;
	private long authKey;
	private UUID uniqAuthKey;
	
	public User(int port, String host, UUID authKey) {
		this.port = port;
		this.host = host;
		uniqAuthKey = UniqueAuthKey.setAuthKey();
		authKey = uniqAuthKey;
	}
	
	public int getPort() {
		return port;
	}
	
	public String getHost() {
		return host;
	}
	
	public long getAuthKey() {
		return authKey;
	}
}
