package com.fightfoodwaste.authservice.controller;

import com.fightfoodwaste.authservice.DTO.*;
import com.fightfoodwaste.authservice.service.AuthenticationService;
import com.fightfoodwaste.authservice.service.AuthenticationServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin
public class AuthenticationController{

    private final AuthenticationService service;






    @PostMapping("/register")
    public ResponseEntity<RegisteredResponse> addNewUser(@RequestBody RegisterRequest request) {
        try{
            RegisteredResponse response= service.saveUser(request);
            return ResponseEntity.ok().body(response);
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }

    }





    @PostMapping("/token")
    public ResponseEntity<AuthResponse> getToken(@RequestBody AuthRequest authRequest) {
        try{
            AuthResponse response = service.authenticate(authRequest);
            HttpStatus status = response.getStatus();
            if(status == HttpStatus.EXPECTATION_FAILED){
                return ResponseEntity.status(status).build();
            }
            return ResponseEntity.status(status).body(response);
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
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

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAccount(@PathVariable("id") long id, @RequestHeader("Authorization") Optional<String> header){
        try {
            if(header.isEmpty()){
                return ResponseEntity.status(403).build();
            }
            HttpStatus status = service.deleteAccount(id, header.get());
            return ResponseEntity.status(status).build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

}
