package com.capgemini.movieticketbooking.service.impl;

import com.capgemini.movieticketbooking.exception.TheatreNotFoundException;
import com.capgemini.movieticketbooking.model.Address;
import com.capgemini.movieticketbooking.model.Theatre;
import com.capgemini.movieticketbooking.repository.AddressRepository;
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
    @Autowired
    private AddressRepository addressRepository;
 
    public void addTheatre(Theatre theatre) {
        addressRepository.save(theatre.getAddress());
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
            
            if(updatedTheatre.getTheatreName() != null)
            	existingTheatre.setTheatreName(updatedTheatre.getTheatreName());
            if(updatedTheatre.getCapacity() != 0)
            	existingTheatre.setCapacity(updatedTheatre.getCapacity());
            if(updatedTheatre.getAddress() != null)
            	existingTheatre.setAddress(updatedTheatre.getAddress());
            if(updatedTheatre.getMovieShows() != null)
            	existingTheatre.setMovieShows(updatedTheatre.getMovieShows());
            if(updatedTheatre.getSeats() != null)
            	existingTheatre.setSeats(updatedTheatre.getSeats());
            
            addressRepository.save(updatedTheatre.getAddress());
            theatreRepository.save(existingTheatre);
        } 
        else {
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
        return theatreRepository.findByAddressCity(city);
    }
 
    public List<Theatre> getTheatresByCountry(String country) {
        return theatreRepository.findByAddressCountry(country);
    }
 
    public List<Theatre> getTheatresByState(String state) {
        return theatreRepository.findByAddressState(state);
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
