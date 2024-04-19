package com.capgemini.movieticketbooking.service.impl;

import com.capgemini.movieticketbooking.dto.TicketBookingDTO;
import com.capgemini.movieticketbooking.exception.ShowNotFoundException;
import com.capgemini.movieticketbooking.exception.UserNotFoundException;
import com.capgemini.movieticketbooking.model.Payments;
import com.capgemini.movieticketbooking.model.Seats;
import com.capgemini.movieticketbooking.model.Ticket;
import com.capgemini.movieticketbooking.repository.MovieShowRepository;
import com.capgemini.movieticketbooking.repository.PaymentsRepository;
import com.capgemini.movieticketbooking.repository.SeatsRepository;
import com.capgemini.movieticketbooking.repository.TicketRepository;
import com.capgemini.movieticketbooking.service.SeatsService;
import com.capgemini.movieticketbooking.service.ShowService;
import com.capgemini.movieticketbooking.service.TicketService;
import com.capgemini.movieticketbooking.service.UserService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TicketServiceImpl implements TicketService {
 
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private SeatsRepository seatsRepository;
    @Autowired
    private SeatsService seatsService;
    @Autowired
    private PaymentsRepository paymentRepository;
    @Autowired
    private ShowService showService;
    @Autowired
    private UserService userService;
    @Autowired
    MovieShowRepository movieShowRepository ;
 
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
 
    public void addTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }
 
    public Ticket getTicketById(int ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        return optionalTicket.orElse(null);
    }
 
    public void updateTicket(int ticketId, Ticket updatedTicket) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket existingTicket = optionalTicket.get();
            existingTicket.setAmount(updatedTicket.getAmount());
            ticketRepository.save(existingTicket);
        }
    }

	public void deleteTicketById(int ticketId) {
		ticketRepository.deleteById(ticketId);
	}

    @Transactional
    public Ticket bookTicketWithPayment(TicketBookingDTO bookingDto) throws UserNotFoundException, ShowNotFoundException {
    	if(bookingDto.getStatus().equalsIgnoreCase("success")) {
    		List<Integer> seatIdsToBeBooked = bookingDto.getListSeatId() ;
    
    		Set<Seats> seatsToBeBooked  = new HashSet<>() ;
    		for(int number : seatIdsToBeBooked) {
    			Seats s = seatsService.getSeatBySeatId(number);
    			s.setReserved(true);
    			seatsRepository.save(s) ;
    			seatsToBeBooked.add(s) ;
    		}
    		
    		Payments p = new Payments() ;
    		p.setAmount(bookingDto.getAmount());
    		p.setPaymentDate(bookingDto.getPurchaseDate());
    		p.setPaymentStatus(bookingDto.getStatus());
    		p.setUser(userService.getUserById(bookingDto.getUserId()));
      
    		Ticket t = new Ticket() ;
    		t.setAmount(bookingDto.getAmount());
    		t.setMovieShow(showService.getShowById(bookingDto.getShowId()));
    		t.setPurchaseDate(bookingDto.getPurchaseDate());
    		t.setSeats(seatsToBeBooked) ;
    		t.setPayment(p) ;
    		
            Ticket savedTicket = ticketRepository.save(t);
       
            p.setTicket(savedTicket);
            
            paymentRepository.save(p) ;
            
            return savedTicket;
    	}
    	else return null ;
    }
}


