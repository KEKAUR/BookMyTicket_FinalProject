package com.capgemini.movieticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.movieticketbooking.exception.TheatreNotFoundException;
import com.capgemini.movieticketbooking.model.Address;
import com.capgemini.movieticketbooking.model.Theatre;
import com.capgemini.movieticketbooking.service.TheatreService;

import java.util.List;

@RestController
@RequestMapping("/api/theatres")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @PostMapping
    public ResponseEntity<String> addTheatre(@RequestBody Theatre theatre) {
        try {
            theatreService.addTheatre(theatre);
            return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create record");
        }
    }

    @GetMapping
    public ResponseEntity<List<Theatre>> getAllTheatres() {
        try {
            List<Theatre> theatres = theatreService.getAllTheatres();
            return ResponseEntity.ok(theatres);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{theatre_id}")
    public ResponseEntity<Theatre> getTheatreById(@PathVariable("theatre_id") int theatreId) throws TheatreNotFoundException {
        try {
            Theatre theatre = theatreService.getTheatreById(theatreId);
            return ResponseEntity.ok(theatre);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{theatre_id}")
    public ResponseEntity<String> updateTheatreById(@PathVariable("theatre_id") int theatreId, @RequestBody Theatre theatre) throws TheatreNotFoundException {
        try {
            theatreService.updateTheatreById(theatreId, theatre);
            return ResponseEntity.ok("Record Updated Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update record");
        }
    }

    @DeleteMapping("/{theatre_id}")
    public ResponseEntity<String> deleteTheatreById(@PathVariable("theatre_id") int theatreId) throws TheatreNotFoundException {
        try {
            theatreService.deleteTheatreById(theatreId);
            return ResponseEntity.ok("Record Deleted Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete record");
        }
    }

    @GetMapping("/name/{theatre_name}")
    public ResponseEntity<Theatre> getTheatreByName(@PathVariable("theatre_name") String theatreName) throws TheatreNotFoundException {
        try {
            Theatre theatre = theatreService.getTheatreByName(theatreName);
            return ResponseEntity.ok(theatre);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Theatre>> getTheatresByCity(@PathVariable("city") String city) {
        try {
            List<Theatre> theatres = theatreService.getTheatresByCity(city);
            return ResponseEntity.ok(theatres);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<Theatre>> getTheatresByCountry(@PathVariable("country") String country) {
        try {
            List<Theatre> theatres = theatreService.getTheatresByCountry(country);
            return ResponseEntity.ok(theatres);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<Theatre>> getTheatresByState(@PathVariable("state") String state) {
        try {
            List<Theatre> theatres = theatreService.getTheatresByState(state);
            return ResponseEntity.ok(theatres);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/address/{theatre_id}")
    public ResponseEntity<String> updateTheatreAddressById(@PathVariable("theatre_id") int theatreId, @RequestBody Address address) throws TheatreNotFoundException {
        try {
            theatreService.updateTheatreAddressById(theatreId, address);
            return ResponseEntity.ok("Record Updated Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update address");
        }
    }

    @GetMapping("/address/{theatre_id}")
    public ResponseEntity<Address> getTheatreAddressById(@PathVariable("theatre_id") int theatreId) throws TheatreNotFoundException {
        try {
            Address address = theatreService.getTheatreAddressById(theatreId);
            return ResponseEntity.ok(address);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
