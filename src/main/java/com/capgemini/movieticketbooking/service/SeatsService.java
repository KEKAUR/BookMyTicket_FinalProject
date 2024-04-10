package com.capgemini.movieticketbooking.service;

import java.util.List;

import com.capgemini.movieticketbooking.exception.SeatNotFoundException;
import com.capgemini.movieticketbooking.model.Seats;

public interface SeatsService {

//	List<Seats> getSeatBySeatId(int seatId);

	List<Seats> getSeatsByTheatreId(int theatreId);

	Seats getSeatDetails(int seatId) throws SeatNotFoundException;

	void updateSeatDetails(int seatId, Seats seat) throws SeatNotFoundException;

}
