package com.capgemini.movieticketbooking.service.impl;

import com.capgemini.movieticketbooking.model.Ticket;
import com.capgemini.movieticketbooking.repository.TicketRepository;
import com.capgemini.movieticketbooking.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;
 
@Service
public class TicketServiceImpl implements TicketService {
 
    @Autowired
    private TicketRepository ticketRepository;
 
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
}


