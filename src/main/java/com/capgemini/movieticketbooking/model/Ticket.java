package com.capgemini.movieticketbooking.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;

    @Positive(message = "Amount must be a positive value")
    private int amount;

    @NotNull(message = "Purchase date must be provided")
    @FutureOrPresent(message = "Purchase date must be in the present or future")
    private LocalDate purchaseDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "show_id")
   // @JsonIgnore
    private MovieShow movieShow;

    @ManyToMany
    @JoinTable(
        name = "ticket_seat_mapping",
        joinColumns = @JoinColumn(name = "ticket_id"),
        inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private Set<Seats> seats = new HashSet<>();

    @OneToOne(mappedBy = "ticket", fetch = FetchType.LAZY)
    @JsonIgnore
    private Payments payment;
    
}