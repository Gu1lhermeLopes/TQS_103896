package com.homework.app.model;

import java.security.SecureRandom;
import java.time.Year;
import java.time.YearMonth;

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
    private String address;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;


    private String creditCardName;
    private String creditCardId;
    private YearMonth creditCardDate;
    private int creditCardCvv;

    private String currency;
    private double userprice;

    private boolean payment;
    private String acessToken;

    public Ticket() {
        this.payment = false;
    }

    public String getAcessToken() {
        return acessToken;
    }

    public void generateToken() {
        SecureRandom random = new SecureRandom();
        StringBuilder token = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int randomNum = random.nextInt(26);
            char randomChar = (char) ('A' + randomNum);
            token.append(randomChar);
        }
        this.acessToken = token.toString();
    }


    public boolean getPayment() {
        return payment;
    }

    public void isPaid() {
        this.payment = true;
        this.generateToken();
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

    public boolean setNif(String nif) {

        if (!nif.matches("\\d{9}")) {
            throw new IllegalArgumentException("O NIF deve ser composto por 9 dígitos.");
        }
        this.nif = nif;
        return true;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
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

    public boolean setCreditCardId(String creditCardId) {

        if (!creditCardId.matches("\\d{4} \\d{4} \\d{4} \\d{4}")) {
            throw new IllegalArgumentException("O número do cartão de crédito deve ter o formato 0000 0000 0000 0000.");
        }
        this.creditCardId = creditCardId;
        return true;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    //CreditCardDate
    public YearMonth getCreditCardDate() {
        return creditCardDate;
    }
    public boolean setCreditCardDate(int creditCardYear, int creditCardMonth) {
        if (creditCardYear < Year.now().getValue()) {
            throw new IllegalArgumentException("O ano do cartão de crédito não pode ser menor que o ano atual.");
        }
        if (creditCardMonth < 1 || creditCardMonth > 12) {
            throw new IllegalArgumentException("O mês do cartão de crédito deve ser um número entre 1 e 12.");
        }
        if (YearMonth.of(creditCardYear, creditCardMonth).isBefore(YearMonth.now())) {
            throw new IllegalArgumentException("A data do cartão de crédito não pode ser anterior à data atual.");
        }
        this.creditCardDate = YearMonth.of(creditCardYear, creditCardMonth);
        return true;
    }



    //CVV
    public int getCreditCardCvv() {
        return creditCardCvv;
    }

    public boolean setCreditCardCvv(int creditCardCvv) {
        if (creditCardCvv < 0) {
            throw new IllegalArgumentException("O CVV do cartão de crédito não pode ser negativo.");
        }
        if (String.valueOf(creditCardCvv).length() != 3) {
            throw new IllegalArgumentException("O CVV do cartão de crédito deve ter exatamente três números.");
        }
        this.creditCardCvv = creditCardCvv;
        return true;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getUserprice() {
        return userprice;
    }

    public void setUserprice(double userprice) {
        this.userprice = userprice;
    }


    





    


    


    
}
