package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.movieticketbooking.dto.TicketBookingDTO;
import com.capgemini.movieticketbooking.exception.ShowNotFoundException;
import com.capgemini.movieticketbooking.exception.UserNotFoundException;
import com.capgemini.movieticketbooking.model.MovieShow;
import com.capgemini.movieticketbooking.model.Seats;
import com.capgemini.movieticketbooking.model.Ticket;
import com.capgemini.movieticketbooking.model.User;
import com.capgemini.movieticketbooking.repository.MovieShowRepository;
import com.capgemini.movieticketbooking.repository.PaymentsRepository;
import com.capgemini.movieticketbooking.repository.SeatsRepository;
import com.capgemini.movieticketbooking.repository.TicketRepository;
import com.capgemini.movieticketbooking.service.SeatsService;
import com.capgemini.movieticketbooking.service.ShowService;
import com.capgemini.movieticketbooking.service.UserService;
import com.capgemini.movieticketbooking.service.impl.TicketServiceImpl;

public class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private SeatsRepository seatsRepository;

    @Mock
    private SeatsService seatsService;

    @Mock
    private PaymentsRepository paymentRepository;

    @Mock
    private ShowService showService;

    @Mock
    private UserService userService;

    @Mock
    private MovieShowRepository movieShowRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBookTicketWithPayment_Success() throws UserNotFoundException, ShowNotFoundException {
        // Mock input data
        TicketBookingDTO bookingDto = new TicketBookingDTO();
        bookingDto.setTheatreId(1);
        bookingDto.setStatus("Success");
        bookingDto.setAmount(100);
        bookingDto.setPurchaseDate(LocalDate.now());
        bookingDto.setShowId(1);
        bookingDto.setUserId(1);
        bookingDto.setListSeatId(Arrays.asList(1, 2, 3));

        // Mock necessary service/repository method calls
        when(seatsService.getSeatBySeatId(anyInt())).thenReturn(new Seats());
        when(showService.getShowById(anyInt())).thenReturn(new MovieShow());
        when(userService.getUserById(anyInt())).thenReturn(new User());

        // Perform the test
        Ticket bookedTicket = ticketService.bookTicketWithPayment(bookingDto);

        // Verify the behavior
        Assertions.assertNotNull(bookedTicket);
        Assertions.assertEquals("success", bookedTicket.getPayment().getPaymentStatus());
        Assertions.assertEquals(3, bookedTicket.getSeats().size());
        verify(seatsRepository, times(3)).save(any());
        verify(ticketRepository, times(1)).save(any());
        verify(paymentRepository, times(1)).save(any());
    }

    @Test
    public void testBookTicketWithPayment_Failure() throws UserNotFoundException, ShowNotFoundException {
        // Mock input data
        TicketBookingDTO bookingDto = new TicketBookingDTO();
        bookingDto.setStatus("failure");

        // Perform the test
        Ticket bookedTicket = ticketService.bookTicketWithPayment(bookingDto);

        // Verify the behavior
        Assertions.assertNull(bookedTicket);
        verifyNoInteractions(seatsRepository); // No seats should be saved
        verifyNoInteractions(ticketRepository); // No ticket should be saved
        verifyNoInteractions(paymentRepository); // No payment should be saved
    }
}