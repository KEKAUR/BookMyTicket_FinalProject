package com.capgemini.movieticketbooking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.movieticketbooking.model.Theatre;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Integer>{
                   
	Optional<Theatre> findByTheatreName(String theatreName);

    List<Theatre> findByAddressCity(String city);

	List<Theatre> findByAddressState(String state);

	List<Theatre> findByAddressCountry(String country);  
	  

}
