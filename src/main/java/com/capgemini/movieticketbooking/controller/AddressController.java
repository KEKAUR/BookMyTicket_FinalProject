package com.capgemini.movieticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.movieticketbooking.exception.AddressNotFoundException;
import com.capgemini.movieticketbooking.model.Address;
import com.capgemini.movieticketbooking.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<String> addAddress(@RequestBody Address address) {
        try {
            addressService.addAddress(address);
            return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create record");
        }
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        try {
            List<Address> addresses = addressService.getAllAddresses();
            return ResponseEntity.ok(addresses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{address_id}")
    public ResponseEntity<Address> getAddressById(@PathVariable("address_id") int addressId) throws AddressNotFoundException {
        try {
            Address address = addressService.getAddressById(addressId);
            return ResponseEntity.ok(address);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Address>> getAddressesByCity(@PathVariable("city") String city) {
        try {
            List<Address> addresses = addressService.getAddressesByCity(city);
            return ResponseEntity.ok(addresses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<Address>> getAddressesByState(@PathVariable("state") String state) {
        try {
            List<Address> addresses = addressService.getAddressesByState(state);
            return ResponseEntity.ok(addresses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{address_id}")
    public ResponseEntity<String> deleteAddressById(@PathVariable("address_id") int addressId) throws AddressNotFoundException {
        try {
            addressService.deleteAddressById(addressId);
            return ResponseEntity.ok("Record Deleted Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete record");
        }
    }

    @PutMapping("/{address_id}")
    public ResponseEntity<String> updateAddressById(@PathVariable("address_id") int addressId, @RequestBody Address address) throws AddressNotFoundException {
        try {
            addressService.updateAddressById(addressId, address);
            return ResponseEntity.ok("Record Updated Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update record");
        }
    }
}
