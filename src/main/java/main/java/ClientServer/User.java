package main.java.ClientServer;

public class User
{
    private String name;
    private int age;
    private String host;

    public User(String name, int age, String host) {
        this.name = name;
        this.age = age;
        this.host = host;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getHost() {
        return host;
    }
}
