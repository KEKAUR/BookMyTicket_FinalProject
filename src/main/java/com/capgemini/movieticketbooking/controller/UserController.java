package com.capgemini.movieticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.capgemini.movieticketbooking.dto.UserDTO;
import com.capgemini.movieticketbooking.exception.UserNotFoundException;
import com.capgemini.movieticketbooking.model.Payments;
import com.capgemini.movieticketbooking.model.User;
import com.capgemini.movieticketbooking.service.UserService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/")
    public ResponseEntity<String> addUser(@Valid @RequestBody User user) throws UserNotFoundException {
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUserById(@PathVariable("user_id") int userId) throws UserNotFoundException {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<String> updateUserById(@PathVariable("user_id") int userId, @Valid @RequestBody User user)
            throws UserNotFoundException {
        userService.updateUserById(userId, user);
        return ResponseEntity.ok("Record Updated Successfully");
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("user_id") int userId) throws UserNotFoundException {
        userService.deleteUserById(userId);
        return ResponseEntity.ok("Record Deleted Successfully");
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) throws UserNotFoundException {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable("name") String name) throws UserNotFoundException {
        User user = userService.getUserByName(name);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/payments/{user_id}")
    public ResponseEntity<List<Payments>> getUserPaymentsById(@PathVariable("user_id") int userId)
            throws UserNotFoundException {
        List<Payments> payments = userService.getUserPaymentsById(userId);
        return ResponseEntity.ok(payments);
    }
    
   	@PostMapping("/register")
   	public ResponseEntity<Object> registerUser(@RequestBody User user) throws UserNotFoundException {
   		User newUser = null;
   		try {
   			if (userService.getUserByEmail(user.getEmail()) != null) {
   				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User already exists");
   			}
   		}
   		catch (UserNotFoundException e) {
   			newUser = userService.addUser(user);
   		}
   		catch (Exception e) {
   			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
   		}
   		return ResponseEntity.ok().body(new CustomResponse("Login successful", newUser));
   	}
   	
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody UserDTO userDto) throws UserNotFoundException {
        User user = new User();
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        User existingUser = userService.getUserByEmail(user.getEmail());
        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            return ResponseEntity.ok().body(new CustomResponse("Login successful", existingUser));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new CustomResponse("Invalid username or password", null));
        }
    }

    // Custom response object to include message and user object
    public static class CustomResponse {
        private String message;
        private User user;

        public CustomResponse(String message, User user) {
            this.message = message;
            this.user = user;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }
}
