package com.capgemini.movieticketbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.movieticketbooking.model.Seats;

public interface SeatsRepository extends JpaRepository<Seats, Integer>{

	List<Seats> findByTheatre_TheatreId(int theatreId);

}
