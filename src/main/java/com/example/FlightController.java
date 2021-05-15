package com.example;

import com.example.Flight.Flight;
import com.example.Flight.SearchTicket;
import com.example.Person.Person;
import com.example.Ticket.Ticket;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
    public List<Ticket> getTickets(@RequestBody SearchTicket searchTicket) {
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
}
