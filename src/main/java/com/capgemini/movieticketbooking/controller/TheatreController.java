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
@CrossOrigin(origins = "http://localhost:3000")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @PostMapping("/")
    public ResponseEntity<String> addTheatre(@RequestBody Theatre theatre) {
        theatreService.addTheatre(theatre);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
    }

    @GetMapping("/")
    public ResponseEntity<List<Theatre>> getAllTheatres() {
        List<Theatre> theatres = theatreService.getAllTheatres();
        return ResponseEntity.ok(theatres);
    }

    @GetMapping("/{theatre_id}")
    public ResponseEntity<Theatre> getTheatreById(@PathVariable("theatre_id") int theatreId) throws TheatreNotFoundException {
        Theatre theatre = theatreService.getTheatreById(theatreId);
        return ResponseEntity.ok(theatre);
    }

    @PutMapping("/{theatre_id}")
    public ResponseEntity<String> updateTheatreById(@PathVariable("theatre_id") int theatreId, @RequestBody Theatre theatre) throws TheatreNotFoundException {
        theatreService.updateTheatreById(theatreId, theatre);
        return ResponseEntity.ok("Record Updated Successfully");
    }

    @DeleteMapping("/{theatre_id}")
    public ResponseEntity<String> deleteTheatreById(@PathVariable("theatre_id") int theatreId) throws TheatreNotFoundException {
        theatreService.deleteTheatreById(theatreId);
        return ResponseEntity.ok("Record Deleted Successfully");
    }

    @GetMapping("/name/{theatre_name}")
    public ResponseEntity<Theatre> getTheatreByName(@PathVariable("theatre_name") String theatreName) throws TheatreNotFoundException {
        Theatre theatre = theatreService.getTheatreByName(theatreName);
        return ResponseEntity.ok(theatre);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Theatre>> getTheatresByCity(@PathVariable("city") String city) {
        List<Theatre> theatres = theatreService.getTheatresByCity(city);
        return ResponseEntity.ok(theatres);
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<Theatre>> getTheatresByCountry(@PathVariable("country") String country) {
        List<Theatre> theatres = theatreService.getTheatresByCountry(country);
        return ResponseEntity.ok(theatres);
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<Theatre>> getTheatresByState(@PathVariable("state") String state) throws TheatreNotFoundException {
        List<Theatre> theatres = theatreService.getTheatresByState(state);
        return ResponseEntity.ok(theatres);
    }

    @PutMapping("/address/{theatre_id}")
    public ResponseEntity<String> updateTheatreAddressById(@PathVariable("theatre_id") int theatreId, @RequestBody Address address) throws TheatreNotFoundException {
        theatreService.updateTheatreAddressById(theatreId, address);
        return ResponseEntity.ok("Record Updated Successfully");
    }

    @GetMapping("/address/{theatre_id}")
    public ResponseEntity<Address> getTheatreAddressById(@PathVariable("theatre_id") int theatreId) throws TheatreNotFoundException {
        Address address = theatreService.getTheatreAddressById(theatreId);
        return ResponseEntity.ok(address);
    }

}
