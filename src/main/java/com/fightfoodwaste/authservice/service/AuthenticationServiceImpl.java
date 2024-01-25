package com.fightfoodwaste.authservice.service;

import com.fightfoodwaste.authservice.DTO.*;
import com.fightfoodwaste.authservice.entity.UserCredential;
import com.fightfoodwaste.authservice.env.EnvVariables;
import com.fightfoodwaste.authservice.message.UserRegisteredPayload;
import com.fightfoodwaste.authservice.repository.UserCredentialRepository;
import com.fightfoodwaste.authservice.utility.ObjConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserCredentialRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final MessagingService messagingService;
    private final ObjConverter objConverter;
    private final AuthenticationManager authenticationManager;
    private final EnvVariables envVariables;


    @Override
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
            return new RegisteredResponse(HttpStatus.CONFLICT, "E-mail already registered");
        }

    }
    @Override
    public AuthResponse authenticate(AuthRequest request){
        try{
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            if (authenticate.isAuthenticated()) {
                AuthResponse response = generateToken(request);
                return response;
            } else {
                return new AuthResponse("", "", HttpStatus.EXPECTATION_FAILED);
            }
        }catch (Exception e){
            return new AuthResponse("", "", HttpStatus.EXPECTATION_FAILED);
        }

    }

    private AuthResponse generateToken(AuthRequest request){
        Long id = repository.findIdByUsername(request.getUsername()).orElseThrow();
        String jwt = jwtService.generateToken(id);
        return new AuthResponse(jwt, "", HttpStatus.OK);
    }

    @Override
    public void validateToken(ValidateRequest request) {
        String auth_token = request.getAuth_token();
        jwtService.validateToken(auth_token);
    }

    @Override
    public HttpStatus deleteAccount(long id, String header){

        if(header.equals(envVariables.getAdminKey())){
            repository.deleteById(id);
            messagingService.publishSafeDeletion(new SafeDeletionPayload(id));
            return HttpStatus.OK;
        }
        return HttpStatus.FORBIDDEN;

    }


}