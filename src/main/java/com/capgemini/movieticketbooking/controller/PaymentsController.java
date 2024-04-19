package com.capgemini.movieticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.capgemini.movieticketbooking.exception.PaymentNotFoundException;
import com.capgemini.movieticketbooking.model.Payments;
import com.capgemini.movieticketbooking.service.PaymentService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<Payments>> getAllPayments() {
        List<Payments> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @PostMapping
    public ResponseEntity<String> addPayment(@Valid @RequestBody Payments payment) {
        paymentService.addPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
    }

    @GetMapping("/{payment_id}")
    public ResponseEntity<Payments> getPaymentById(@PathVariable("payment_id") int paymentId) throws PaymentNotFoundException {
        Payments payment = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<Payments>> getPaymentsByUserId(@PathVariable("user_id") int userId) {
        List<Payments> payments = paymentService.getPaymentsByUserId(userId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/date/{payment_date}")
    public ResponseEntity<List<Payments>> getPaymentsByDate(@PathVariable("payment_date") String paymentDate) {
        List<Payments> payments = paymentService.getPaymentsByDate(paymentDate);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/status/{payment_status}")
    public ResponseEntity<List<Payments>> getPaymentsByStatus(@PathVariable("payment_status") String paymentStatus) {
        List<Payments> payments = paymentService.getPaymentsByStatus(paymentStatus);
        return ResponseEntity.ok(payments);
    }

}
