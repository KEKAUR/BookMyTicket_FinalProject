//package com.example.demo;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import com.capgemini.movieticketbooking.controller.UserController;
//import com.capgemini.movieticketbooking.exception.UserNotFoundException;
//import com.capgemini.movieticketbooking.model.Address;
//import com.capgemini.movieticketbooking.model.User;
//import com.capgemini.movieticketbooking.service.UserService;
//
//public class UserControllerTests {
//	
//	@Test
//    public void testAddUser() {
//        // Create a mock UserService
//        UserService userService = mock(UserService.class);
//        
//        // Create a UserController instance and inject the mock UserService
//        UserController userController = new UserController();
//        userController.setUserService(userService);
//
//        // Create a sample user
//        User user = new User();
//        user.setId(1);
//        user.setUsername("testuser");
//        user.setEmail("test@example.com");
//
//        // Mock the behavior of the userService.addUser() method
//        when(userService.addUser(any(User.class))).thenReturn(true);
//
//        // Call the addUser method in UserController
//        ResponseEntity<String> response = userController.addUser(user);
//
//        // Verify the response
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals("Record Created Successfully", response.getBody());
//    }
//
//    @Test
//    public void testGetAllUsers() {
//        UserService userService = mock(UserService.class);
//        UserController userController = new UserController();
//        //userController.setUserService(userService);
//
//        List<User> userList = new ArrayList<>();
//        userList.add(new User(1, "user1", "user1@example.com", "123456789", "user1password", "ADMIN", null, new Address()));
//        userList.add(new User(2, "user2", "user2@example.com", "123456789", "user2password", "USER", null, new Address()));
//
//        when(userService.getAllUsers()).thenReturn(userList);
//
//        ResponseEntity<List<User>> response = userController.getAllUsers();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(userList.size(), response.getBody().size());
//    }
//
//    @Test
//    public void testGetUserById() throws UserNotFoundException {
//        UserService userService = mock(UserService.class);
//        UserController userController = new UserController();
//        userController.setUserService(userService);
//
//        User user = new User(1, "testuser", "test@example.com");
//        when(userService.getUserById(1)).thenReturn(user);
//
//        ResponseEntity<User> response = userController.getUserById(1);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(user.getId(), response.getBody().getId());
//    }
//
//
//    @Test
//    public void testUpdateUserById() throws UserNotFoundException {
//        UserService userService = mock(UserService.class);
//        UserController userController = new UserController();
//        userController.setUserService(userService);
//
//        User userToUpdate = new User(1, "updatedUser", "updateduser@example.com");
//        when(userService.updateUserById(1, userToUpdate)).thenReturn(true);
//
//        ResponseEntity<String> response = userController.updateUserById(1, userToUpdate);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Record Updated Successfully", response.getBody());
//    }
//
//    @Test
//    public void testDeleteUserById() throws UserNotFoundException {
//        UserService userService = mock(UserService.class);
//        UserController userController = new UserController();
//        userController.setUserService(userService);
//
//        when(userService.deleteUserById(1)).thenReturn(true);
//
//        ResponseEntity<String> response = userController.deleteUserById(1);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Record Deleted Successfully", response.getBody());
//    }
//
//    @Test
//    public void testGetUserByEmail() throws UserNotFoundException {
//        UserService userService = mock(UserService.class);
//        UserController userController = new UserController();
//        userController.setUserService(userService);
//
//        User userByEmail = new User(1, "testuser", "test@example.com");
//        when(userService.getUserByEmail("test@example.com")).thenReturn(userByEmail);
//
//        ResponseEntity<User> response = userController.getUserByEmail("test@example.com");
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(userByEmail.getEmail(), response.getBody().getEmail());
//    }
//
//    @Test
//    public void testGetUserByName() throws UserNotFoundException {
//        UserService userService = mock(UserService.class);
//        UserController userController = new UserController();
//        userController.setUserService(userService);
//
//        User userByName = new User(1, "testuser", "test@example.com");
//        when(userService.getUserByName("testuser")).thenReturn(userByName);
//
//        ResponseEntity<User> response = userController.getUserByName("testuser");
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(userByName.getUsername(), response.getBody().getUsername());
//    }
//
//    @Test
//    public void testGetUserPaymentsById() throws UserNotFoundException {
//        UserService userService = mock(UserService.class);
//        UserController userController = new UserController();
//        userController.setUserService(userService);
//
//        List<Payments> paymentsList = new ArrayList<>();
//        // Add sample payments to the list
//        paymentsList.add(new Payments(1, "Payment1", 100.0));
//        paymentsList.add(new Payments(2, "Payment2", 150.0));
//
//        when(userService.getUserPaymentsById(1)).thenReturn(paymentsList);
//
//        ResponseEntity<List<Payments>> response = userController.getUserPaymentsById(1);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(paymentsList.size(), response.getBody().size());
//    }
//}
