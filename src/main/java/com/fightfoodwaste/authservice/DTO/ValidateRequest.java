package com.fightfoodwaste.authservice.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidateRequest {
    private String auth_token;
}
