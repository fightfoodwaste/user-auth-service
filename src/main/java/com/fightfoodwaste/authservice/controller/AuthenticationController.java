package com.fightfoodwaste.authservice.controller;

import com.fightfoodwaste.authservice.DTO.*;
import com.fightfoodwaste.authservice.service.AuthenticationService;
import com.fightfoodwaste.authservice.service.AuthenticationServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController{

    private final AuthenticationService service;
    private final AuthenticationManager authenticationManager;
    @PostMapping("/register")
    public ResponseEntity<RegisteredResponse> addNewUser(@RequestBody RegisterRequest request) {
        try{
            RegisteredResponse response= service.saveUser(request);
            return ResponseEntity.status(response.getStatus()).body(response);
        }
        catch(RuntimeException e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }

    }

    @PostMapping("/token")
    public ResponseEntity<AuthResponse> getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            AuthResponse response = service.generateToken(authRequest);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @PostMapping("/validate")
    public ResponseEntity validateToken(@RequestBody ValidateRequest request) {
        try{
            service.validateToken(request);
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

}
