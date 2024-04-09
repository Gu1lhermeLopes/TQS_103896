package com.homework.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.homework.app.model.Trip;

@Repository
public interface TripRepo extends JpaRepository<Trip, Long>{
    @Query("SELECT DISTINCT t.depart FROM Trip t")
    List<String> findDistinctDepartCities();

    @Query("SELECT DISTINCT t.arrive FROM Trip t")
    List<String> findDistinctArriveCities();

    List<Trip> findByDepartAndArrive(String depart, String arrive);
}
