package com.capgemini.movieticketbooking.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Seats {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatId;

    @NotBlank(message = "Seat number must not be blank")
    private String seatNumber;

    private boolean isReserved;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "theatre_id")
    @JsonIgnore
    private Theatre theatre;

    @ManyToMany(mappedBy = "seats")
    private Set<Ticket> tickets = new HashSet<>();

}