package com.homework.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String company;

    

    private String depart;
    private String arrive;

    private String departTime;
    private String arriveTime;

    private double price;

    private int totalSeats;

    private int avaibleSeats;

    private boolean isAvailable;

    public Trip() {
        isAvailable = false;
    }


    public boolean bookSeat() {
        if (isAvailable()){
            setAvaibleSeats(getAvaibleSeats() - 1);
            setAvailable();
            return true;
        }
        return false;
    }



    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public boolean setTotalSeats(int totalSeats) {
        if (totalSeats > 0) {
            this.totalSeats = totalSeats;
            setAvaibleSeats(totalSeats);
            setAvailable();
            return true;
        }
        return false;

    }

    public int getAvaibleSeats() {
        return avaibleSeats;
    }

    public void setAvaibleSeats(int avaibleSeats) {
        this.avaibleSeats = avaibleSeats;
    }

    private void setAvailable() {
        if (getAvaibleSeats() > 0) {
            isAvailable = true;
        } else {
            isAvailable = false;
        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }

}
