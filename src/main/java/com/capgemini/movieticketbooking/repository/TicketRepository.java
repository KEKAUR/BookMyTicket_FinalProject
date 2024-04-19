package com.capgemini.movieticketbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.movieticketbooking.model.MovieShow;
import com.capgemini.movieticketbooking.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{

	List<Ticket> findByMovieShow(MovieShow show);

	List<Ticket> findByMovieShowShowId(int showId);

}
