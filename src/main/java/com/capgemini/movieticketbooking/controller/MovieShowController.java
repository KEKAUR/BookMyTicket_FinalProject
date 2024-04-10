package com.capgemini.movieticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.movieticketbooking.exception.ShowNotFoundException;
import com.capgemini.movieticketbooking.model.MovieShow;
import com.capgemini.movieticketbooking.service.ShowService;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class MovieShowController {

    @Autowired
    private ShowService showService;

    @PostMapping
    public ResponseEntity<String> addShow(@RequestBody MovieShow show) {
        try {
            showService.addShow(show);
            return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create record");
        }
    }

    @GetMapping
    public ResponseEntity<List<MovieShow>> getAllShows() {
        try {
            List<MovieShow> shows = showService.getAllShows();
            return ResponseEntity.ok(shows);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{show_id}")
    public ResponseEntity<MovieShow> getShowById(@PathVariable("show_id") int showId) throws ShowNotFoundException {
        try {
            MovieShow show = showService.getShowById(showId);
            return ResponseEntity.ok(show);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{show_id}")
    public ResponseEntity<String> updateShowById(@PathVariable("show_id") int showId, @RequestBody MovieShow show) throws ShowNotFoundException {
        try {
            showService.updateShowById(showId, show);
            return ResponseEntity.ok("Record Updated Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update record");
        }
    }

    @DeleteMapping("/{show_id}")
    public ResponseEntity<String> deleteShowById(@PathVariable("show_id") int showId) throws ShowNotFoundException {
        try {
            showService.deleteShowById(showId);
            return ResponseEntity.ok("Record Deleted Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete record");
        }
    }

    @GetMapping("/movie/{movie_name}")
    public ResponseEntity<List<MovieShow>> getShowsByMovieName(@PathVariable("movie_name") String movieName) {
        try {
            List<MovieShow> shows = showService.getShowsByMovieName(movieName);
            return ResponseEntity.ok(shows);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/movie/{movie_name}/city/{city}")
    public ResponseEntity<List<MovieShow>> getShowsByMovieNameAndCity(@PathVariable("movie_name") String movieName, @PathVariable("city") String city) {
        try {
            List<MovieShow> shows = showService.getShowsByMovieNameAndCity(movieName, city);
            return ResponseEntity.ok(shows);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/theatre/{theatre_name}")
    public ResponseEntity<List<MovieShow>> getShowsByTheatreName(@PathVariable("theatre_name") String theatreName) {
        try {
            List<MovieShow> shows = showService.getShowsByTheatreName(theatreName);
            return ResponseEntity.ok(shows);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/movieShows/{seatId}")
    public ResponseEntity<MovieShow> getMovieShowBySeatId(@PathVariable int seatId) {
        try {
            MovieShow movieShow = showService.getSeatId(seatId);
            return new ResponseEntity<>(movieShow, HttpStatus.OK);
        } catch (ShowNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/{movie_name}/{state}")
//    public ResponseEntity<List<MovieShow>> getShowsByMovieNameAndState(@PathVariable("movie_name") String movieName, @PathVariable("state") String state) {
//        try {
//            List<MovieShow> shows = showService.getShowsByMovieNameAndState(movieName, state);
//            return ResponseEntity.ok(shows);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

}
