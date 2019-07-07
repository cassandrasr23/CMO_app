package com.example.cmoproject.Categories;

public enum Categories {
    HOME(new Category(0, "Home")),
    CAR(new Category(1, "Car")),
    WORK(new Category(2, "Work"));

    Categories(Category category) {
        Category category1 = category;
    }
}

class Category {
    int id;
    String name;

        Category(int id, String name) {
        this.id = id;
        this.name = name;

    }
}