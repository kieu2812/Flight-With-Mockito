package com.example.controller;

import com.example.model.Flight;
import com.example.request.SearchTicketRequest;
import com.example.model.Person;
import com.example.model.Ticket;
import com.example.request.TicketRequest;
import com.example.response.TicketTotal;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@RestController
public class FlightController {

    @GetMapping("/flights/flight")
    public Flight getFlight(){
        Ticket ticket= new Ticket(200, new Person("Some name", "Some other name"));
        Flight flight =  new Flight();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ft.setTimeZone(TimeZone.getTimeZone("EST"));
        try {
            flight.setDeparts( ft.parse("2017-04-21 14:34"));

            System.out.println("*****************   "+flight.getDeparts());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        flight.setTickets(Arrays.asList(ticket));
        return flight;
    }
    @GetMapping("/flights")
    public List<Flight> getFlights(){
        Ticket ticket= new Ticket(200, new Person("Some name", "Some other name"));
        Ticket ticket2= new Ticket(400, new Person("Some other name", null));
        Flight flight =  new Flight();
        Flight flight2 =  new Flight();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ft.setTimeZone(TimeZone.getTimeZone("EST"));
        try {
            flight.setDeparts( ft.parse("2017-04-21 14:34"));
            flight2.setDeparts( ft.parse("2017-04-21 14:34"));

            System.out.println("*****************   "+flight.getDeparts());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        flight.setTickets(Arrays.asList(ticket));
        flight2.setTickets(Arrays.asList(ticket2));
        return Arrays.asList(flight, flight2);
    }

    @PostMapping("/tickets/search")
    public List<Ticket> getTickets(@RequestBody SearchTicketRequest searchTicket) {
        Ticket ticket= new Ticket(200, new Person("Mora A", "Some other name"));
        Ticket ticket2= new Ticket(300, new Person("Matt B", null));
        Ticket ticket3= new Ticket(400, new Person("Maria", "D"));
        Flight flight =  new Flight();
        flight.setTickets(Arrays.asList(ticket, ticket2, ticket3));
        return flight.getTickets().stream()
                .filter( t-> t.getPrice()<= searchTicket.getPrice() && t.getPassenger().getFirstName().contains(searchTicket.getName()))
                .sorted( (t1, t2) -> t1.getPassenger().getFirstName().compareTo(t2.getPassenger().getFirstName()))
                .collect(Collectors.toList());

    }
//    @PostMapping("/flights/tickets/total")
//    public TicketTotal calculateTicketTotal(@RequestBody List<Ticket> tickets){
//        final TicketTotal ticketTotal = new TicketTotal();
//        System.out.println("******** "+tickets);
//        if(tickets!=null && !tickets.isEmpty()){
//            ticketTotal.setResult(
//                    tickets.stream()
//                           .mapToInt(t -> t.getPrice())
//                    .sum()
//            );
//        }
//        return ticketTotal;
//    }

    @PostMapping("/flights/tickets/total")
    public TicketTotal calculateTicketTotal(@RequestBody TicketRequest ticketRequest){
        final TicketTotal ticketTotal = new TicketTotal();
        System.out.println("******** "+ticketRequest);
        if(ticketRequest!=null && !ticketRequest.getTickets().isEmpty()){
            ticketTotal.setResult(
                    ticketRequest.getTickets().stream()
                            .mapToInt(t -> t.getPrice())
                            .sum()
            );
        }
        return ticketTotal;
    }

}
