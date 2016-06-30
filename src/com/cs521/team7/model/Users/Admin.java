package com.cs521.team7.model.Users;

/**
 * Created by Acer on 6/29/2016.
 */
public class Admin extends User{
    public Admin(String username, String password) {
        super(username, password);
        this.type="ADMIN";
    }
}
