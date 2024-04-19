package com.capgemini.movieticketbooking.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.capgemini.movieticketbooking.exception.AddressNotFoundException;
import com.capgemini.movieticketbooking.exception.MovieNotFoundException;
import com.capgemini.movieticketbooking.exception.PaymentNotFoundException;
import com.capgemini.movieticketbooking.exception.SeatNotFoundException;
import com.capgemini.movieticketbooking.exception.ShowNotFoundException;
import com.capgemini.movieticketbooking.exception.TheatreNotFoundException;
import com.capgemini.movieticketbooking.exception.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
	   log.info("Inside Handle Method Argument Not Valid Exception");
	   
       Map<String, Object> body = new HashMap<>();
       body.put("status", status.value());
       body.put("timestamp", LocalDateTime.now());
       List<String> errors = ex.getBindingResult().getFieldErrors().stream()
               .map(error -> error.getField() + ": " + error.getDefaultMessage())
               .collect(Collectors.toList());
       body.put("errors", errors);

       return new ResponseEntity<>(body, headers, status);
	} 

    @ExceptionHandler(AddressNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleAddressNotFoundException(AddressNotFoundException e) {
    	log.info("Inside Handle Address Not Found Exception");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found with id: " + e.getMessage());
    }
    
    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<String> handleMovieNotFoundException(MovieNotFoundException ex) {
    	log.info("Inside Handle Movie Not Found Exception");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    
    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<String> handlePaymentNotFoundException(PaymentNotFoundException ex) {
    	log.info("Inside Handle Payment Not Found Exception");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }	
    
    @ExceptionHandler(TheatreNotFoundException.class)
    public ResponseEntity<String> handleTheatreNotFoundException(Exception e) {
    	log.info("Inside Handle Theatre Not Found Exception");
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Theatre Not Found: " + e.getMessage());
    }

    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<String> handleSeatNotFoundException(SeatNotFoundException ex) {
    	log.info("Inside Handle Seat Not Found Exception");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    
    @ExceptionHandler(ShowNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleShowNotFoundException(ShowNotFoundException e) {
    	log.info("Inside Handle Show Not Found Exception");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Show Not Found: " + e.getMessage());
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
    	log.info("Inside Handle User Not Found Exception");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found: " + e.getMessage());
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGlobalException(Exception e) {
    	log.info("Inside Handle Global Exception");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error. Check Request Body and API Endpoint.");
    }

}
