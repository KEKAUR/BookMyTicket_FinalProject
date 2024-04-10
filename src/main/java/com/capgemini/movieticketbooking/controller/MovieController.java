package com.capgemini.movieticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.movieticketbooking.exception.MovieNotFoundException;
import com.capgemini.movieticketbooking.model.Movie;
import com.capgemini.movieticketbooking.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        try {
            movieService.addMovie(movie);
            return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create record");
        }
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        try {
            List<Movie> movies = movieService.getAllMovies();
            return ResponseEntity.ok(movies);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{movie_id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("movie_id") int movieId) throws MovieNotFoundException {
        try {
            Movie movie = movieService.getMovieById(movieId);
            return ResponseEntity.ok(movie);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/movie/{movie_name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("movie_name") String movieName) throws MovieNotFoundException {
        try {
            Movie movie = movieService.getMovieByName(movieName);
            return ResponseEntity.ok(movie);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{movie_id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable("movie_id") int movieId) throws MovieNotFoundException {
        try {
            movieService.deleteMovieById(movieId);
            return ResponseEntity.ok("Record Deleted Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete record");
        }
    }

    @PutMapping("/{movie_id}")
    public ResponseEntity<String> updateMovieById(@PathVariable("movie_id") int movieId, @RequestBody Movie movie) throws MovieNotFoundException {
        try {
            movieService.updateMovieById(movieId, movie);
            return ResponseEntity.ok("Record Updated Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update record");
        }
    }
}
