package com.example.cmoproject.Models;

public class User {

    public String first_name,last_name,email;

    public User() {
        this("", "", "");
    }

    public User(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }


}
