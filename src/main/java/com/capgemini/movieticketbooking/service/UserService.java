package com.capgemini.movieticketbooking.service;

import java.util.List;

import com.capgemini.movieticketbooking.exception.UserNotFoundException;
import com.capgemini.movieticketbooking.model.Address;
import com.capgemini.movieticketbooking.model.Payments;
import com.capgemini.movieticketbooking.model.User;

public interface UserService {

	List<User> getAllUsers();

	User getUserById(int userId) throws UserNotFoundException;

	void updateUserById(int userId, User user) throws UserNotFoundException;

	void deleteUserById(int userId) throws UserNotFoundException;

	//List<User> getUsersByCity(String city) throws UserNotFoundException;

	User getUserByEmail(String email) throws UserNotFoundException;

	User getUserByName(String name) throws UserNotFoundException;

	List<Payments> getUserPaymentsById(int userId) throws UserNotFoundException;

	void addUser(User user) throws UserNotFoundException;

}
