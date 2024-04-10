package com.capgemini.movieticketbooking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.movieticketbooking.model.Theatre;

public interface TheatreRepository extends JpaRepository<Theatre, Integer>{

	@Query("SELECT t FROM Theatre t WHERE t.theatreName = :theatreName")
	Optional<Theatre> findByTheatreName(String theatreName);

	@Query("SELECT t FROM Theatre t JOIN t.address a WHERE a.city = :city")
	List<Theatre> findByAddressCity(String city);

	@Query("SELECT t FROM Theatre t JOIN t.address a WHERE a.country = :country")
	List<Theatre> findByCountry(String country);
	
	@Query("SELECT t FROM Theatre t JOIN t.address a WHERE a.city = :city")
	List<Theatre> findByCity(String city);

	@Query("SELECT t FROM Theatre t JOIN t.address a WHERE a.state = :state")
	List<Theatre> findByState(String state);

}
