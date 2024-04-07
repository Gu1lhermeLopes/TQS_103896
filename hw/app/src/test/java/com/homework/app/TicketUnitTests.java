package com.homework.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.YearMonth;

import com.homework.app.model.*;

public class TicketUnitTests {


    // Tests CreditCard id
    @Test
    public void set_invalid_id_Test(){
        Ticket t = new Ticket();
        assertThrows(IllegalArgumentException.class, () -> {
            t.setCreditCardId("123q 5678 1234 5678");
        });
    }

    @Test
    public void set_valid_id_Test(){
        Ticket t = new Ticket();
        t.setCreditCardId("1234 1234 1234 1234");
        assertEquals("1234 1234 1234 1234", t.getCreditCardId());
    }
        

    // Tests NIF
    @Test
    public void set_invalid_nif_Test(){
        Ticket t = new Ticket();
        assertThrows(IllegalArgumentException.class, () -> {
            t.setNif("1234567890");
        });
    }
    @Test
    public void set_valid_nif_Test(){
        Ticket t = new Ticket();
        t.setNif("123456789");
        assertEquals("123456789", t.getNif());
    }


    // Tests CreditCard date
    @Test
    public void set_invalid_date_Test(){
        Ticket t = new Ticket();

        assertThrows(IllegalArgumentException.class, () -> {
            t.setCreditCardDate(2024,1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            t.setCreditCardDate(2025,13);
        });
    }

    @Test
    public void set_valid_date_Test(){
        Ticket t = new Ticket();
        t.setCreditCardDate(2024,4);
        assertEquals(t.getCreditCardDate(), YearMonth.of(2024,4));
    }

    
    // Tests CreditCard ccv
    @Test
    public void set_negative_ccv_Test(){
        Ticket t = new Ticket();
        assertThrows(IllegalArgumentException.class, () -> {
            t.setCreditCardCvv(-1);
        });
    }
    @Test
    public void set_non3_ccv_Test(){
        Ticket t = new Ticket();
        assertThrows(IllegalArgumentException.class, () -> {
            t.setCreditCardCvv(41);
        });
    }
    @Test
    public void set_valid_ccv_Test(){
        Ticket t = new Ticket();
        t.setCreditCardCvv(123);
        assertEquals(123, t.getCreditCardCvv());
    }

    
}
