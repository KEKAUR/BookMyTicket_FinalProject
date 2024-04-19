package com.capgemini.movieticketbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.movieticketbooking.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

	List<Address> findByState(String state);

	List<Address> findByCity(String city);

}
