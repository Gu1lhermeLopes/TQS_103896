package com.homework.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.homework.app.model.Trip;

public class TripUnitTests {


    // Tests Trip
    @Test
    public void set_negative_seats_Test(){
        Trip trip = new Trip();
        assertFalse(trip.setTotalSeats(-1));
        assertFalse(trip.bookSeat());
    }

    @Test
    public void unavailable_when_full_Test(){
        Trip trip = new Trip();
        trip.setTotalSeats(1);
        trip.bookSeat();
        assertFalse(trip.isAvailable());
    }

    @Test
    public void avaible_seats_logic_Test(){
        Trip trip = new Trip();
        trip.setTotalSeats(1);
        assertEquals(trip.getTotalSeats(), trip.getAvaibleSeats());
        trip.bookSeat();
        assertEquals(0, trip.getAvaibleSeats());
    }

    @Test
    public void book_full_Test(){
        Trip trip = new Trip();
        trip.setTotalSeats(1);
        assertTrue(trip.bookSeat());
        assertFalse(trip.bookSeat());
    }
    
}
