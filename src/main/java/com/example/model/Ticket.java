package com.example.model;

import com.example.model.Person;


public class Ticket {
    private int price;
    private Person passenger;

  //  @JsonGetter("Price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

 //   @JsonGetter("Passenger")
    public Person getPassenger() {
        return passenger;
    }

    public void setPassenger(Person passenger) {
        this.passenger = passenger;
    }

    public Ticket(){}
    public Ticket(int price, Person passenger) {
        this.price = price;
        this.passenger = passenger;

    }

    @Override
    public String toString() {
        return "Ticket{" +
                "price=" + price +
                ", passenger=" + passenger +
                '}';
    }
}
