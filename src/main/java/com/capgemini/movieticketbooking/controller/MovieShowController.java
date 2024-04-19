package com.capgemini.movieticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.capgemini.movieticketbooking.dto.MovieShowDTO;
import com.capgemini.movieticketbooking.dto.SeatDTO;
import com.capgemini.movieticketbooking.exception.MovieNotFoundException;
import com.capgemini.movieticketbooking.exception.ShowNotFoundException;
import com.capgemini.movieticketbooking.exception.TheatreNotFoundException;
import com.capgemini.movieticketbooking.model.MovieShow;
import com.capgemini.movieticketbooking.model.Seats;
import com.capgemini.movieticketbooking.service.ShowService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/")
    public ResponseEntity<String> addShow(@Valid @RequestBody MovieShowDTO show) throws MovieNotFoundException, TheatreNotFoundException {
        showService.addShow(show);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
    }

    @GetMapping
    public ResponseEntity<List<MovieShow>> getAllShows() throws ShowNotFoundException {
        List<MovieShow> shows = showService.getAllShows();
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/{show_id}")
    public ResponseEntity<?> getShowById(@PathVariable("show_id") int showId) throws ShowNotFoundException {
        MovieShow show = showService.getShowById(showId);
        return ResponseEntity.ok(show);
    }

    @PutMapping("/{show_id}")
    public ResponseEntity<String> updateShowById(@PathVariable("show_id") int showId, @Valid @RequestBody MovieShow show) throws ShowNotFoundException {
        showService.updateShowById(showId, show);
        return ResponseEntity.ok("Record Updated Successfully");
    }

    @DeleteMapping("/{show_id}")
    public ResponseEntity<String> deleteShowById(@PathVariable("show_id") int showId) throws ShowNotFoundException {
        showService.deleteShowById(showId);
        return ResponseEntity.ok("Record Deleted Successfully");
    }

    @GetMapping("/movie/{movie_name}")
    public ResponseEntity<List<MovieShow>> getShowsByMovieName(@PathVariable("movie_name") String movieName) throws ShowNotFoundException {
        List<MovieShow> shows = showService.getShowsByMovieName(movieName);
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/movie/{movie_name}/city/{city}")
    public ResponseEntity<List<MovieShow>> getShowsByMovieNameAndCity(@PathVariable("movie_name") String movieName, @PathVariable("city") String city) throws ShowNotFoundException {
        List<MovieShow> shows = showService.getShowsByMovieNameAndCity(movieName, city);
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/theatre/{theatre_name}")
    public ResponseEntity<List<MovieShow>> getShowsByTheatreName(@PathVariable("theatre_name") String theatreName) throws ShowNotFoundException {
        List<MovieShow> shows = showService.getShowsByTheatreName(theatreName);
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/movieid/{movie_id}/city/{city}")
    public ResponseEntity<List<MovieShow>> getShowsByMovieIdAndCity(@PathVariable("movie_id") int movieId, @PathVariable("city") String city) throws ShowNotFoundException {
        List<MovieShow> shows = showService.getShowsByMovieIdAndCity(movieId, city);
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/theatre-id/{theatre_id}")
    public ResponseEntity<List<MovieShow>> getShowsByTheatreId(@PathVariable("theatre_id") int theatreId) {
        List<MovieShow> shows = showService.getShowsByTheatreId(theatreId);
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/seats/{show_id}")
    public ResponseEntity<List<Seats>> getAvailableSeats(@PathVariable("show_id") int showId) throws ShowNotFoundException, TheatreNotFoundException {
        return new ResponseEntity<>(showService.getAvailableSeats(showId), HttpStatus.OK);
    }

    @GetMapping("/seats/all/{show_id}")
    public ResponseEntity<List<SeatDTO>> getSeatDetails(@PathVariable("show_id") int showId) throws ShowNotFoundException, TheatreNotFoundException {
        return new ResponseEntity<>(showService.getSeatDetails(showId), HttpStatus.OK);
    }
    
}
