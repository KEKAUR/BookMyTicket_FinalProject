package com.capgemini.movieticketbooking.service.impl;

import com.capgemini.movieticketbooking.exception.AddressNotFoundException;
import com.capgemini.movieticketbooking.model.Address;
import com.capgemini.movieticketbooking.repository.AddressRepository;
import com.capgemini.movieticketbooking.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;
 
@Service
public class AddressServiceImpl implements AddressService{
 
    @Autowired
    private AddressRepository addressRepository;
 
    public void addAddress(Address address) {
        addressRepository.save(address);
    }
 
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }
 
    public Address getAddressById(int addressId) throws AddressNotFoundException {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        if (optionalAddress.isPresent()) {
            return optionalAddress.get();
        } else {
            throw new AddressNotFoundException("Address not found with id: " + addressId);
        }
    }
 
    public List<Address> getAddressesByCity(String city) {
        return addressRepository.findByCity(city);
    }
 
    public List<Address> getAddressesByState(String state) {
        return addressRepository.findByState(state);
    }
 
    public void deleteAddressById(int addressId) throws AddressNotFoundException {
        if (addressRepository.existsById(addressId)) {
            addressRepository.deleteById(addressId);
        } else {
            throw new AddressNotFoundException("Address not found with id: " + addressId);
        }
    }
 
    public void updateAddressById(int addressId, Address updatedAddress) throws AddressNotFoundException {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        if (optionalAddress.isPresent()) {
            Address existingAddress = optionalAddress.get();
            existingAddress.setAddressLine(updatedAddress.getAddressLine());
            existingAddress.setCity(updatedAddress.getCity());
            existingAddress.setState(updatedAddress.getState());
            addressRepository.save(existingAddress);
        } else {
            throw new AddressNotFoundException("Address not found with id: " + addressId);
        }
    }
}
