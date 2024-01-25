package com.fightfoodwaste.authservice.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserCredentialTest {

    @Test
    void testSettersAndGetters() {
        UserCredential userCredential = new UserCredential();
        userCredential.setId(1L);
        userCredential.setUsername("testUser");
        userCredential.setPassword("testPass");

        assertEquals(1L, userCredential.getId());
        assertEquals("testUser", userCredential.getUsername());
        assertEquals("testPass", userCredential.getPassword());
    }

    @Test
    void testConstructorWithParameters() {
        UserCredential userCredential = new UserCredential("testUser", "testPass");
        assertEquals("testUser", userCredential.getUsername());
        assertEquals("testPass", userCredential.getPassword());
    }
}
