package com.example.controller;

import com.example.request.SearchTicketRequest;
import com.example.controller.FlightController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.TimeZone;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void testGetFlight() throws Exception {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ft.setTimeZone(TimeZone.getTimeZone("EST"));
        this.mvc.perform(get("/flights/flight"))
                .andExpect(status().isOk())
             //   .andExpect(jsonPath("$.departs", is("2017-04-21 14:34")))
          //      .andExpect(jsonPath("$.departs", is(ft.parse("2017-04-21 14:34"))))
                .andExpect(jsonPath("$.Tickets[0].Price", is(200)))
                .andExpect(jsonPath("$.Tickets[0].Passenger.FirstName", is("Some name")))
                .andExpect(jsonPath("$.Tickets[0].Passenger.LastName", is("Some other name")));

    }

    @Test
    public void testGetFlights() throws Exception {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ft.setTimeZone(TimeZone.getTimeZone("EST"));
        this.mvc.perform(get("/flights"))
                .andExpect(status().isOk())
                //   .andExpect(jsonPath("$.departs", is("2017-04-21 14:34")))
                //      .andExpect(jsonPath("$.departs", is(ft.parse("2017-04-21 14:34"))))
                .andExpect(jsonPath("$[0].Tickets[0].Price", is(200)))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.FirstName", is("Some name")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.LastName", is("Some other name")))
                .andExpect(jsonPath("$[1].Tickets[0].Price", is(400)))
                .andExpect(jsonPath("$[1].Tickets[0].Passenger.FirstName", is("Some other name"))) ;

    }
    @Test
    public void testGetTicketsByObjectMapper() throws Exception {

        SearchTicketRequest searchTicket =  new SearchTicketRequest(300, "M");
        String jsonSearchTicket = new ObjectMapper().writeValueAsString(searchTicket);

        RequestBuilder request = post("/tickets/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonSearchTicket);
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].Price", is(200)))
                .andExpect(jsonPath("$[0].Price", is(300)))
                .andExpect(jsonPath("$[1].Passenger.FirstName", is("Mora A")))
                .andExpect(jsonPath("$[0].Passenger.FirstName", is("Matt B")));

    }
    @Test
    public void testGetTicketsByMap() throws Exception {

        HashMap<String, Object> map =  new HashMap<>();
        map.put("price", 300);
        map.put("name", "M");
        String jsonSearchTicket = new ObjectMapper().writeValueAsString(map);

        RequestBuilder request = post("/tickets/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonSearchTicket);
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].Price", is(200)))
                .andExpect(jsonPath("$[0].Price", is(300)))
                .andExpect(jsonPath("$[1].Passenger.FirstName", is("Mora A")))
                .andExpect(jsonPath("$[0].Passenger.FirstName", is("Matt B")));
    }
    @Test
    public void testRawBody() throws Exception {
        String json = getJSON("src/test/resources/data.json");
        System.out.println("JSON from file "+ json);
        RequestBuilder request = post("/tickets/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].Price", is(200)))
                .andExpect(jsonPath("$[0].Price", is(300)))
                .andExpect(jsonPath("$[1].Passenger.FirstName", is("Mora A")))
                .andExpect(jsonPath("$[0].Passenger.FirstName", is("Matt B")));
    }


    private String getJSON(String filePath) throws Exception {
        Path path = Paths.get(filePath);
        return new String(Files.readAllBytes(path));
    }

}
