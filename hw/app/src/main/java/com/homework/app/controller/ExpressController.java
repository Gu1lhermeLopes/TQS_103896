package com.homework.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.homework.app.model.*;
import com.homework.app.service.ApiService;
import com.homework.app.service.CurrencyService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ExpressController {

    private final ApiService service;

    private final CurrencyService currencyService;

    @Autowired
    public ExpressController(ApiService service, CurrencyService currencyService) {
        this.service = service;
        this.currencyService = currencyService;
    }

    @GetMapping("/testmoney")
    public String testMoney(Model model) {

        model.addAttribute("exchangeRates", currencyService.getExchangeRates());
        return "exchange-rates-view";
    }

    @GetMapping({ "/", "/index" })
    public String index(Model model,
            @CookieValue(value = "preferredCurrency", defaultValue = "EUR") String preferredCurrency) {

        model.addAttribute("depart", service.getDepartures());
        model.addAttribute("arrive", service.getArrivals());
        model.addAttribute("preferredCurrency", preferredCurrency);
        model.addAttribute("currency", currencyService.getSupportedCurrencies());
        return "index";
    }

    // muda moeda
    @PostMapping("/currency")
    public String chooseCurrency(@RequestParam("currency") String currency, HttpServletResponse response) {
        Cookie cookie = new Cookie("preferredCurrency", currency);
        cookie.setMaxAge(365 * 24 * 60 * 60);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return "redirect:/index";
    }

    // resultado de viagens
    @PostMapping("/result")
    public String processForm(@RequestParam("depart") String departureCity,
            @RequestParam("arrive") String arrivalCity,
            Model model, @CookieValue(value = "preferredCurrency", defaultValue = "EUR") String preferredCurrency) {
        List<Trip> trips = service.getTripsByDepartAndArrive(departureCity, arrivalCity);
        model.addAttribute("trips", currencyService.convertTripsPrice(trips, preferredCurrency));
        model.addAttribute("preferredCurrency", preferredCurrency);
        model.addAttribute("currency", currencyService.getSupportedCurrencies());
        return "trips-result";
    }

    // comprar de bilhete
    @PostMapping("/buyticket")
    public String confirmTicket(@RequestParam("tripId") String tripId, Model model, @CookieValue(value = "preferredCurrency", defaultValue = "EUR") String preferredCurrency){
        Trip trip = service.getTripById(Long.parseLong(tripId));
        model.addAttribute("trip", currencyService.convertTripPrice(trip, preferredCurrency));
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("preferredCurrency", preferredCurrency);
        return "buyticket";
    }

    // confirmar bilhete
    @PostMapping("/confirm")
    public String processTicket(@ModelAttribute("ticket") Ticket ticket, @RequestParam("tripId") Long tripId,
            @RequestParam("creditCardMonth") int creditCardMonth,
            @RequestParam("creditCardYear") int creditCardYear) {
        ticket.setTrip(service.getTripById(tripId));
        ticket.setCreditCardDate(creditCardYear, creditCardMonth);
        service.saveTicket(ticket);
        return "redirect:/index";
    }

    // @GetMapping("/trips")
    // public String showTrips(Model model) {
    // model.addAttribute("trips", service.getAllTrips());
    // return "trips";
    // }

    //credencial to access ticket
    @GetMapping("/acessticket")
    public String accessTicket(){
        return "access-ticket";
    }

    @PostMapping("/ticket")
    public String showTicket(@RequestParam("ticketId") Long ticketId, @RequestParam("token") String token, Model model){
        Ticket ticket = service.accessTicket(ticketId, token);
        if (ticket != null) {
            model.addAttribute("ticket", ticket);
            return "ticket";
        }
        return "access-ticket";
    }

    @GetMapping("/tickets")
    public String showTickets(Model model) {
        model.addAttribute("tickets", service.getAllTickets());
        return "tickets";
    }

}
