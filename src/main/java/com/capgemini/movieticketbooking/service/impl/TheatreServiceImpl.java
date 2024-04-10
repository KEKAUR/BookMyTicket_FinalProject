package com.capgemini.movieticketbooking.service.impl;

import com.capgemini.movieticketbooking.exception.TheatreNotFoundException;
import com.capgemini.movieticketbooking.model.Address;
import com.capgemini.movieticketbooking.model.Theatre;
import com.capgemini.movieticketbooking.repository.TheatreRepository;
import com.capgemini.movieticketbooking.service.TheatreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;
 
@Service
public class TheatreServiceImpl implements TheatreService {
 
    @Autowired
    private TheatreRepository theatreRepository;
 
    public void addTheatre(Theatre theatre) {
        theatreRepository.save(theatre);
    }
 
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }
 
    public Theatre getTheatreById(int theatreId) throws TheatreNotFoundException {
        Optional<Theatre> optionalTheatre = theatreRepository.findById(theatreId);
        if (optionalTheatre.isPresent()) {
            return optionalTheatre.get();
        } else {
            throw new TheatreNotFoundException("Theatre not found with id: " + theatreId);
        }
    }
 
    public void updateTheatreById(int theatreId, Theatre updatedTheatre) throws TheatreNotFoundException {
        Optional<Theatre> optionalTheatre = theatreRepository.findById(theatreId);
        if (optionalTheatre.isPresent()) {
            Theatre existingTheatre = optionalTheatre.get();
            existingTheatre.setTheatreName(updatedTheatre.getTheatreName());
            existingTheatre.setAddress(updatedTheatre.getAddress());
            // Update other fields as needed
            theatreRepository.save(existingTheatre);
        } else {
            throw new TheatreNotFoundException("Theatre not found with id: " + theatreId);
        }
    }
 
    public void deleteTheatreById(int theatreId) throws TheatreNotFoundException {
        Optional<Theatre> optionalTheatre = theatreRepository.findById(theatreId);
        if (optionalTheatre.isPresent()) {
            theatreRepository.delete(optionalTheatre.get());
        } else {
            throw new TheatreNotFoundException("Theatre not found with id: " + theatreId);
        }
    }
 
    public Theatre getTheatreByName(String theatreName) throws TheatreNotFoundException {
        return theatreRepository.findByTheatreName(theatreName)
                .orElseThrow(() -> new TheatreNotFoundException("Theatre not found with name: " + theatreName));
    }
 
    public List<Theatre> getTheatresByCity(String city) {
        return theatreRepository.findByCity(city);
    }
 
    public List<Theatre> getTheatresByCountry(String country) {
        return theatreRepository.findByCountry(country);
    }
 
    public List<Theatre> getTheatresByState(String state) {
        return theatreRepository.findByState(state);
    }
 
    public void updateTheatreAddressById(int theatreId, Address address) throws TheatreNotFoundException {
        Optional<Theatre> optionalTheatre = theatreRepository.findById(theatreId);
        if (optionalTheatre.isPresent()) {
            Theatre existingTheatre = optionalTheatre.get();
            existingTheatre.setAddress(address);
            theatreRepository.save(existingTheatre);
        } else {
            throw new TheatreNotFoundException("Theatre not found with id: " + theatreId);
        }
    }
 
    public Address getTheatreAddressById(int theatreId) throws TheatreNotFoundException {
        Optional<Theatre> optionalTheatre = theatreRepository.findById(theatreId);
        if (optionalTheatre.isPresent()) {
            return optionalTheatre.get().getAddress();
        } else {
            throw new TheatreNotFoundException("Theatre not found with id: " + theatreId);
        }
    }
}
