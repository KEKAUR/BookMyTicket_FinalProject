package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.movieticketbooking.dto.SeatDTO;
import com.capgemini.movieticketbooking.exception.SeatNotFoundException;
import com.capgemini.movieticketbooking.model.Seats;
import com.capgemini.movieticketbooking.repository.SeatsRepository;
import com.capgemini.movieticketbooking.repository.TheatreRepository;
import com.capgemini.movieticketbooking.service.impl.SeatsServiceImpl;

public class SeatsServiceImplTest {

    @Mock
    private SeatsRepository seatsRepository;

    @Mock
    private TheatreRepository theatreRepository;

    @InjectMocks
    private SeatsServiceImpl seatsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddSeat() {
        Seats seat = new Seats();
        seat.setSeatId(1);
        when(seatsRepository.save(any(Seats.class))).thenReturn(seat);

        seatsService.addSeat(seat);

        verify(seatsRepository).save(seat);
    }

    @Test
    public void testGetSeatBySeatId() throws SeatNotFoundException {
        Seats seat = new Seats();
        seat.setSeatId(1);
        when(seatsRepository.findById(1)).thenReturn(Optional.of(seat));

        Seats resultSeat = seatsService.getSeatBySeatId(1);

        assertEquals(1, resultSeat.getSeatId());
    }
    

//    @Test
//    public void testGetSeatBySeatIdNotFound() {
//        // Mock the repository to return an empty optional
//        when(seatsRepository.findById(anyInt())).thenReturn(Optional.empty());
//
//        // Assert that SeatNotFoundException is thrown
//        SeatNotFoundException exception = assertThrows(SeatNotFoundException.class, () -> seatsService.getSeatBySeatId(1));
//
//        // Optionally, assert the exception message or perform additional checks
//        assertEquals("Seat not found with id: 1", exception.getMessage());
//    }


    @Test
    public void testGetSeatsByTheatreId() {
        List<Seats> seatsList = new ArrayList<>();
        Seats seat1 = new Seats();
        seat1.setSeatId(1);
        seatsList.add(seat1);
        Seats seat2 = new Seats();
        seat2.setSeatId(2);
        seatsList.add(seat2);

        when(seatsRepository.findByTheatre_TheatreId(1)).thenReturn(seatsList);

        List<Seats> resultSeats = seatsService.getSeatsByTheatreId(1);

        assertEquals(2, resultSeats.size());
        assertEquals(1, resultSeats.get(0).getSeatId());
        assertEquals(2, resultSeats.get(1).getSeatId());
    }

    @Test
    public void testUpdateSeatDetails() throws SeatNotFoundException {
        Seats seat = new Seats();
        seat.setSeatId(1);
        seat.setReserved(false);
        when(seatsRepository.findById(1)).thenReturn(Optional.of(seat));

        Seats updatedSeat = new Seats();
        updatedSeat.setSeatId(1);
        updatedSeat.setReserved(true);

        seatsService.updateSeatDetails(1, updatedSeat);

        verify(seatsRepository).save(updatedSeat);
        assertEquals(true, seat.isReserved());
    }

    @Test
    public void testUpdateSeatDetailsNotFound() {
        when(seatsRepository.findById(1)).thenReturn(Optional.empty());

        Seats updatedSeat = new Seats();
        updatedSeat.setSeatId(1);
        updatedSeat.setReserved(true);

        assertThrows(SeatNotFoundException.class, () -> seatsService.updateSeatDetails(1, updatedSeat));
    }



}
