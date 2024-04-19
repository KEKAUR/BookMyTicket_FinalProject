package com.capgemini.movieticketbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.movieticketbooking.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

	@Query("SELECT m FROM Movie m WHERE LOWER(m.movieName) = LOWER(:movieName)")    
	Optional<Movie> findByNameIgnoreCase(@Param("movieName") String movieName);

}
