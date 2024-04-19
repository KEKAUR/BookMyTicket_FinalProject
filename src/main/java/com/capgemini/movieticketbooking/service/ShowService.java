package com.capgemini.movieticketbooking.service;

import java.util.List;

import com.capgemini.movieticketbooking.dto.MovieShowDTO;
import com.capgemini.movieticketbooking.dto.SeatDTO;
import com.capgemini.movieticketbooking.exception.MovieNotFoundException;
import com.capgemini.movieticketbooking.exception.ShowNotFoundException;
import com.capgemini.movieticketbooking.exception.TheatreNotFoundException;
import com.capgemini.movieticketbooking.model.MovieShow;
import com.capgemini.movieticketbooking.model.Seats;

public interface ShowService {

	void addShow(MovieShow show);

	List<MovieShow> getAllShows() throws ShowNotFoundException;

	MovieShow getShowById(int showId) throws ShowNotFoundException;

	void updateShowById(int showId, MovieShow show) throws ShowNotFoundException;

	List<MovieShow> getShowsByMovieNameAndCity(String movieName, String city) throws ShowNotFoundException;

	List<MovieShow> getShowsByTheatreName(String theatreName) throws ShowNotFoundException;

	List<MovieShow> getShowsByMovieName(String movieName) throws ShowNotFoundException;

	void deleteShowById(int showId) throws ShowNotFoundException;

	List<MovieShow> getShowsByTheatreId(int theatreId);
	
	List<Seats> getAvailableSeats(int showId) throws ShowNotFoundException, TheatreNotFoundException;

	MovieShow addShow(MovieShowDTO show) throws MovieNotFoundException, TheatreNotFoundException;

	List<SeatDTO> getSeatDetails(int showId)throws ShowNotFoundException, TheatreNotFoundException;

	List<MovieShow> getShowsByMovieIdAndCity(int movieId, String city) throws ShowNotFoundException;
}
