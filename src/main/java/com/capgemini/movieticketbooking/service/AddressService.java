package com.capgemini.movieticketbooking.service;

import java.util.List;

import com.capgemini.movieticketbooking.exception.AddressNotFoundException;
import com.capgemini.movieticketbooking.model.Address;

public interface AddressService {

	void addAddress(Address address);

	List<Address> getAllAddresses() throws AddressNotFoundException;

	Address getAddressById(int addressId) throws AddressNotFoundException;

	List<Address> getAddressesByCity(String city) throws AddressNotFoundException;

	List<Address> getAddressesByState(String state) throws AddressNotFoundException;

	void deleteAddressById(int addressId) throws AddressNotFoundException;

	void updateAddressById(int addressId, Address address) throws AddressNotFoundException;

}
