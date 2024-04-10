package com.capgemini.movieticketbooking.service;

import java.util.List;

import com.capgemini.movieticketbooking.exception.TheatreNotFoundException;
import com.capgemini.movieticketbooking.model.Address;
import com.capgemini.movieticketbooking.model.Theatre;

public interface TheatreService {

	void addTheatre(Theatre theatre);

	List<Theatre> getAllTheatres();

	Theatre getTheatreById(int theatreId) throws TheatreNotFoundException;

	void updateTheatreById(int theatreId, Theatre theatre) throws TheatreNotFoundException;

	void deleteTheatreById(int theatreId) throws TheatreNotFoundException;

	Theatre getTheatreByName(String theatreName) throws TheatreNotFoundException;

	List<Theatre> getTheatresByCity(String city);

	List<Theatre> getTheatresByCountry(String country);

	Address getTheatreAddressById(int theatreId) throws TheatreNotFoundException;

	void updateTheatreAddressById(int theatreId, Address address) throws TheatreNotFoundException;

	List<Theatre> getTheatresByState(String state) throws TheatreNotFoundException;

}
