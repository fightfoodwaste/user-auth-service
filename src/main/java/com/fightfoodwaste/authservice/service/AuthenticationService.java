package com.fightfoodwaste.authservice.service;

import com.fightfoodwaste.authservice.DTO.*;
import com.fightfoodwaste.authservice.entity.UserCredential;
import com.fightfoodwaste.authservice.message.UserRegisteredPayload;
import com.fightfoodwaste.authservice.repository.UserCredentialRepository;
import com.fightfoodwaste.authservice.utility.ObjConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthenticationService{

    private final UserCredentialRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final MessagingService messagingService;
    private final ObjConverter objConverter;

    public RegisteredResponse saveUser(RegisterRequest request) {
        if(request == null){
            return new RegisteredResponse(HttpStatus.BAD_REQUEST, "Invalid request");
        }
        UserCredential credential = new UserCredential(request.getUsername(), request.getPassword());
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        try{
            UserCredential user = repository.saveAndFlush(credential);
            UserRegisteredPayload payload = objConverter.toUserRegistrationPayload(user.getId(), request);
            messagingService.publishUserRegistration(payload);
            return new RegisteredResponse(HttpStatus.OK,"Successful registration");
        }
        catch (Exception e){
            e.printStackTrace();
            return new RegisteredResponse(HttpStatus.CONFLICT, "E-mail already registered");
        }

    }

    public AuthResponse generateToken(AuthRequest request) {
        String jwt = jwtService.generateToken(request.getUsername());
        return new AuthResponse(jwt, "");
    }

    public void validateToken(ValidateRequest request) {
        String auth_token = request.getAuth_token();
        jwtService.validateToken(auth_token);
    }


}