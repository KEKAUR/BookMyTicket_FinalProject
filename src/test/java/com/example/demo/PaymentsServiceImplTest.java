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

import com.capgemini.movieticketbooking.exception.PaymentNotFoundException;
import com.capgemini.movieticketbooking.model.Payments;
import com.capgemini.movieticketbooking.repository.PaymentsRepository;
import com.capgemini.movieticketbooking.service.impl.PaymentsServiceImpl;

public class PaymentsServiceImplTest {

    @Mock
    private PaymentsRepository paymentRepository;

    @InjectMocks
    private PaymentsServiceImpl paymentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPayment() {
        Payments newPayment = new Payments();
        when(paymentRepository.save(newPayment)).thenReturn(newPayment);

        Payments savedPayment = paymentService.addPayment(newPayment);

        assertNotNull(savedPayment);
        verify(paymentRepository, times(1)).save(newPayment);
    }

    @Test
    public void testGetAllPayments() {
        List<Payments> mockPayments = new ArrayList<>();
        mockPayments.add(new Payments());
        mockPayments.add(new Payments());

        when(paymentRepository.findAll()).thenReturn(mockPayments);

        List<Payments> payments = paymentService.getAllPayments();

        assertEquals(2, payments.size());
        verify(paymentRepository, times(1)).findAll();
    }

    @Test
    public void testGetPaymentById_ExistingId() throws PaymentNotFoundException {
        int existingId = 1;
        Payments mockPayment = new Payments();
        mockPayment.setPaymentId(existingId);

        when(paymentRepository.findById(existingId)).thenReturn(Optional.of(mockPayment));

        Payments payment = paymentService.getPaymentById(existingId);

        assertNotNull(payment);
        assertEquals(existingId, payment.getPaymentId());
        verify(paymentRepository, times(1)).findById(existingId);
    }

    @Test
    public void testGetPaymentById_NonExistingId() {
        int nonExistingId = 100;

        when(paymentRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(PaymentNotFoundException.class, () -> {
            paymentService.getPaymentById(nonExistingId);
        });
        verify(paymentRepository, times(1)).findById(nonExistingId);
    }

    // Add similar tests for getPaymentsByUserId, getPaymentsByDate, and getPaymentsByStatus methods

    @Test
    public void testDeletePaymentById_ExistingId() throws PaymentNotFoundException {
        int existingId = 1;

        when(paymentRepository.existsById(existingId)).thenReturn(true);

        paymentService.deletePaymentById(existingId);

        verify(paymentRepository, times(1)).deleteById(existingId);
    }

    @Test
    public void testDeletePaymentById_NonExistingId() {
        int nonExistingId = 100;

        when(paymentRepository.existsById(nonExistingId)).thenReturn(false);

        assertThrows(PaymentNotFoundException.class, () -> {
            paymentService.deletePaymentById(nonExistingId);
        });
        verify(paymentRepository, times(0)).deleteById(any()); // No delete should occur
    }

    @Test
    public void testUpdatePaymentById_ExistingId() throws PaymentNotFoundException {
        int existingId = 1;
        Payments updatedPayment = new Payments();
        updatedPayment.setAmount(100);

        Payments mockPayment = new Payments();
        mockPayment.setPaymentId(existingId);

        when(paymentRepository.findById(existingId)).thenReturn(Optional.of(mockPayment));
        when(paymentRepository.save(any())).thenReturn(updatedPayment);

        paymentService.updatePaymentById(existingId, updatedPayment);

        assertEquals(100, mockPayment.getAmount());
        verify(paymentRepository, times(1)).save(mockPayment);
    }

    @Test
    public void testUpdatePaymentById_NonExistingId() {
        int nonExistingId = 100;
        Payments updatedPayment = new Payments();
        updatedPayment.setAmount(100);

        when(paymentRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(PaymentNotFoundException.class, () -> {
            paymentService.updatePaymentById(nonExistingId, updatedPayment);
        });
        verify(paymentRepository, times(0)).save(any()); // No save should occur
    }
    
    @Test
    public void testGetPaymentsByUserId() {
        int userId = 1;
        List<Payments> mockPayments = new ArrayList<>();
        mockPayments.add(new Payments());
        mockPayments.add(new Payments());

        when(paymentRepository.findByUserId(userId)).thenReturn(mockPayments);

        List<Payments> payments = paymentService.getPaymentsByUserId(userId);

        assertEquals(2, payments.size());
        verify(paymentRepository, times(1)).findByUserId(userId);
    }

    @Test
    public void testGetPaymentsByDate() {
        String paymentDate = "2023-04-17"; // Example date
        List<Payments> mockPayments = new ArrayList<>();
        mockPayments.add(new Payments());
        mockPayments.add(new Payments());

        when(paymentRepository.findByPaymentDate(paymentDate)).thenReturn(mockPayments);

        List<Payments> payments = paymentService.getPaymentsByDate(paymentDate);

        assertEquals(2, payments.size());
        verify(paymentRepository, times(1)).findByPaymentDate(paymentDate);
    }

    @Test
    public void testGetPaymentsByStatus() {
        String paymentStatus = "success"; // Example status
        List<Payments> mockPayments = new ArrayList<>();
        mockPayments.add(new Payments());
        mockPayments.add(new Payments());

        when(paymentRepository.findByPaymentStatus(paymentStatus)).thenReturn(mockPayments);

        List<Payments> payments = paymentService.getPaymentsByStatus(paymentStatus);

        assertEquals(2, payments.size());
        verify(paymentRepository, times(1)).findByPaymentStatus(paymentStatus);
    }
}
