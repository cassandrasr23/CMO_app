package com.example.cmoproject.Models;

public class ShowValues {

    public String categoriename;
    public String value;


    public ShowValues(String categorie, String value) {
        this.categoriename = categorie;
        this.value = value;
    }

    public String getCategorie() {
        return categoriename;
    }

    public void setCategorie(String categorie) {
        this.categoriename = categorie;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
