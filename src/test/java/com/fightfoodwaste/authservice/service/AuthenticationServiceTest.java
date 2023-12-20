package com.fightfoodwaste.authservice.service;
import com.fightfoodwaste.authservice.DTO.AuthRequest;
import com.fightfoodwaste.authservice.DTO.AuthResponse;
import com.fightfoodwaste.authservice.DTO.ValidateRequest;
import com.fightfoodwaste.authservice.DTO.RegisteredResponse;
import com.fightfoodwaste.authservice.entity.UserCredential;
import com.fightfoodwaste.authservice.repository.UserCredentialRepository;
import com.fightfoodwaste.authservice.service.AuthenticationService;
import com.fightfoodwaste.authservice.service.JwtService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AuthenticationServiceTest {

    @Mock
    private UserCredentialRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveUser_Success() {
        // Mocking
        UserCredential credential = new UserCredential(); // Assuming a constructor or setters
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        doNothing().when(repository).save(any(UserCredential.class));

        // Execution
        RegisteredResponse response = authenticationService.saveUser(credential);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("Successful registration", response.getMessage());
    }

    @Test
    public void testSaveUser_NullCredential() {
        UserCredential userCredential = null;
        RegisteredResponse response = authenticationService.saveUser(userCredential);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
        assertEquals("Invalid request", response.getMessage());
    }

    @Test
    public void testGenerateToken() {
        // Mocking
        String expectedToken = "token";
        when(jwtService.generateToken(anyString())).thenReturn(expectedToken);

        // Execution
        AuthResponse response = authenticationService.generateToken(new AuthRequest("username", "password"));

        // Assertions
        assertEquals(expectedToken, response.getAuth_token());
    }

    @Test
    public void testValidateToken() {
        // Mocking
        doNothing().when(jwtService).validateToken(anyString());

        // Execution
        authenticationService.validateToken(new ValidateRequest("validToken"));

        // Verification
        verify(jwtService).validateToken("validToken");
    }

    // Additional tests to handle exceptions and other edge cases
}
