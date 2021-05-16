package com.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Flight {
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm")
    private Date departs;

    private List<Ticket> tickets;

   // @JsonGetter("Departs")
    public Date getDeparts() {
        return departs;
    }

    public void setDeparts(Date departs) {
        this.departs = departs;
    }
  //  @JsonGetter("Tickets")
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    public Flight(){}
    public Flight(Date departs, List<Ticket> tickets) {
        this.departs = departs;
        this.tickets = tickets;
    }
}
