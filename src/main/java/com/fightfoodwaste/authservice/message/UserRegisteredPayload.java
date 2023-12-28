package com.fightfoodwaste.authservice.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class UserRegisteredPayload {

    private int auth_id;
    private String first_name;
    private String last_name;
    private Date date_of_birth;
}
