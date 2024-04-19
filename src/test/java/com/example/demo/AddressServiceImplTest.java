package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.movieticketbooking.exception.AddressNotFoundException;
import com.capgemini.movieticketbooking.model.Address;
import com.capgemini.movieticketbooking.repository.AddressRepository;
import com.capgemini.movieticketbooking.service.impl.AddressServiceImpl;

public class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAddresses() {
        List<Address> mockAddresses = new ArrayList<>();
        mockAddresses.add(new Address());
        mockAddresses.add(new Address());

        when(addressRepository.findAll()).thenReturn(mockAddresses);

        List<Address> addresses = addressService.getAllAddresses();

        assertEquals(2, addresses.size());
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    public void testGetAddressById_ExistingId() throws AddressNotFoundException {
        int existingId = 1;
        Address mockAddress = new Address();
        mockAddress.setAddressId(existingId);

        when(addressRepository.findById(existingId)).thenReturn(Optional.of(mockAddress));

        Address address = addressService.getAddressById(existingId);

        assertNotNull(address);
        assertEquals(existingId, address.getAddressId());
        verify(addressRepository, times(1)).findById(existingId);
    }

    @Test
    public void testGetAddressById_NonExistingId() {
        int nonExistingId = 100;

        when(addressRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(AddressNotFoundException.class, () -> {
            addressService.getAddressById(nonExistingId);
        });
        verify(addressRepository, times(1)).findById(nonExistingId);
    }

    @Test
    public void testGetAddressesByCity() {
        String city = "TestCity";
        List<Address> mockAddresses = new ArrayList<>();
        mockAddresses.add(new Address());
        mockAddresses.add(new Address());

        when(addressRepository.findByCity(city)).thenReturn(mockAddresses);

        List<Address> addresses = addressService.getAddressesByCity(city);

        assertEquals(2, addresses.size());
        verify(addressRepository, times(1)).findByCity(city);
    }

    @Test
    public void testGetAddressesByState() {
        String state = "TestState";
        List<Address> mockAddresses = new ArrayList<>();
        mockAddresses.add(new Address());
        mockAddresses.add(new Address());

        when(addressRepository.findByState(state)).thenReturn(mockAddresses);

        List<Address> addresses = addressService.getAddressesByState(state);

        assertEquals(2, addresses.size());
        verify(addressRepository, times(1)).findByState(state);
    }

    @Test
    public void testAddAddress() {
        Address newAddress = new Address();
        addressService.addAddress(newAddress);

        verify(addressRepository, times(1)).save(newAddress);
    }

    @Test
    public void testUpdateAddressById_ExistingId() throws AddressNotFoundException {
        int existingId = 1;
        Address updatedAddress = new Address();
        updatedAddress.setAddressLine("Updated Line");

        Address mockAddress = new Address();
        mockAddress.setAddressId(existingId);

        when(addressRepository.findById(existingId)).thenReturn(Optional.of(mockAddress));

        addressService.updateAddressById(existingId, updatedAddress);

        assertEquals("Updated Line", mockAddress.getAddressLine());
        verify(addressRepository, times(1)).save(mockAddress);
    }

    @Test
    public void testUpdateAddressById_NonExistingId() {
        int nonExistingId = 100;
        Address updatedAddress = new Address();
        updatedAddress.setAddressLine("Updated Line");

        when(addressRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(AddressNotFoundException.class, () -> {
            addressService.updateAddressById(nonExistingId, updatedAddress);
        });
        verify(addressRepository, times(0)).save(any()); // No save should occur
    }

    @Test
    public void testDeleteAddressById_ExistingId() throws AddressNotFoundException {
        int existingId = 1;

        when(addressRepository.existsById(existingId)).thenReturn(true);

        addressService.deleteAddressById(existingId);

        verify(addressRepository, times(1)).deleteById(existingId);
    }

    @Test
    public void testDeleteAddressById_NonExistingId() {
        int nonExistingId = 100;

        when(addressRepository.existsById(nonExistingId)).thenReturn(false);

        assertThrows(AddressNotFoundException.class, () -> {
            addressService.deleteAddressById(nonExistingId);
        });
        verify(addressRepository, times(0)).deleteById(any()); // No delete should occur
    }
}
