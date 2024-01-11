package com.fightfoodwaste.authservice.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
    @Bean
    public ConnectionFactory getConnectionFactory(){
        /*ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(System.getenv("RABBITMQ_HOST"));
        connectionFactory.setPort(Integer.parseInt(System.getenv("RABBITMQ_PORT")));
        connectionFactory.setUsername(System.getenv("RABBITMQ_USERNAME"));
        connectionFactory.setPassword(System.getenv("RABBITMQ_PASSWORD"));*/
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(Integer.parseInt("5672"));
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }
}
