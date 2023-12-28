package com.fightfoodwaste.authservice.utility;

import com.fightfoodwaste.authservice.DTO.RegisterRequest;
import com.fightfoodwaste.authservice.message.UserRegisteredPayload;

public class ObjConverter {

    public UserRegisteredPayload toUserRegistrationPayload(int id, RegisterRequest request){
        return new UserRegisteredPayload(
                id,
                request.getFirst_name(),
                request.getLast_name(),
                request.getDate_of_birth()
        );
    }
}
