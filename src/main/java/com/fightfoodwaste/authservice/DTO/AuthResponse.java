package com.fightfoodwaste.authservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String auth_token;
    private String refresh_token;
    private HttpStatus status;
}
