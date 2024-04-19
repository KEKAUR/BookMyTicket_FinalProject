package com.capgemini.movieticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.capgemini.movieticketbooking.dto.SeatDTO;
import com.capgemini.movieticketbooking.exception.SeatNotFoundException;
import com.capgemini.movieticketbooking.model.Seats;
import com.capgemini.movieticketbooking.service.SeatsService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@CrossOrigin(origins = "http://localhost:3000")
public class SeatsController {

    @Autowired
    private SeatsService seatService;
    
    @GetMapping
    public ResponseEntity<List<Seats>> getAllSeats() throws SeatNotFoundException {
        List<Seats> seats = seatService.getAllSeats();
        return ResponseEntity.ok(seats);
    }
    
    @PostMapping("/")
    public ResponseEntity<String> addSeat(@Valid @RequestBody SeatDTO seat) {
        seatService.addSeat(seat);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
    }
    
    @GetMapping("/theatre/{theatre_id}")
    public ResponseEntity<List<Seats>> getSeatsByTheatreId(@PathVariable("theatre_id") int theatreId) {
        List<Seats> seats = seatService.getSeatsByTheatreId(theatreId);
        return ResponseEntity.ok(seats);
    }

    @PutMapping("/{seat_id}")
    public ResponseEntity<String> updateSeatDetails(@PathVariable("seat_id") int seatId,@Valid @RequestBody Seats seat) throws SeatNotFoundException {
        seatService.updateSeatDetails(seatId, seat);
        return ResponseEntity.ok("Record Updated Successfully");
    }

    @GetMapping("/{seat_id}")
    public ResponseEntity<Seats> getSeatDetails(@PathVariable("seat_id") int seatId) throws SeatNotFoundException {
        Seats seat = seatService.getSeatDetails(seatId);
        return ResponseEntity.ok(seat);
    }

}
