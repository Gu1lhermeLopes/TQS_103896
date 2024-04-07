package com.homework.app.controller;

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

    @Autowired
    private ApiService service;
    
    @Autowired
    private CurrencyService currencyService;

    @GetMapping({ "/", "/index"})
    public String index(Model model, @CookieValue(value = "preferredCurrency", defaultValue = "EUR") String preferredCurrency){


        model.addAttribute("depart", service.getDepartures());
        model.addAttribute("arrive", service.getArrivals());
        model.addAttribute("preferredCurrency", preferredCurrency);
        model.addAttribute("currency", currencyService.getSupportedCurrencies());
        return "index";
    }
    //muda coeda
    @PostMapping("/currency")
    public String chooseCurrency(@RequestParam("currency") String currency, HttpServletResponse response) {
        // Armazenar a moeda preferida do usuário
        Cookie cookie = new Cookie("preferredCurrency", currency);
        cookie.setMaxAge(365 * 24 * 60 * 60);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return "redirect:/index";
    }
    

    //pesquisa de viagens
    @PostMapping("/result")
    public String processForm(@RequestParam("depart") String departureCity,
            @RequestParam("arrive") String arrivalCity,
            Model model) {
        model.addAttribute("trips", service.getTripsByDepartAndArrive(departureCity, arrivalCity));
        return "trips-result";
    }

    // chamado no newticket.html
    @PostMapping("/save")
    public String saveTicket(@ModelAttribute("ticket") Ticket ticket) {
        service.saveTicket(ticket);

        return "redirect:/index";
    }

    @GetMapping("/trips")
    public String showTrips(Model model) {
        model.addAttribute("trips", service.getAllTrips());
        return "trips";
    }

    @GetMapping("/tickets")
    public String showTickets(Model model) {
        model.addAttribute("tickets", service.getAllTickets());
        return "tickets";
    }



    @PostMapping("/buyticket")
    public String confirmTicket(@RequestParam("tripId") String tripId, Model model) {
        Trip trip = service.getTripById(Long.parseLong(tripId));
        model.addAttribute("trip", trip);
        model.addAttribute("ticket", new Ticket());
        return "buyticket";
    }

    @PostMapping("/confirm")
    public String processTicket(@ModelAttribute("ticket") Ticket ticket, @RequestParam("tripId") Long tripId,
            @RequestParam("creditCardMonth") int creditCardMonth,
            @RequestParam("creditCardYear") int creditCardYear) {
        ticket.setTrip(service.getTripById(tripId));
        ticket.setCreditCardDate(creditCardYear, creditCardMonth);
        service.saveTicket(ticket);
        return "redirect:/index";
    }
}
