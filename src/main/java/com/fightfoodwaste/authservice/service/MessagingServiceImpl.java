package com.fightfoodwaste.authservice.service;

import com.fightfoodwaste.authservice.message.UserRegisteredPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessagingServiceImpl implements MessagingService{

    private final RabbitTemplate rabbitTemplate;

    public void sendTestMessage(){
        rabbitTemplate.convertAndSend("", "user-registration", "message");
    }

    public void publishUserRegistration(UserRegisteredPayload payload){
        String routing_key = "user-registration";
        rabbitTemplate.convertAndSend("", routing_key, payload);
    }
}
