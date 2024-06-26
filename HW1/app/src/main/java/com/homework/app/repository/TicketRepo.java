package com.homework.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homework.app.model.Ticket;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long>{
    
}
