package com.example.muzeumfrontendjavafx;

public class Statues {
    private int id;
    private String person;
    private int hight;
    private int price;

    public Statues(int id, String person, int hight, int price) {
        this.id = id;
        this.person = person;
        this.hight = hight;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getPerson() {
        return person;
    }

    public int getHight() {
        return hight;
    }

    public int getPrice() {
        return price;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
