package com.example.virtualcompany;

public class User {

    public String userName;
    public String Email;
    public String ID;

    public User(String Email,String ID,String userName)
    {
        this.userName = userName;
        this.Email = Email;
        this.ID = ID;
    }

    public String getEmail() {
        return Email;
    }

    public String getID() {
        return ID;
    }

    public String getUserName() {
        return userName;
    }

}
