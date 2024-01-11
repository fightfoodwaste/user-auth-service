package com.fightfoodwaste.authservice.service;

import com.fightfoodwaste.authservice.DTO.*;
import com.fightfoodwaste.authservice.repository.UserCredentialRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AuthenticationServiceImplTest {

    @Mock
    private UserCredentialRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtServiceImpl jwtServiceImpl;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /*@Test
    public void testSaveUser_Success() {
        // Mocking
        RegisterRequest credential = new RegisterRequest(); // Assuming a constructor or setters
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        // Execution
        RegisteredResponse response = authenticationService.saveUser(credential);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("Successful registration", response.getMessage());
    }*/

    @Test
    public void testSaveUser_NullCredential() {
        RegisterRequest userCredential = null;
        RegisteredResponse response = authenticationService.saveUser(userCredential);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
        assertEquals("Invalid request", response.getMessage());
    }

    @Test
    public void testGenerateToken() {
        // Mocking
        String expectedToken = "token";
        when(jwtServiceImpl.generateToken(anyString())).thenReturn(expectedToken);

        // Execution
        AuthResponse response = authenticationService.generateToken(new AuthRequest("username", "password"));

        // Assertions
        assertEquals(expectedToken, response.getAuth_token());
    }

    @Test
    public void testValidateToken() {
        // Mocking
        doNothing().when(jwtServiceImpl).validateToken(anyString());

        // Execution
        authenticationService.validateToken(new ValidateRequest("validToken"));

        // Verification
        verify(jwtServiceImpl).validateToken("validToken");
    }

    // Additional tests to handle exceptions and other edge cases
}
