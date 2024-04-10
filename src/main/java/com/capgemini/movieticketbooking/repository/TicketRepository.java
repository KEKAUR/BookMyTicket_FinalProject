package com.capgemini.movieticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.movieticketbooking.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

}
