package com.capgemini.movieticketbooking.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.capgemini.movieticketbooking.exception.AddressNotFoundException;
import com.capgemini.movieticketbooking.model.Address;
import com.capgemini.movieticketbooking.service.AddressService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@CrossOrigin(origins = "http://localhost:3000")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/")
    public ResponseEntity<List<Address>> getAllAddresses() throws AddressNotFoundException {
        List<Address> addresses = addressService.getAllAddresses();
        return ResponseEntity.ok(addresses);
    }
    
    @PostMapping("/")
    public ResponseEntity<String> addAddress(@Valid @RequestBody Address address) {
        addressService.addAddress(address);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
    }

    @GetMapping("/{address_id}")
    public ResponseEntity<Address> getAddressById(@PathVariable("address_id") int addressId) throws AddressNotFoundException {
        Address address = addressService.getAddressById(addressId);
        return ResponseEntity.ok(address);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Address>> getAddressesByCity(@PathVariable("city") String city) throws AddressNotFoundException {
        List<Address> addresses = addressService.getAddressesByCity(city);
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<Address>> getAddressesByState(@PathVariable("state") String state) throws AddressNotFoundException {
        List<Address> addresses = addressService.getAddressesByState(state);
        return ResponseEntity.ok(addresses);
    }

    @DeleteMapping("/{address_id}")
    public ResponseEntity<String> deleteAddressById(@PathVariable("address_id") int addressId) throws AddressNotFoundException {
        addressService.deleteAddressById(addressId);
        return ResponseEntity.ok("Record Deleted Successfully");
    }

    @PutMapping("/{address_id}")
    public ResponseEntity<String> updateAddressById(@PathVariable("address_id") int addressId, @Valid @RequestBody Address address) throws AddressNotFoundException {
        addressService.updateAddressById(addressId, address);
        return ResponseEntity.ok("Record Updated Successfully");
    }
}
