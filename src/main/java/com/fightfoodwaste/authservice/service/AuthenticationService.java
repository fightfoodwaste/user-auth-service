package com.fightfoodwaste.authservice.service;

import com.fightfoodwaste.authservice.DTO.AuthResponse;
import com.fightfoodwaste.authservice.DTO.RegisteredResponse;
import com.fightfoodwaste.authservice.DTO.ValidateRequest;
import com.fightfoodwaste.authservice.entity.UserCredential;
import com.fightfoodwaste.authservice.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public RegisteredResponse saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
       // repository.save(credential);
        try{
            repository.save(credential);
            return new RegisteredResponse(HttpStatus.OK,"Successful registration");
        }
        catch (Exception e){
            return new RegisteredResponse(HttpStatus.CONFLICT, "E-mail already registered");
        }

    }

    public AuthResponse generateToken(String email) {
        String jwt = jwtService.generateToken(email);
        return new AuthResponse(jwt, "");
    }

    public void validateToken(ValidateRequest request) {
        String auth_token = request.getAuth_token();
        jwtService.validateToken(auth_token);
    }


}