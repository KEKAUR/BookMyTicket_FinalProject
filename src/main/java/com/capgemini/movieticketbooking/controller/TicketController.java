package com.capgemini.movieticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.movieticketbooking.dto.TicketBookingDTO;
import com.capgemini.movieticketbooking.exception.ShowNotFoundException;
import com.capgemini.movieticketbooking.exception.UserNotFoundException;
import com.capgemini.movieticketbooking.model.Ticket;
import com.capgemini.movieticketbooking.service.TicketService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:3000")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/")
    public ResponseEntity<String> addTicket(@Valid @RequestBody Ticket ticket) {
        ticketService.addTicket(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
    }
    
    @PostMapping("/book")
    public ResponseEntity<Ticket> bookTickets(@Valid @RequestBody TicketBookingDTO bookingDto) throws UserNotFoundException, ShowNotFoundException {
        Ticket bookedTicket = ticketService.bookTicketWithPayment(bookingDto);
        return ResponseEntity.ok(bookedTicket);
    }

    @GetMapping("/{ticket_id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("ticket_id") int ticketId) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        return ResponseEntity.ok(ticket);
    }

    @PutMapping("/{ticket_id}")
    public ResponseEntity<String> updateTicket(@PathVariable("ticket_id") int ticketId, @Valid @RequestBody Ticket ticket) {
        ticketService.updateTicket(ticketId, ticket);
        return ResponseEntity.ok("Record Updated Successfully");
    }

    @DeleteMapping("/{ticket_id}")
    public ResponseEntity<String> deleteTicketById(@PathVariable("ticket_id") int ticketId) {
        ticketService.deleteTicketById(ticketId);
        return ResponseEntity.ok("Record Deleted Successfully");
    }

}
