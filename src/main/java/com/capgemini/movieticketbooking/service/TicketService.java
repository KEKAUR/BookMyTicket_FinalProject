package com.capgemini.movieticketbooking.service;

import java.util.List;

import com.capgemini.movieticketbooking.model.Ticket;

public interface TicketService {

	List<Ticket> getAllTickets();

	Ticket getTicketById(int ticketId);

	void updateTicket(int ticketId, Ticket ticket);

	void deleteTicketById(int ticketId);

	void addTicket(Ticket ticket);

}
