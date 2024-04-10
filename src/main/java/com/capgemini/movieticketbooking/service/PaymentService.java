package com.capgemini.movieticketbooking.service;

import java.util.List;

import com.capgemini.movieticketbooking.exception.PaymentNotFoundException;
import com.capgemini.movieticketbooking.model.Payments;

public interface PaymentService {

	List<Payments> getAllPayments();

	void addPayment(Payments payment);

	Payments getPaymentById(int paymentId) throws PaymentNotFoundException;

	List<Payments> getPaymentsByUserId(int userId);

	List<Payments> getPaymentsByDate(String paymentDate);

	List<Payments> getPaymentsByStatus(String paymentStatus);
	
//	List<Payments> getPaymentsByUserId(String paymentStatus);

}
