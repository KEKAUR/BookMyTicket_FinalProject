package com.capgemini.movieticketbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.movieticketbooking.model.MovieShow;

public interface MovieShowRepository extends JpaRepository<MovieShow, Integer>{
	
   // @Query("SELECT ms FROM MovieShow ms WHERE ms.movieName = ?1")
	@Query("SELECT ms FROM MovieShow ms JOIN ms.movie m WHERE m.movieName = :movieName")
	List<MovieShow> findByMovieName(String movieName);
    
	//@Query("SELECT ms FROM MovieShow ms WHERE ms.theatre.address.addressName = :addressName")
	@Query("SELECT ms FROM MovieShow ms JOIN ms.movie m JOIN ms.theatre t JOIN t.address a WHERE m.movieName = :movieName AND a.city = :city")
	List<MovieShow> findByMovieNameAndCity(String movieName, String city);

	//@Query("SELECT ms FROM MovieShow ms WHERE ms.theatre.theatreName = ?1")
    @Query("SELECT ms FROM MovieShow ms JOIN ms.theatre t WHERE t.theatreName = :theatreName")
	List<MovieShow> findByTheatreName(String theatreName);
    @Query("SELECT ms FROM MovieShow ms INNER JOIN ms.tickets t INNER JOIN t.seats s WHERE s.seatId = :seatId")
    MovieShow findBySeatId(@Param("seatId") int seatId);
	
//	//@Query("SELECT ms FROM MovieShow ms WHERE ms.movieName = :movieName AND ms.theatre.address.state = :state\r\n"
//			+ "")
//@Query("SELECT ms FROM MovieShow ms JOIN ms.movie m JOIN ms.theatre t WHERE m.movieName = :movieName AND t.theatreName = :theatreName")
//	List<MovieShow> findByMovieNameAndState(String movieName, String state);

}
