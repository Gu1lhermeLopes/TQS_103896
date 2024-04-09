package com.homework.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.homework.app.model.Ticket;
import com.homework.app.model.Trip;
import com.homework.app.repository.TicketRepo;
import com.homework.app.repository.TripRepo;
import com.homework.app.service.ApiService;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ApiServiceTests {

    @Mock
    private TicketRepo ticketRepo;

    @Mock
    private TripRepo tripRepo;

    @InjectMocks
    private ApiService apiService;

    @Test
    void testGetTicketById() {
        Trip trip = new Trip();
        trip.setTotalSeats(2);
        Ticket ticket = new Ticket();
        ticket.setTrip(trip);
        apiService.saveTicket(ticket);

        when(ticketRepo.findById(ticket.getId())).thenReturn(Optional.of(ticket));

        Ticket retrievedTicket = apiService.getTicketById(ticket.getId());

        assertEquals(ticket.getId(), retrievedTicket.getId());
    }

    @Test
    void testAccessTicket() {
        Trip trip = new Trip();
        trip.setTotalSeats(2);
        Ticket ticket = new Ticket();
        ticket.setTrip(trip);

        apiService.saveTicket(ticket);

        when(ticketRepo.findById(ticket.getId())).thenReturn(Optional.of(ticket));

        Ticket retrievedTicket = apiService.accessTicket(ticket.getId(), ticket.getAcessToken());

        assertEquals(ticket.getId(), retrievedTicket.getId());
    }

    @Test
    void testGetTripsByDepartAndArrive() {
        Trip trip1 = new Trip();
        trip1.setDepart("Lisboa");
        trip1.setArrive("Porto");
        apiService.saveTrip(trip1);

        Trip trip2 = new Trip();
        trip1.setDepart("Lisboa");
        trip1.setArrive("Porto");
        apiService.saveTrip(trip2);

        Trip trip3 = new Trip();
        trip1.setDepart("Porto");
        trip1.setArrive("Lisboa");
        apiService.saveTrip(trip3);

        when(tripRepo.findByDepartAndArrive("Lisbon", "Porto")).thenReturn(List.of(trip1, trip2));

        List<Trip> retrievedTrip = apiService.getTripsByDepartAndArrive("Lisbon", "Porto");

        assertEquals(2, retrievedTrip.size());
    }

    @Test
    void testGetDeparturesAndArrivalsCities() {
        Trip trip1 = new Trip();
        trip1.setDepart("Lisboa");
        trip1.setArrive("Porto");
        apiService.saveTrip(trip1);

        Trip trip2 = new Trip();
        trip1.setDepart("Lisboa");
        trip1.setArrive("Porto");
        apiService.saveTrip(trip2);

        Trip trip3 = new Trip();
        trip1.setDepart("Braga");
        trip1.setArrive("Lisboa");
        apiService.saveTrip(trip3);

        when(tripRepo.findDistinctDepartCities()).thenReturn(List.of("Lisboa", "Braga"));
        List<String> retrievedDepartures = apiService.getDepartures();
        assertThat(retrievedDepartures).hasSize(2).contains("Lisboa", "Braga");

        when(tripRepo.findDistinctArriveCities()).thenReturn(List.of("Porto", "Lisboa"));
        List<String> retrievedArrivals = apiService.getArrivals();
        assertThat(retrievedArrivals).hasSize(2).contains("Porto", "Lisboa");

    }

    @Test
    void testGetTripById() {
        Trip trip = new Trip();
        apiService.saveTrip(trip);

        when(tripRepo.findById(trip.getId())).thenReturn(Optional.of(trip));

        Trip retrievedTrip = apiService.getTripById(trip.getId());

        assertEquals(trip.getId(), retrievedTrip.getId());

    }
}
