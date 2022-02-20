package com.example.muzeumfrontendjavafx.paintings;

public class Paintings {
    private int id;
    private String title;
    private int year;
    private boolean on_display;

    public Paintings(int id, String title, int year, boolean on_display) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.on_display = on_display;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean getOn_display() {
        return on_display;
    }

    public void setOn_display(boolean on_display) {
        this.on_display = on_display;
    }
}
