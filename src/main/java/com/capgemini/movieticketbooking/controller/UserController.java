package com.capgemini.movieticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.movieticketbooking.exception.UserNotFoundException;
import com.capgemini.movieticketbooking.model.Payments;
import com.capgemini.movieticketbooking.model.User;
import com.capgemini.movieticketbooking.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<String> addUser(@RequestBody User user) {
		try {
			userService.addUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create record");
		}
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		try {
			List<User> users = userService.getAllUsers();
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/{user_id}")
	public ResponseEntity<User> getUserById(@PathVariable("user_id") int userId) throws UserNotFoundException {
		try {
			User user = userService.getUserById(userId);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/{user_id}")
	public ResponseEntity<String> updateUserById(@PathVariable("user_id") int userId, @RequestBody User user)
			throws UserNotFoundException {
		try {
			userService.updateUserById(userId, user);
			return ResponseEntity.ok("Record Updated Successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update record");
		}
	}

	@DeleteMapping("/{user_id}")
	public ResponseEntity<String> deleteUserById(@PathVariable("user_id") int userId) throws UserNotFoundException {
		try {
			userService.deleteUserById(userId);
			return ResponseEntity.ok("Record Deleted Successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete record");
		}
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) throws UserNotFoundException {
		try {
			User user = userService.getUserByEmail(email);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<User> getUserByName(@PathVariable("name") String name) throws UserNotFoundException {
		try {
			User user = userService.getUserByName(name);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/payments/{user_id}")
	public ResponseEntity<List<Payments>> getUserPaymentsById(@PathVariable("user_id") int userId)
			throws UserNotFoundException {
		try {
			List<Payments> payments = userService.getUserPaymentsById(userId);
			return ResponseEntity.ok(payments);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
