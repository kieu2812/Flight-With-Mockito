package com.example.request;

public class SearchTicketRequest {
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

    public SearchTicketRequest(int price, String name) {
        this.price = price;
        this.name = name;
    }
}
