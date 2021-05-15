package com.example.Ticket;

import com.example.Person.Person;
import com.fasterxml.jackson.annotation.JsonGetter;

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
}
