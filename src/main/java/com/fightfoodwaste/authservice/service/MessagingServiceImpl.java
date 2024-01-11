package com.fightfoodwaste.authservice.service;

import com.fightfoodwaste.authservice.message.UserRegisteredPayload;
import com.fightfoodwaste.authservice.utility.JsonExtract;
import com.fightfoodwaste.authservice.utility.JsonExtractImpl;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class MessagingServiceImpl implements MessagingService{

    private final ConnectionFactory connectionFactory;

    private final JsonExtract jsonExtract;


    public void publishUserRegistration(UserRegisteredPayload payload){
        //String routing_key = "user-registration";
        try(Connection connection = connectionFactory.newConnection()){
            Channel channel = connection.createChannel();
            channel.queueDeclare("user-registration", true, false, false, null);

            String message = jsonExtract.convertUserRegistrationPaylodToJson(payload);

            if(!Objects.equals(message, "")){
                channel.basicPublish("", "user-registration", null, message.getBytes());
                System.out.println("Message published!");
            }
            else{
                System.out.println("Error converting object");
            }

        } catch(IOException e){
            e.printStackTrace();
            System.out.println("Connection error");
        } catch(TimeoutException e){
            e.printStackTrace();
            System.out.println("Timeout");
        }
    }
}
