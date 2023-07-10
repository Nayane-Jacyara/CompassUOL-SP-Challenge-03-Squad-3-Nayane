package br.com.compassuol.pb.challenge.msuser.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class UserTest {

    private User user;
    private Role role1;
    private Role role2;

    @BeforeEach
    public void setUp() {
        user = new User("John", "Doe", "john.doe@example.com", "password");
        role1 = new Role("ROLE_ADMIN");
        role2 = new Role("ROLE_USER");
    }

    @Test
    public void testUserCreation() {
        Assertions.assertEquals("John", user.getFirstName());
        Assertions.assertEquals("Doe", user.getLastName());
        Assertions.assertEquals("john.doe@example.com", user.getEmail());
        Assertions.assertEquals("password", user.getPassword());
        Assertions.assertTrue(user.getRoles().isEmpty());
    }

    @Test
    public void testSettersAndGetters() {
        user.setFirstName("Jane");
        Assertions.assertEquals("Jane", user.getFirstName());

        user.setLastName("Smith");
        Assertions.assertEquals("Smith", user.getLastName());

        user.setEmail("jane.smith@example.com");
        Assertions.assertEquals("jane.smith@example.com", user.getEmail());

        user.setPassword("newPassword");
        Assertions.assertEquals("newPassword", user.getPassword());

        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        roles.add(role2);

        user.setRoles(roles);
        Assertions.assertEquals(2, user.getRoles().size());
        Assertions.assertTrue(user.getRoles().contains(role1));
        Assertions.assertTrue(user.getRoles().contains(role2));
    }

    @Test
    public void testEqualsAndHashCode() {
        User user1 = new User("John", "Doe", "john.doe@example.com", "password");
        User user2 = new User("John", "Doe", "john.doe@example.com", "password");

        Assertions.assertEquals(user1, user2);
        Assertions.assertEquals(user1.hashCode(), user2.hashCode());
    }
}
