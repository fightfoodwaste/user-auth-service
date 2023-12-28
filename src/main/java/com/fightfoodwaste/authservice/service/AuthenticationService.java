package com.fightfoodwaste.authservice.service;

import com.fightfoodwaste.authservice.DTO.*;

public interface AuthenticationService {

    RegisteredResponse saveUser(RegisterRequest request);

    AuthResponse generateToken(AuthRequest request);

    void validateToken(ValidateRequest request);
}
