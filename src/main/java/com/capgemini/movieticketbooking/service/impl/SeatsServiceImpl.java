package com.capgemini.movieticketbooking.service.impl;

import com.capgemini.movieticketbooking.dto.SeatDTO;
import com.capgemini.movieticketbooking.exception.SeatNotFoundException;
import com.capgemini.movieticketbooking.model.Seats;
import com.capgemini.movieticketbooking.repository.SeatsRepository;
import com.capgemini.movieticketbooking.repository.TheatreRepository;
import com.capgemini.movieticketbooking.service.SeatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;
 
@Service
public class SeatsServiceImpl implements SeatsService{
 
    @Autowired
    private SeatsRepository seatsRepository;
    
    @Autowired
    private TheatreRepository theatreRepository;
    
	public void addSeat(Seats seat) {
		seatsRepository.save(seat);
	}
	
	@Override
	public void addSeat(SeatDTO seat) {
		Seats s = new Seats() ;
		s.setReserved(false);
		s.setSeatNumber(seat.getSeatNumber());
		s.setTheatre(theatreRepository.findById(seat.getTheatreId()).get());
		s.setTickets(null);
		seatsRepository.save(s);
	}
	
    public Seats getSeatBySeatId(int seatId) {
        return seatsRepository.findById(seatId).get();
    }
 
    public List<Seats> getSeatsByTheatreId(int theatreId) {
        return seatsRepository.findByTheatre_TheatreId(theatreId);
    }
 
    public void updateSeatDetails(int seatId, Seats updatedSeat) throws SeatNotFoundException {
        Optional<Seats> optionalSeat = seatsRepository.findById(seatId);
        if (optionalSeat.isPresent()) {
            Seats existingSeat = optionalSeat.get();
            existingSeat.setSeatNumber(updatedSeat.getSeatNumber());
            existingSeat.setReserved(updatedSeat.isReserved());
            seatsRepository.save(existingSeat);
        } else {
            throw new SeatNotFoundException("Seat not found with id: " + seatId);
        }
    }
 
    public Seats getSeatDetails(int seatId) throws SeatNotFoundException {
        Optional<Seats> optionalSeat = seatsRepository.findById(seatId);
        if (optionalSeat.isPresent()) {
            return optionalSeat.get();
        } else {
            throw new SeatNotFoundException("Seat not found with id: " +seatId);
        }
    }

	@Override
	public List<Seats> getAllSeats() throws SeatNotFoundException {
		return seatsRepository.findAll() ;
	}

}
