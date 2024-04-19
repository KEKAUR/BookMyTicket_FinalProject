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

import com.capgemini.movieticketbooking.exception.TheatreNotFoundException;
import com.capgemini.movieticketbooking.model.Address;
import com.capgemini.movieticketbooking.model.Theatre;
import com.capgemini.movieticketbooking.repository.AddressRepository;
import com.capgemini.movieticketbooking.repository.TheatreRepository;
import com.capgemini.movieticketbooking.service.TheatreService;
import com.capgemini.movieticketbooking.service.impl.TheatreServiceImpl;

public class TheatreServiceImplTest {

    @Mock
    private TheatreRepository theatreRepository;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private TheatreServiceImpl theatreService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTheatreById() throws TheatreNotFoundException {
        int theatreId = 1;
        Theatre mockTheatre = new Theatre();
        mockTheatre.setTheatreId(theatreId);

        when(theatreRepository.findById(theatreId)).thenReturn(Optional.of(mockTheatre));

        Theatre retrievedTheatre = theatreService.getTheatreById(theatreId);

        assertNotNull(retrievedTheatre);
        assertEquals(theatreId, retrievedTheatre.getTheatreId());
        verify(theatreRepository, times(1)).findById(theatreId);
    }

    @Test
    public void testAddTheatre() {
        Theatre mockTheatre = new Theatre();
        Address mockAddress = new Address();
        mockTheatre.setAddress(mockAddress);

        theatreService.addTheatre(mockTheatre);

        verify(addressRepository, times(1)).save(mockAddress);
        verify(theatreRepository, times(1)).save(mockTheatre);
    }

    @Test
    public void testDeleteTheatreById() throws TheatreNotFoundException {
        int theatreId = 1;
        Theatre mockTheatre = new Theatre();
        mockTheatre.setTheatreId(theatreId);

        when(theatreRepository.findById(theatreId)).thenReturn(Optional.of(mockTheatre));

        theatreService.deleteTheatreById(theatreId);

        verify(theatreRepository, times(1)).delete(mockTheatre);
    }
}
