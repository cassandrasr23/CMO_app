package com.example.cmoproject.Models;

public class AccountsValues {
    public String categorie;
    public int value;
    public boolean type;
    public String ida;

    AccountsValues()
    {
        this("",0,false, "" );
    }
    public AccountsValues(String categorie, int value, boolean type, String ida) {
        this.categorie = categorie;
        this.value = value;
        this.type = type;
        this.ida=ida;
    }
}
