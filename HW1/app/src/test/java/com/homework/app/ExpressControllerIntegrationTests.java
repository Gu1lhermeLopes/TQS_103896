package com.homework.app;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Collections;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.homework.app.model.Ticket;
import com.homework.app.model.Trip;
import com.homework.app.service.ApiService;
import com.homework.app.service.CurrencyService;

@SpringBootTest
@AutoConfigureMockMvc
class ExpressControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApiService service;

    @MockBean
    private CurrencyService currencyService;

    @Test
    void testIndex() throws Exception {
        when(service.getDepartures()).thenReturn(Arrays.asList("City1", "City2"));
        when(service.getArrivals()).thenReturn(Arrays.asList("City3", "City4"));
        when(currencyService.getSupportedCurrencies()).thenReturn(Arrays.asList("USD", "EUR"));

        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("depart"))
                .andExpect(model().attributeExists("arrive"))
                .andExpect(model().attributeExists("preferredCurrency"))
                .andExpect(model().attributeExists("currency"));
    }

    @Test
    void testTestMoney() throws Exception {
        mockMvc.perform(get("/testmoney"))
                .andExpect(status().isOk())
                .andExpect(view().name("exchange-rates-view"))
                .andExpect(model().attributeExists("exchangeRates"));
    }

    @Test
    void testCurrency() throws Exception {
        mockMvc.perform(post("/currency").param("currency", "USD"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));
    }

    @Test
    void testResult() throws Exception {
        when(service.getTripsByDepartAndArrive(toString(), toString())).thenReturn(Collections.emptyList());

        mockMvc.perform(post("/result").param("depart", "City1").param("arrive", "City2"))
                .andExpect(status().isOk())
                .andExpect(view().name("trips-result"))
                .andExpect(model().attributeExists("trips"))
                .andExpect(model().attributeExists("preferredCurrency"))
                .andExpect(model().attributeExists("city1"))
                .andExpect(model().attributeExists("city2"));
    }

    @Test
    void testBuyTicket() throws Exception {
        Trip trip = new Trip();
        trip.setId(1L);
        trip.setTotalSeats(10);
        trip.setCompany("nonnull");
        trip.setPrice(10.0);

        when(service.getTripById(anyLong())).thenReturn(trip);
        when(currencyService.convertTripPrice( any(Trip.class), anyString() )).thenReturn(trip);

        mockMvc.perform(post("/buyticket").param("tripId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("buyticket"))
                .andExpect(model().attributeExists("trip"))
                .andExpect(model().attributeExists("ticket"))
                .andExpect(model().attributeExists("preferredCurrency"));
    }

    @Test
    void testConfirm() throws Exception {
        Trip trip = new Trip();
        trip.setId(1L);
        trip.setTotalSeats(10);
        trip.setCompany("nonnull");
        trip.setPrice(10.0);
    
        Ticket ticket = new Ticket();
        ticket.setId(1L); // Setting a non-null id for the ticket
    
        when(service.getTripById(anyLong())).thenReturn(trip);
        when(service.getTicketById(anyLong())).thenReturn(ticket);
    
        mockMvc.perform(post("/confirm")
                .param("tripId", "1")
                .param("creditCardMonth", "12")
                .param("creditCardYear", "2024")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("ticket", ticket)) // Passing the ticket object with a non-null id
                .andExpect(status().isOk())
                .andExpect(view().name("confirm-ticket"))
                .andExpect(model().attributeExists("ticket"));
    
        verify(service).getTripById(1L);
        verify(service).saveTicket(ticket);

    }

    @Test
    void testAccessTicket() throws Exception {
        mockMvc.perform(get("/acessticket"))
                .andExpect(status().isOk())
                .andExpect(view().name("access-ticket"));
    }

    @Test
    void testShowTicket() throws Exception {
        Ticket ticket = new Ticket();
        Trip trip = new Trip();
        trip.setPrice(10.0);
        
        ticket.setId(1L);
        ticket.setTrip(trip);
        service.saveTicket(ticket);

        when(service.accessTicket(anyLong(), anyString())).thenReturn(ticket);

        mockMvc.perform(post("/ticket")
                .param("ticketId", "1")
                .param("token", "token123"))
                .andExpect(status().isOk())
                .andExpect(view().name("ticket-final"))
                .andExpect(model().attributeExists("ticket"));
    }

    @Test
    void testShowTickets() throws Exception {
        when(service.getAllTickets()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/tickets"))
                .andExpect(status().isOk())
                .andExpect(view().name("tickets"))
                .andExpect(model().attributeExists("tickets"));
    }
}