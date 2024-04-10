package com.capgemini.movieticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.movieticketbooking.exception.PaymentNotFoundException;
import com.capgemini.movieticketbooking.model.Payments;
import com.capgemini.movieticketbooking.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<Payments>> getAllPayments() {
        try {
            List<Payments> payments = paymentService.getAllPayments();
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> addPayment(@RequestBody Payments payment) {
        try {
            paymentService.addPayment(payment);
            return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create record");
        }
    }

    @GetMapping("/{payment_id}")
    public ResponseEntity<Payments> getPaymentById(@PathVariable("payment_id") int paymentId) throws PaymentNotFoundException {
        try {
            Payments payment = paymentService.getPaymentById(paymentId);
            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<Payments>> getPaymentsByUserId(@PathVariable("user_id") int userId) {
        try {
            List<Payments> payments = paymentService.getPaymentsByUserId(userId);
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/date/{payment_date}")
    public ResponseEntity<List<Payments>> getPaymentsByDate(@PathVariable("payment_date") String paymentDate) {
        try {
            List<Payments> payments = paymentService.getPaymentsByDate(paymentDate);
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/status/{payment_status}")
    public ResponseEntity<List<Payments>> getPaymentsByStatus(@PathVariable("payment_status") String paymentStatus) {
        try {
            List<Payments> payments = paymentService.getPaymentsByStatus(paymentStatus);
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @GetMapping("/tickets/{user_id}")
//    public ResponseEntity<List<Payments>> getPaymentsByUserId1(@PathVariable("user_id") Long userId) {
//        try {
//            List<Payments> payments = paymentService.getPaymentsByUserId(userId);
//            return ResponseEntity.ok(payments);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}
