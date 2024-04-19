package com.capgemini.movieticketbooking.service.impl;

import com.capgemini.movieticketbooking.exception.UserNotFoundException;
import com.capgemini.movieticketbooking.model.Payments;
import com.capgemini.movieticketbooking.model.User;
import com.capgemini.movieticketbooking.repository.UserRepository;
import com.capgemini.movieticketbooking.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;
 
@Service
public class UserServiceImpl implements UserService{
 
    @Autowired
    private UserRepository userRepository;
 
    public User addUser(User user) {
    	user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }
 
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
 
    public User getUserById(int userId) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }
 
    public void updateUserById(int userId, User user) throws UserNotFoundException {
        User existingUser = getUserById(userId);
        existingUser.setUserName(user.getUserName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        userRepository.save(existingUser);
    }
 
    public void deleteUserById(int userId) throws UserNotFoundException {
        userRepository.deleteById(userId);
    }
 
    public User getUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }
 
    public User getUserByName(String name) throws UserNotFoundException {
        return userRepository.findByUserName(name)
                .orElseThrow(() -> new UserNotFoundException("User not found with name: " + name));
    }
 
    public List<Payments> getUserPaymentsById(int userId) throws UserNotFoundException {
        return getUserById(userId).getPayments();
    }

}
