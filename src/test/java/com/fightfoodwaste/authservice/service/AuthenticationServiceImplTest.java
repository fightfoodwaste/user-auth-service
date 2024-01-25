package com.fightfoodwaste.authservice.service;

import com.fightfoodwaste.authservice.DTO.*;
import com.fightfoodwaste.authservice.entity.UserCredential;
import com.fightfoodwaste.authservice.env.EnvVariables;
import com.fightfoodwaste.authservice.repository.UserCredentialRepository;
import com.fightfoodwaste.authservice.utility.ObjConverter;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AuthenticationServiceImplTest {


    @Mock
    private UserCredentialRepository repository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private MessagingService messagingService;
    @Mock
    private ObjConverter objConverter;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private EnvVariables envVariables;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;


    void setUp(){
        this.repository = mock(UserCredentialRepository.class);
        this.passwordEncoder = mock(PasswordEncoder.class);
        this.jwtService = mock(JwtServiceImpl.class);
        //this.messagingService = mock(MessagingServiceImpl.class);
        this.objConverter = mock(ObjConverter.class);
        this.authenticationManager = mock(AuthenticationManager.class);
        this.envVariables = mock(EnvVariables.class);
        this.authenticationService = new AuthenticationServiceImpl(repository, passwordEncoder, jwtService, objConverter, authenticationManager, envVariables);
    }

    @org.junit.jupiter.api.Test
    public void saveUser_ShouldRegisterSuccessfully() {
        RegisterRequest request = new RegisterRequest("user", "pass", "", "", new Date());
        when(this.passwordEncoder.encode("pass")).thenReturn("encodedPass");
        when(repository.saveAndFlush(any(UserCredential.class))).thenReturn(new UserCredential(1L, "user", "encodedPass"));

        RegisteredResponse response = authenticationService.saveUser(request);

        Assertions.assertEquals(HttpStatus.OK, response.getStatus());
        Assertions.assertEquals("Successful registration", response.getMessage());
    }

    @org.junit.jupiter.api.Test
    public void saveUser_ShouldReturnBadRequestForNullRequest() {
        RegisteredResponse response = authenticationService.saveUser(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
        Assertions.assertEquals("Invalid request", response.getMessage());
    }

    @Test
    public void saveUser_testFail() {
        RegisterRequest request = new RegisterRequest("user", "pass", "", "", new Date());
        when(passwordEncoder.encode("pass")).thenReturn("encodedPass");
        when(repository.saveAndFlush(any(UserCredential.class))).thenThrow(new DataIntegrityViolationException("User exists"));

        RegisteredResponse response = authenticationService.saveUser(request);

        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatus());
        Assertions.assertEquals("E-mail already registered", response.getMessage());
    }

}
