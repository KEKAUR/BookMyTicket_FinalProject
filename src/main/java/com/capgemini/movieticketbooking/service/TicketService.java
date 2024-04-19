package com.capgemini.movieticketbooking.service;

import java.util.List;

import com.capgemini.movieticketbooking.dto.TicketBookingDTO;
import com.capgemini.movieticketbooking.exception.ShowNotFoundException;
import com.capgemini.movieticketbooking.exception.UserNotFoundException;
import com.capgemini.movieticketbooking.model.Ticket;

public interface TicketService {

	List<Ticket> getAllTickets();

	Ticket getTicketById(int ticketId);

	void updateTicket(int ticketId, Ticket ticket);

	void deleteTicketById(int ticketId);

	void addTicket(Ticket ticket);

//	Ticket bookSeatsWithPayment(BookingDTO bookingDto) throws UserNotFoundException, ShowNotFoundException;
	Ticket bookTicketWithPayment(TicketBookingDTO bookingDto) throws UserNotFoundException, ShowNotFoundException;

}
