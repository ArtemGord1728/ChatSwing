package main.java.SQLPack;

import java.sql.Connection;

public class SQLHelper
{
    Connection connection;

    private SQLHelper()
    {

    }

    public static SQLHelper getInstanceSQL(){
        return new SQLHelper();
    }
}
