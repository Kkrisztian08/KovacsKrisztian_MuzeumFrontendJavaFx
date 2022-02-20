package com.example.muzeumfrontendjavafx.statues;

public class Statues {
    private int id;
    private String person;
    private int height;
    private int price;

    public Statues(int id, String person, int hight, int price) {
        this.id = id;
        this.person = person;
        this.height = hight;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getPerson() {
        return person;
    }

    public int getHeight() {
        return height;
    }

    public int getPrice() {
        return price;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
