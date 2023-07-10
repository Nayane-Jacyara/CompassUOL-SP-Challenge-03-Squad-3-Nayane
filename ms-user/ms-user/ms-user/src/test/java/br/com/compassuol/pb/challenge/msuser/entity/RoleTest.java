package br.com.compassuol.pb.challenge.msuser.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoleTest {

    private Role role1;
    private Role role2;

    @BeforeEach
    public void setUp() {
        role1 = new Role("ROLE_ADMIN");
        role2 = new Role("ROLE_USER");
    }

    @Test
    public void testRoleCreation() {
        Assertions.assertEquals("ROLE_ADMIN", role1.getName());
        Assertions.assertEquals("ROLE_USER", role2.getName());
    }

    @Test
    public void testSettersAndGetters() {
        role1.setName("ROLE_SUPER_ADMIN");
        Assertions.assertEquals("ROLE_SUPER_ADMIN", role1.getName());

        role2.setName("ROLE_MODERATOR");
        Assertions.assertEquals("ROLE_MODERATOR", role2.getName());
    }

    @Test
    public void testEqualsAndHashCode() {
        Role role1Copy = new Role("ROLE_ADMIN");

        Assertions.assertEquals(role1, role1Copy);
        Assertions.assertEquals(role1.hashCode(), role1Copy.hashCode());

        Assertions.assertNotEquals(role1, role2);
        Assertions.assertNotEquals(role1.hashCode(), role2.hashCode());
    }
}
