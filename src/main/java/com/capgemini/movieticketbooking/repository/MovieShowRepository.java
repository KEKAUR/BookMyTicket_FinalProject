package com.capgemini.movieticketbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.movieticketbooking.model.MovieShow;

@Repository
public interface MovieShowRepository extends JpaRepository<MovieShow, Integer>{
	
	List<MovieShow> findByMovieMovieName(String movieName);
    
	List<MovieShow> findByMovieMovieNameAndTheatreAddressCity(String movieName, String city);

	List<MovieShow> findByTheatreTheatreName(String theatreName);

	List<MovieShow> findByMovieMovieNameAndTheatreAddressState(String movieName, String state);

	List<MovieShow> findByTheatreTheatreId(int theatreId);

	List<MovieShow> findByMovieMovieIdAndTheatreAddressCity(int movieId, String city);

}
