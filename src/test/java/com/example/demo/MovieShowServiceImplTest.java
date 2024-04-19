package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.movieticketbooking.dto.SeatDTO;
import com.capgemini.movieticketbooking.exception.ShowNotFoundException;
import com.capgemini.movieticketbooking.exception.TheatreNotFoundException;
import com.capgemini.movieticketbooking.model.MovieShow;
import com.capgemini.movieticketbooking.model.Seats;
import com.capgemini.movieticketbooking.model.Theatre;
import com.capgemini.movieticketbooking.repository.TicketRepository;
import com.capgemini.movieticketbooking.repository.MovieShowRepository;

import com.capgemini.movieticketbooking.repository.TheatreRepository;

import com.capgemini.movieticketbooking.service.TheatreService;
import com.capgemini.movieticketbooking.service.impl.ShowServiceImpl;

public class MovieShowServiceImplTest {

    @Mock
    private MovieShowRepository showRepository;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private TheatreService theatreService;

    @InjectMocks
    private ShowServiceImpl showService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSeatDetails() throws ShowNotFoundException, TheatreNotFoundException {
        int showId = 1;
        MovieShow mockShow = new MovieShow();
        mockShow.setShowId(showId);
        Theatre mockTheatre = new Theatre();
        Seats mockSeat = new Seats() ;
        List<Seats> mockListOfSeats = new ArrayList<Seats>() ;
        mockListOfSeats.add(mockSeat) ;
        mockTheatre.setSeats(mockListOfSeats); // or populate with some seats for testing
        mockShow.setTheatre(mockTheatre);

        when(showRepository.findById(showId)).thenReturn(Optional.of(mockShow));
        when(ticketRepository.findByMovieShowShowId(showId)).thenReturn(new ArrayList<>());

        List<SeatDTO> seatDetails = showService.getSeatDetails(showId);

        assertNotNull(seatDetails);
        assertEquals(mockTheatre.getSeats().size(), seatDetails.size());
        assertFalse(seatDetails.get(0).isReserved()); // Adjust assertions based on your scenario
        verify(showRepository, times(1)).findById(showId);
        verify(ticketRepository, times(1)).findByMovieShowShowId(showId);
    }

    @Test
    public void testGetAvailableSeats() throws ShowNotFoundException, TheatreNotFoundException {
        int showId = 1;
        MovieShow mockShow = new MovieShow();
        mockShow.setShowId(showId);
        Theatre mockTheatre = new Theatre();
        Set<Seats> seats = new HashSet<>(); // or populate with some seats for testing
        mockTheatre.setSeats(new ArrayList<>());
        mockShow.setTheatre(mockTheatre);

        when(showRepository.findById(showId)).thenReturn(Optional.of(mockShow));

        List<Seats> availableSeats = showService.getAvailableSeats(showId);

        assertNotNull(availableSeats);
        assertEquals(seats.size(), availableSeats.size());
        // Add assertions as needed based on your scenario
        verify(showRepository, times(1)).findById(showId);
    }

    
}
