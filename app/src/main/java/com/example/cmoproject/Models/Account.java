package com.example.cmoproject.Models;

public class Account {

    public String account_name;
    public String id_user;

    public Account(){
        this ("","");
    }

    public Account(String account_name, String id_user) {
        this.account_name = account_name;
       this.id_user=id_user;
    }
}
