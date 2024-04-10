package com.capgemini.movieticketbooking.service;

import java.util.List;

import com.capgemini.movieticketbooking.exception.ShowNotFoundException;
import com.capgemini.movieticketbooking.model.MovieShow;

public interface ShowService {

	void addShow(MovieShow show);

	List<MovieShow> getAllShows() throws ShowNotFoundException;

	MovieShow getShowById(int showId) throws ShowNotFoundException;
	MovieShow getSeatId(int seatId) throws ShowNotFoundException;

	void updateShowById(int showId, MovieShow show) throws ShowNotFoundException;

	List<MovieShow> getShowsByMovieNameAndCity(String movieName, String city) throws ShowNotFoundException;

	List<MovieShow> getShowsByTheatreName(String theatreName) throws ShowNotFoundException;

//	List<MovieShow> getShowsByMovieNameAndState(String movieName, String state) throws ShowNotFoundException;

	List<MovieShow> getShowsByMovieName(String movieName) throws ShowNotFoundException;

	void deleteShowById(int showId) throws ShowNotFoundException;

}
