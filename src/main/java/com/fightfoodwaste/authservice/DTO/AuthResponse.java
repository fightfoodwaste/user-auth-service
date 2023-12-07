package com.fightfoodwaste.authservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String auth_token;
    private String refresh_token;
}
