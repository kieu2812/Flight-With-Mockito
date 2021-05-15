package com.example.Flight;

public class SearchTicket {
    private int price;
    private String name;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SearchTicket(int price, String name) {
        this.price = price;
        this.name = name;
    }
}