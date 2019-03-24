package main.java.SQLPack;

import java.sql.*;

public class SQLHelper
{
    private static Connection connection;
    private static Statement statement;
    private final String url = "jdbc:mysql://localhost:3306/serverswing_db";
    private final String user_name = "root";
    private final String password = "saboteur";

    public SQLHelper() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, user_name, password);
        statement = connection.createStatement();
    }

    public void insert(String name, int host_or_port) {
        String query = "INSERT INTO clients VALUES(NULL, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, String.valueOf(host_or_port));
            preparedStatement.executeUpdate();
        }
        catch (SQLException exception){
            exception.getMessage();
        }
    }
}
