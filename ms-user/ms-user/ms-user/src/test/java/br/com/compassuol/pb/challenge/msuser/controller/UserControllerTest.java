package br.com.compassuol.pb.challenge.msuser.controller;

import br.com.compassuol.pb.challenge.msuser.entity.User;
import br.com.compassuol.pb.challenge.msuser.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class UserControllerTest {

    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService);
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("John", "Doe", "john.doe@example.com", "password"));
        userList.add(new User("Jane", "Smith", "jane.smith@example.com", "password"));
        when(userService.getAllUsers()).thenReturn(userList);

        ResponseEntity<List<User>> response = userController.getAllUser();
        List<User> retrievedUsers = response.getBody();

        verify(userService).getAllUsers();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Assertions.assertEquals(userList, retrievedUsers);
    }

    @Test
    public void testGetUserById() {

        Long userId = 1L;


        User existingUser = new User("John", "Doe", "john.doe@example.com", "password");
        when(userService.getUserById(userId)).thenReturn(existingUser);

        ResponseEntity<User> response = userController.getUserById(userId);
        User retrievedUser = response.getBody();

        verify(userService).getUserById(userId);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Assertions.assertEquals(existingUser, retrievedUser);
    }

    @Test
    public void testCreateUser() {
        User inputUser = new User("John", "Doe", "john.doe@example.com", "password");

        User createdUser = new User("John", "Doe", "john.doe@example.com", "password");
        when(userService.createUser(inputUser)).thenReturn(createdUser);


        ResponseEntity<User> response = userController.createUser(inputUser);
        User returnedUser = response.getBody();

        verify(userService).createUser(inputUser);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Assertions.assertEquals(createdUser, returnedUser);
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        User updatedUser = new User("Jane", "Smith", "jane.smith@example.com", "newPassword");

        User updated = new User("Jane", "Smith", "jane.smith@example.com", "newPassword");
        when(userService.updateUser(userId, updatedUser)).thenReturn(updated);

        ResponseEntity<User> response = userController.updateUser(userId, updatedUser);
        User returnedUser = response.getBody();

        verify(userService).updateUser(userId, updatedUser);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Assertions.assertEquals(updated, returnedUser);
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        ResponseEntity<Void> response = userController.deleteUser(userId);

        verify(userService).deleteUser(userId);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
