package com.fightfoodwaste.authservice.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisteredPayload {

    private Long auth_id;
    private String first_name;
    private String last_name;
    private Long date_of_birth;
}
