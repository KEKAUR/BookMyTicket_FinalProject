package com.capgemini.movieticketbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@RestController
public class MovieTicketBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieTicketBookingSystemApplication.class, args);
		
		System.out.println("<=============================== Application UP ===============================>");
	}
	
	@GetMapping("hello")
	public String hello() {
		return "Hello from movie booking app";
	}
	
}