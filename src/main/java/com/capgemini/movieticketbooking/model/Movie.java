package com.capgemini.movieticketbooking.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
@Table(name = "movies")
public class Movie {

	@Id
	//@GeneratedValue
	@Column(name = "id")
	private int movieId;

	@Column(name = "name")
	private String movieName;

	@Column(name = "ticket_base_price")
	private int ticketBasePrice;

	@Column(name = "plot")
	private String plot;

	@Column(name = "director")
	private String director;

	@Column(name = "rating")
	private float rating;

	@Column(name = "genre")
	private String genre;

	@Column(name = "poster")
	private String poster;

	@Column(name = "language")
	private String language;

	@Column(name = "trailer")
	private String trailer;

	@OneToMany(mappedBy = "showId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<MovieShow> movieshows;

	public Movie() {

	}

	public Movie(int movieId, String movieName, int ticketBasePrice, String plot, String director, float rating,
			String genre, String poster, String language, String trailer, List<MovieShow> movieshows) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.ticketBasePrice = ticketBasePrice;
		this.plot = plot;
		this.director = director;
		this.rating = rating;
		this.genre = genre;
		this.poster = poster;
		this.language = language;
		this.trailer = trailer;
		this.movieshows = movieshows;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getTicketBasePrice() {
		return ticketBasePrice;
	}

	public void setTicketBasePrice(int ticketBasePrice) {
		this.ticketBasePrice = ticketBasePrice;
	}

	public List<MovieShow> getMovieshows() {
		return movieshows;
	}

	public void setMovieshows(List<MovieShow> movieshows) {
		this.movieshows = movieshows;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

}