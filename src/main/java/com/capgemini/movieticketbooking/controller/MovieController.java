package com.capgemini.movieticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.capgemini.movieticketbooking.exception.MovieNotFoundException;
import com.capgemini.movieticketbooking.model.Movie;
import com.capgemini.movieticketbooking.service.MovieService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/")
    public ResponseEntity<String> addMovie(@Valid @RequestBody Movie movie) {
        movieService.addMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{movie_id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("movie_id") int movieId) throws MovieNotFoundException {
        Movie movie = movieService.getMovieById(movieId);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/movie/{movie_name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("movie_name") String movieName) throws MovieNotFoundException {
        Movie movie = movieService.getMovieByName(movieName);
        return ResponseEntity.ok(movie);
    }

    @DeleteMapping("/{movie_id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable("movie_id") int movieId) throws MovieNotFoundException {
        movieService.deleteMovieById(movieId);
        return ResponseEntity.ok("Record Deleted Successfully");
    }

    @PutMapping("/{movie_id}")
    public ResponseEntity<String> updateMovieById(@PathVariable("movie_id") int movieId, @Valid @RequestBody Movie movie) throws MovieNotFoundException {
        movieService.updateMovieById(movieId, movie);
        return ResponseEntity.ok("Record Updated Successfully");
    }

}
