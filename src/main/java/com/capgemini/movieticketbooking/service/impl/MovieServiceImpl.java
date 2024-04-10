package com.capgemini.movieticketbooking.service.impl;
 
import com.capgemini.movieticketbooking.exception.MovieNotFoundException;
import com.capgemini.movieticketbooking.model.Movie;
import com.capgemini.movieticketbooking.repository.MovieRepository;
import com.capgemini.movieticketbooking.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
 
@Service
public class MovieServiceImpl implements MovieService{
 
    @Autowired
    private MovieRepository movieRepository;
 
    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }
 
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
 
    public Movie getMovieById(int movieId) throws MovieNotFoundException {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with ID: " + movieId));
    }
 
    public Movie getMovieByName(String movieName) throws MovieNotFoundException {
        return movieRepository.findByNameIgnoreCase(movieName)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with Name: " + movieName));
    }
 
    public void deleteMovieById(int movieId) throws MovieNotFoundException {
        if (!movieRepository.existsById(movieId)) {
            throw new MovieNotFoundException("Movie not found with ID: " + movieId);
        }
        movieRepository.deleteById(movieId);
    }
 
    public void updateMovieById(int movieId, Movie updatedMovie) throws MovieNotFoundException {
        if (!movieRepository.existsById(movieId)) {
            throw new MovieNotFoundException("Movie not found with ID: " + movieId);
        }
        updatedMovie.setMovieId(movieId);
        movieRepository.save(updatedMovie);
    }
}

