package com.capgemini.movieticketbooking.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "movie_show")
public class MovieShow {

	@Id
	@GeneratedValue
	@Column(name = "show_id")
	private int showId;

	@Column(name = "show_datetime")
	@CreationTimestamp
	private LocalDateTime showDateTime;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "movie_id")
	@JsonIgnore
	private Movie movie;

	@OneToMany(mappedBy = "movieshow", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Ticket> tickets;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "theatre_id")
	@JsonIgnore
	private Theatre theatre;

	public MovieShow() {
	}

	public MovieShow(int showId, LocalDateTime showDateTime, Movie movie, List<Ticket> tickets,
			Theatre theatre) {
		super();
		this.showId = showId;
		this.showDateTime = showDateTime;
		this.movie = movie;
//		this.offers = offers;
		this.tickets = tickets;
		this.theatre = theatre;
	}

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public LocalDateTime getShowDateTime() {
		return showDateTime;
	}

	public void setShowDateTime(LocalDateTime showDateTime) {
		this.showDateTime = showDateTime;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

//	public List<Offers> getOffers() {
//		return offers;
//	}
//
//	public void setOffers(List<Offers> offers) {
//		this.offers = offers;
//	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

}
