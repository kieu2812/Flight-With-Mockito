package com.example.request;

import com.example.model.Ticket;
import com.example.response.TicketTotal;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class TicketRequest {
    @JsonProperty("tickets")
    private List<Ticket> tickets;

    public TicketRequest(){
        tickets = new ArrayList<>();
    }
    public TicketRequest(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "TicketRequest{" +
                "tickets=" + tickets +
                '}';
    }
}
