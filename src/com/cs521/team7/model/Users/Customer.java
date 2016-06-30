package com.cs521.team7.model.Users;

/**
 * Created by Acer on 6/29/2016.
 */
public class Customer extends User{

    String fname;
    String lname;
    String email;
    String phoneno;

    public Customer(String username, String password, String fname, String lname, String email, String phoneno) {
        super(username, password);
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phoneno = phoneno;
        this.type="CUSTOMER";
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}
