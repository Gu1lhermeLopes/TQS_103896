package com.homework.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.homework.app.repository.TicketRepo;
import com.homework.app.repository.TripRepo;

import jakarta.persistence.EntityNotFoundException;

import com.homework.app.model.Ticket;
import com.homework.app.model.Trip;

import java.util.List;
import java.util.Optional;

@Configuration
@Service
public class ApiService {

    private final TicketRepo ticketRepo;

    private final TripRepo tripRepo;

    @Autowired
    public ApiService(TicketRepo ticketRepo, TripRepo tripRepo) {
        this.ticketRepo = ticketRepo;
        this.tripRepo = tripRepo;
    }


    public Ticket getTicketById(Long id) {
        Optional<Ticket> ticketOptional = ticketRepo.findById(id);
        if (ticketOptional.isPresent()) {
            return ticketOptional.get();
        }
        return null;
    }


    public Ticket accessTicket(Long id, String token) {
        Ticket ticket = getTicketById(id);
        if (ticket != null && ticket.getAcessToken().equals(token)) {
            return ticket;
        }
        return null;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }

    public List<Trip> getAllTrips() {
        return tripRepo.findAll();
    }

    public void saveTicket(Ticket ticket) {
        
        if (ticket.getTrip().isAvailable()) {
            ticket.isPaid();
            ticket.getTrip().bookSeat();
            ticketRepo.save(ticket);
            tripRepo.save(ticket.getTrip());
        }
    }

    public List<String> getDepartures() {
        return tripRepo.findDistinctDepartCities();
    }

    public List<String> getArrivals() {
        return tripRepo.findDistinctArriveCities();
    }

    public List<Trip> getTripsByDepartAndArrive(String depart, String arrive) {
        return tripRepo.findByDepartAndArrive(depart, arrive);
    }

    public void saveTrip(Trip trip) {
        tripRepo.save(trip);
    }

    public Trip getTripById(Long id) {
        Optional<Trip> tripOptional = tripRepo.findById(id);
        return tripOptional.orElseThrow(() -> new EntityNotFoundException("Trip not found with ID: " + id));
    }
}
