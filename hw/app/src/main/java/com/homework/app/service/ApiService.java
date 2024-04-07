package com.homework.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.homework.app.repository.TicketRepo;
import com.homework.app.repository.TripRepo;

import com.homework.app.model.Ticket;
import com.homework.app.model.Trip;

import java.util.List;
import java.util.Optional;

@Configuration
@Service
public class ApiService {

    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private TripRepo tripRepo;

    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }

    public List<Trip> getAllTrips() {
        return tripRepo.findAll();
    }

    public void saveTicket(Ticket ticket) {
        
        if (ticket.getTrip().isAvailable()) {
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
        if (tripOptional.isPresent()) {
            return tripOptional.get();
        }
        return null;
    }
}
