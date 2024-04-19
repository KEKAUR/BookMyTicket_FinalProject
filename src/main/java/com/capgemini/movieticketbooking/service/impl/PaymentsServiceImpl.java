package com.capgemini.movieticketbooking.service.impl;

import com.capgemini.movieticketbooking.exception.PaymentNotFoundException;
import com.capgemini.movieticketbooking.model.Payments;
import com.capgemini.movieticketbooking.repository.PaymentsRepository;
import com.capgemini.movieticketbooking.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;
 
@Service
public class PaymentsServiceImpl implements PaymentService {
 
    @Autowired
    private PaymentsRepository paymentRepository;
 
    public Payments addPayment(Payments payment) {
        return paymentRepository.save(payment);
    }
 
    public List<Payments> getAllPayments() {
        return paymentRepository.findAll();
    }
 
    public Payments getPaymentById(int paymentId) throws PaymentNotFoundException {
        Optional<Payments> optionalPayment = paymentRepository.findById(paymentId);
        if (optionalPayment.isPresent()) {
            return optionalPayment.get();
        } else {
            throw new PaymentNotFoundException("Payment not found with id: " + paymentId);
        }
    }
 
    public List<Payments> getPaymentsByUserId(int userId) {
    	
        return paymentRepository.findByUserId(userId);
    }
 
    public List<Payments> getPaymentsByDate(String paymentDate) {
    	
        return paymentRepository.findByPaymentDate(paymentDate);
    }
 
    public List<Payments> getPaymentsByStatus(String paymentStatus) {
        return paymentRepository.findByPaymentStatus(paymentStatus);
    }
 
    // You can add more methods for specific queries as needed
 
    public void deletePaymentById(int paymentId) throws PaymentNotFoundException {
        if (paymentRepository.existsById(paymentId)) {
            paymentRepository.deleteById(paymentId);
        } else {
            throw new PaymentNotFoundException("Payment not found with id: " + paymentId);
        }
    }
 
    public void updatePaymentById(int paymentId, Payments updatedPayment) throws PaymentNotFoundException {
        Optional<Payments> optionalPayment = paymentRepository.findById(paymentId);
        if (optionalPayment.isPresent()) {
            Payments existingPayment = optionalPayment.get();
            existingPayment.setAmount(updatedPayment.getAmount());
            existingPayment.setPaymentStatus(updatedPayment.getPaymentStatus());
            // Update other fields as needed
            paymentRepository.save(existingPayment);
        } else {
            throw new PaymentNotFoundException("Payment not found with id: " + paymentId);
        }
    }

//	@Override
//	public List<Payments> getPaymentsByUserId(String paymentStatus) {
//		// TODO Auto-generated method stub
//		return paymentRepository.findByUserId(userId);
//	}
}
