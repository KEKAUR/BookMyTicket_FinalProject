package com.capgemini.movieticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.movieticketbooking.exception.SeatNotFoundException;
import com.capgemini.movieticketbooking.model.Seats;
import com.capgemini.movieticketbooking.service.SeatsService;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatsController {

    @Autowired
    private SeatsService seatService;

//    @GetMapping("/{tier_id}")
//    public ResponseEntity<List<Seats>> getSeatsByTierId(@PathVariable("tier_id") int tierId) {
//        try {
//            List<Seats> seats = seatService.getSeatsByTierId(tierId);
//            return ResponseEntity.ok(seats);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    @GetMapping("/theatre/{theatre_id}")
    public ResponseEntity<List<Seats>> getSeatsByTheatreId(@PathVariable("theatre_id") int theatreId) {
        try {
            List<Seats> seats = seatService.getSeatsByTheatreId(theatreId);
            return ResponseEntity.ok(seats);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{seat_id}")
    public ResponseEntity<String> updateSeatDetails(@PathVariable("seat_id") int seatId, @RequestBody Seats seat) throws SeatNotFoundException {
        try {
            seatService.updateSeatDetails(seatId, seat);
            return ResponseEntity.ok("Record Updated Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update record");
        }
    }

    @GetMapping("/{seat_id}")
    public ResponseEntity<Seats> getSeatDetails( @PathVariable("seat_id") int seatId) throws SeatNotFoundException {
        try {
            Seats seat = seatService.getSeatDetails( seatId);
            return ResponseEntity.ok(seat);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

