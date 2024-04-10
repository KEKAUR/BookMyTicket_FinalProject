package com.capgemini.movieticketbooking.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {
	
	@GeneratedValue
	@Id
	@Column(name = "ticket_id")
	private int ticketId;

	@Column(name = "amount")
	private int amount;

	@Column(name = "purchase_date")
	private LocalDate purchaseDate;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private MovieShow movieshow;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Seats seats;
//
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Payments payment;

	public Ticket() {
	}

	public Ticket(int ticketId, int amount, LocalDate purchaseDate, MovieShow movieshow, Seats seats,
			Payments payment) {
		super();
		this.ticketId = ticketId;
		this.amount = amount;
		this.purchaseDate = purchaseDate;
		this.movieshow = movieshow;
		this.seats = seats;
		this.payment = payment;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public MovieShow getMovieshow() {
		return movieshow;
	}

	public void setMovieshow(MovieShow movieshow) {
		this.movieshow = movieshow;
	}

	public Seats getSeats() {
		return seats;
	}

	public void setSeats(Seats seats) {
		this.seats = seats;
	}

	public Payments getPayment() {
		return payment;
	}

	public void setPayment(Payments payment) {
		this.payment = payment;
	}

	

}
