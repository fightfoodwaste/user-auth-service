package com.fightfoodwaste.authservice.service;

import com.fightfoodwaste.authservice.DTO.*;
import org.springframework.http.HttpStatus;

public interface AuthenticationService {

    RegisteredResponse saveUser(RegisterRequest request);

    AuthResponse authenticate(AuthRequest request);

    void validateToken(ValidateRequest request);

    HttpStatus deleteAccount(long id, String header);
}
