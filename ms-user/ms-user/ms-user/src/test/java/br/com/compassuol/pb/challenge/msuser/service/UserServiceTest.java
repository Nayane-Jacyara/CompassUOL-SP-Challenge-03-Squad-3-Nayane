package br.com.compassuol.pb.challenge.msuser.service;

import br.com.compassuol.pb.challenge.msuser.entity.User;
import br.com.compassuol.pb.challenge.msuser.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void testGetUserById() {

        Long userId = 1L;

        User existingUser = new User("John", "Doe", "john.doe@example.com", "password");
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        User retrievedUser = userService.getUserById(userId);

        verify(userRepository).findById(userId);

        Assertions.assertEquals(existingUser, retrievedUser);
    }

    @Test
    public void testGetUserByIdWithInvalidId() {

        Long invalidUserId = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.getUserById(invalidUserId);
        });
    }

    @Test
    public void testGetUserByIdWithNonexistentId() {

        Long nonexistentUserId = 1L;

        when(userRepository.findById(nonexistentUserId)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            userService.getUserById(nonexistentUserId);
        });
    }

    @Test
    public void testUpdateUser() {

        Long userId = 1L;
        User updatedUser = new User("Jane", "Smith", "jane.smith@example.com", "newPassword");

        User existingUser = new User("John", "Doe", "john.doe@example.com", "password");
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        User savedUser = new User("Jane", "Smith", "jane.smith@example.com", "newPassword");
        when(userRepository.save(existingUser)).thenReturn(savedUser);

        User updated = userService.updateUser(userId, updatedUser);

        verify(userRepository).findById(userId);

        verify(userRepository).save(existingUser);

        Assertions.assertEquals(savedUser, updated);
        Assertions.assertEquals(updatedUser.getFirstName(), updated.getFirstName());
        Assertions.assertEquals(updatedUser.getLastName(), updated.getLastName());
        Assertions.assertEquals(updatedUser.getEmail(), updated.getEmail());
        Assertions.assertEquals(updatedUser.getPassword(), updated.getPassword());
    }

    @Test
    public void testDeleteUser() {

        Long userId = 1L;

        User existingUser = new User("John", "Doe", "john.doe@example.com", "password");
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        userService.deleteUser(userId);

        verify(userRepository).findById(userId);

        verify(userRepository).delete(existingUser);
    }
}
