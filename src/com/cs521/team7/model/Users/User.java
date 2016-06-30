package com.cs521.team7.model.Users;

/**
 * Created by Acer on 6/29/2016.
 */
public class User {
    String username;
    String password;
    String type;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
