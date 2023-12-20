package com.fightfoodwaste.authservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record ValidateRequest(String auth_token) {
}
