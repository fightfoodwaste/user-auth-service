package com.fightfoodwaste.authservice.service;

import com.fightfoodwaste.authservice.DTO.AuthRequest;
import com.fightfoodwaste.authservice.DTO.AuthResponse;
import com.fightfoodwaste.authservice.DTO.RegisteredResponse;
import com.fightfoodwaste.authservice.DTO.ValidateRequest;
import com.fightfoodwaste.authservice.entity.UserCredential;
import com.fightfoodwaste.authservice.repository.UserCredentialRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthenticationService{

    private final UserCredentialRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public RegisteredResponse saveUser(UserCredential credential) {
        if(credential == null){
            return new RegisteredResponse(HttpStatus.BAD_REQUEST, "Invalid request");
        }
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

    public AuthResponse generateToken(AuthRequest request) {
        String jwt = jwtService.generateToken(request.username());
        return new AuthResponse(jwt, "");
    }

    public void validateToken(ValidateRequest request) {
        String auth_token = request.auth_token();
        jwtService.validateToken(auth_token);
    }


}