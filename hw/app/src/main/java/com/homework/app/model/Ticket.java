package com.homework.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String nif;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;


    private Double price;

    private String address;

    private String creditCardId;
    private String creditCardName;
    private int creditCardMonth;
    private int creditCardYear;
    private int creditCardCvv;

    public Ticket() {
    }

    public Ticket(String name, String nif, Trip trip, Double price, String address, String creditCardId, String creditCardName, int creditCardMonth, int creditCardYear, int creditCardCvv) {
        this.name = name;//
        this.nif = nif;//
        this.trip = trip;
        this.price = price;
        this.address = address;
        this.creditCardId = creditCardId;
        this.creditCardName = creditCardName;
        this.creditCardMonth = creditCardMonth;
        this.creditCardYear = creditCardYear;
        this.creditCardCvv = creditCardCvv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(String creditCardId) {
        this.creditCardId = creditCardId;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public int getCreditCardMonth() {
        return creditCardMonth;
    }

    public void setCreditCardMonth(int creditCardMonth) {
        this.creditCardMonth = creditCardMonth;
    }

    public int getCreditCardYear() {
        return creditCardYear;
    }

    public void setCreditCardYear(int creditCardYear) {
        this.creditCardYear = creditCardYear;
    }

    public int getCreditCardCvv() {
        return creditCardCvv;
    }

    public void setCreditCardCvv(int creditCardCvv) {
        this.creditCardCvv = creditCardCvv;
    }


    





    


    


    
}
