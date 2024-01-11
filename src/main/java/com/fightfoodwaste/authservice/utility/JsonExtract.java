package com.fightfoodwaste.authservice.utility;

import com.fightfoodwaste.authservice.message.UserRegisteredPayload;

public interface JsonExtract {

    String convertUserRegistrationPaylodToJson(UserRegisteredPayload payload);

}
