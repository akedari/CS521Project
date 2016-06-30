package com.cs521.team7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Acer on 6/26/2016.
 */
public class DatabaseConnection {
    static Connection db=null;

    public static void init()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            db = DriverManager
                    .getConnection("jdbc:mysql://cs521.c4lyzaywi83r.us-east-1.rds.amazonaws.com:3306/cs521team7","team7", "team1234");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection()
    {
        if(db==null)
            init();
        return db;
    }
}
