package com.capgemini.movieticketbooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.movieticketbooking.exception.MovieNotFoundException;
import com.capgemini.movieticketbooking.model.Movie;

@Service
public interface MovieService {

	void addMovie(Movie movie);

	List<Movie> getAllMovies();

	Movie getMovieById(int movieId) throws MovieNotFoundException;

	Movie getMovieByName(String movieName) throws MovieNotFoundException;

	void deleteMovieById(int movieId) throws MovieNotFoundException;

	void updateMovieById(int movieId, Movie movie) throws MovieNotFoundException;

}
