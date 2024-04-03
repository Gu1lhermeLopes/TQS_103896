package com.homework.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.homework.app.model.*;
import com.homework.app.service.ApiService;


@Controller
public class ExpressController {

    @Autowired
    private ApiService service;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("depart", service.getDepartures());
        model.addAttribute("arrive", service.getArrivals());

        return "index";
    }

    //chamado no index.html
    @GetMapping("/addticket")
    public String showSignUpForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "newticket";
    }

    //chamado no newticket.html
    @PostMapping("/save")
    public String saveTicket(@ModelAttribute("ticket") Ticket ticket){
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
    
    @PostMapping("/result")
    public String processForm(@RequestParam("depart") String departureCity,
                              @RequestParam("arrive") String arrivalCity,
                              Model model) {
        
        model.addAttribute("trips",  service.getTripsByDepartAndArrive(departureCity, arrivalCity));
              
        return "trips-result";
    }   
    
    @PostMapping("/buyticket")
    public String confirmTicket(@RequestParam("tripId") String tripId, Model model) {
        Trip trip = service.getTripById(Long.parseLong(tripId));
        model.addAttribute("trip", trip);
        model.addAttribute("ticket", new Ticket());
        return "buyticket";
    }

    @PostMapping("/confirm")
    public String processTicket(@ModelAttribute("ticket") Ticket ticket, @RequestParam("tripId") Long tripId) {
        ticket.setTrip(service.getTripById(tripId));
        service.saveTicket(ticket);
        return "redirect:/index";
    }
}
