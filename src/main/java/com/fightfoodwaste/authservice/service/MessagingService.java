package com.fightfoodwaste.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessagingService {

    private final RabbitTemplate rabbitTemplate;

    public void sendTestMessage(){
        rabbitTemplate.convertAndSend("", "user-registration", "message");
    }
}
