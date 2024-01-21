package com.fightfoodwaste.authservice.service;

import com.fightfoodwaste.authservice.DTO.SafeDeletionPayload;
import com.fightfoodwaste.authservice.message.UserRegisteredPayload;

public interface MessagingService {

    void publishUserRegistration(UserRegisteredPayload payload);

    void publishSafeDeletion(SafeDeletionPayload payload);
}
