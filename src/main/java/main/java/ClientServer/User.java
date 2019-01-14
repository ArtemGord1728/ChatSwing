package main.java.ClientServer;

public class User
{
    private String name;
    private String host;

    public User(String name, String host) {
        this.name = name;
        this.host = host;
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }
}
