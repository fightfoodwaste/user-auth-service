package com.fightfoodwaste.authservice.service;

import com.fightfoodwaste.authservice.message.UserRegisteredPayload;

public interface MessagingService {

    void publishUserRegistration(UserRegisteredPayload payload);
}
