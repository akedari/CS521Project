package com.cs521.team7;

import com.cs521.team7.model.Users.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Acer on 6/14/2016.
 */
public class RunProject {
    public static void main(String args[]) {
        try {
            HomePage reservationFrame = new HomePage(new User("s","s"));
            //LoginPage reservationFrame = new LoginPage();
            reservationFrame.setTitle("Login");
            reservationFrame.setVisible(true);


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
