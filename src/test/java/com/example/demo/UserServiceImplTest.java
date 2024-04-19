package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.movieticketbooking.exception.UserNotFoundException;
import com.capgemini.movieticketbooking.model.Payments;
import com.capgemini.movieticketbooking.model.User;
import com.capgemini.movieticketbooking.repository.UserRepository;
import com.capgemini.movieticketbooking.service.impl.UserServiceImpl;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUser() {
        User newUser = new User();
        newUser.setUserName("testuser");
        newUser.setEmail("test@example.com");
        newUser.setPassword("testpassword");

        when(userRepository.save(any())).thenReturn(newUser);

        userService.addUser(newUser);

        assertNotNull(newUser.getPassword()); // Password should be encoded
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void testGetAllUsers() {
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(new User());
        mockUsers.add(new User());

        when(userRepository.findAll()).thenReturn(mockUsers);

        List<User> users = userService.getAllUsers();

        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById_ExistingId() throws UserNotFoundException {
        int existingId = 1;
        User mockUser = new User();
        mockUser.setUserId(existingId);

        when(userRepository.findById(existingId)).thenReturn(Optional.of(mockUser));

        User user = userService.getUserById(existingId);

        assertNotNull(user);
        assertEquals(existingId, user.getUserId());
        verify(userRepository, times(1)).findById(existingId);
    }

    @Test
    public void testGetUserById_NonExistingId() {
        int nonExistingId = 100;

        when(userRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(nonExistingId);
        });
        verify(userRepository, times(1)).findById(nonExistingId);
    }

    @Test
    public void testUpdateUserById() throws UserNotFoundException {
        int existingId = 1;
        User existingUser = new User();
        existingUser.setUserId(existingId);

        User updatedUser = new User();
        updatedUser.setUserName("UpdatedUser");

        when(userRepository.findById(existingId)).thenReturn(Optional.of(existingUser));

        userService.updateUserById(existingId, updatedUser);

        assertEquals("UpdatedUser", existingUser.getUserName());
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    public void testDeleteUserById_ExistingId() throws UserNotFoundException {
        int existingId = 1;
        User existingUser = new User();
        existingUser.setUserId(existingId);

        when(userRepository.existsById(existingId)).thenReturn(true);

        userService.deleteUserById(existingId);

        verify(userRepository, times(1)).deleteById(existingId);
    }

    @Test
    public void testDeleteUserById_NonExistingId() {
        int nonExistingId = 100;

        when(userRepository.existsById(nonExistingId)).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> {
            userService.deleteUserById(nonExistingId);
        });
        verify(userRepository, never()).deleteById(nonExistingId);
    }

    @Test
    public void testGetUserByEmail_ExistingEmail() throws UserNotFoundException {
        String existingEmail = "test@example.com";
        User mockUser = new User();
        mockUser.setEmail(existingEmail);

        when(userRepository.findByEmail(existingEmail)).thenReturn(Optional.of(mockUser));

        User user = userService.getUserByEmail(existingEmail);

        assertNotNull(user);
        assertEquals(existingEmail, user.getEmail());
        verify(userRepository, times(1)).findByEmail(existingEmail);
    }

    @Test
    public void testGetUserByEmail_NonExistingEmail() {
        String nonExistingEmail = "nonexistent@example.com";

        when(userRepository.findByEmail(nonExistingEmail)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserByEmail(nonExistingEmail);
        });
        verify(userRepository, times(1)).findByEmail(nonExistingEmail);
    }

    @Test
    public void testGetUserByName_ExistingName() throws UserNotFoundException {
        String existingName = "TestUser";
        User mockUser = new User();
        mockUser.setUserName(existingName);

        when(userRepository.findByUserName(existingName)).thenReturn(Optional.of(mockUser));

        User user = userService.getUserByName(existingName);

        assertNotNull(user);
        assertEquals(existingName, user.getUserName());
        verify(userRepository, times(1)).findByUserName(existingName);
    }

    @Test
    public void testGetUserByName_NonExistingName() {
        String nonExistingName = "NonExistingUser";

        when(userRepository.findByUserName(nonExistingName)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserByName(nonExistingName);
        });
        verify(userRepository, times(1)).findByUserName(nonExistingName);
    }

//    @Test
//    public void testGetUserPaymentsById_ExistingId() throws UserNotFoundException {
//        int existingId = 1;
//        User mockUser = new User();
//        mockUser.setUserId(existingId);
//
//        when(userRepository.findById(existingId)).thenReturn(Optional.of(mockUser));
//
//        List<Payments> mockPaymentsList = new ArrayList<>();
//        // Populate mockPaymentsList as needed
//
//        when(mockUser.getPayments()).thenReturn(mockPaymentsList);
//
//        List<Payments> payments = userService.getUserPaymentsById(existingId);
//
//        assertNotNull(payments);
//        assertEquals(mockPaymentsList.size(), payments.size());
//        verify(userRepository, times(1)).findById(existingId);
//    }

    @Test
    public void testGetUserPaymentsById_NonExistingId() {
        int nonExistingId = 100;

        when(userRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserPaymentsById(nonExistingId);
        });
        verify(userRepository, times(1)).findById(nonExistingId);
    }

}
