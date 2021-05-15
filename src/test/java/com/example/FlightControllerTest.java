package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

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
}
