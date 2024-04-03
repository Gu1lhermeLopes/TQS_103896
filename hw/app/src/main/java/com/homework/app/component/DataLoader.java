package com.homework.app.component;

import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.homework.app.model.Trip;
import com.homework.app.repository.TripRepo;

@Component
public class DataLoader implements CommandLineRunner {

    private final TripRepo repo;

    public DataLoader(TripRepo repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception {
                List<String> cities = List.of("Porto", "Fatima", "Lisboa", "Faro", "Beja", "Braga", "Setubal");
                List<String> companies = List.of("FlixBus", "Rede Expresso", "Renex", "Transdev");

        for (int i = 0; i < cities.size(); i++) {
            for (int j = 0; j < cities.size(); j++) {
                if (i != j) {

                    int x = randomNumber(1, 5);

                    for (int k = 0; k < x; k++) {
                        Trip trip = new Trip();
                        trip.setDepart(cities.get(i));
                        trip.setArrive(cities.get(j));
                        trip.setCompany(companies.get(randomNumber(0, companies.size() - 1)));
                        trip.setDepartTime("10:00");
                        trip.setArriveTime("12:00");
                        trip.setPrice(randomNumber(10, 100));
                        repo.save(trip);
                    }
                }
            }
        }
    }

    private int randomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }





}