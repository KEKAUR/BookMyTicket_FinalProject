package com.capgemini.movieticketbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.movieticketbooking.model.Seats;

@Repository
public interface SeatsRepository extends JpaRepository<Seats, Integer>{

	List<Seats> findByTheatre_TheatreId(int theatreId);

}
