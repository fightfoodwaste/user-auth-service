/*package com.fightfoodwaste.authservice.config;

import com.rabbitmq.client.ConnectionFactory;
import jakarta.inject.Qualifier;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
    public static final String USER_REGISTRATION_QUEUE_NAME = "user-registration";
    public static final String USER_REGISTRATION_EXCHANGE_NAME = "user-registration-exchange";
    public static final String USER_REGISTRATION_ROUTING_KEY = "user-registration";

    public static final String SAFE_DELETION_QUEUE_NAME = "safe-delete";
    public static final String SAFE_DELETION_EXCHANGE_NAME = "safe-delete-exchange";
    public static final String SAFE_DELETION_ROUTING_KEY = "safe-delete";

    @Bean
    org.springframework.amqp.core.Queue register_queue() {
        return new org.springframework.amqp.core.Queue(USER_REGISTRATION_QUEUE_NAME, true);
    }

    @Bean
    org.springframework.amqp.core.Queue safe_delete_queue() {
        return new org.springframework.amqp.core.Queue(SAFE_DELETION_QUEUE_NAME, true);
    }

    @Bean
    DirectExchange register_exchange() {
        return new DirectExchange(USER_REGISTRATION_EXCHANGE_NAME);
    }

    @Bean
    DirectExchange safe_delete_exchange() {
        return new DirectExchange(SAFE_DELETION_EXCHANGE_NAME);
    }

    @Bean
    Binding register_binding(@org.springframework.beans.factory.annotation.Qualifier("register_queue") Queue register_queue,
                             @org.springframework.beans.factory.annotation.Qualifier("register_exchange") DirectExchange register_exchange) {
        return BindingBuilder.bind(register_queue).to(register_exchange).with(USER_REGISTRATION_ROUTING_KEY);
    }

    @Bean
    Binding binding(@org.springframework.beans.factory.annotation.Qualifier("safe_delete_queue") Queue safe_delete_queue,
                    @org.springframework.beans.factory.annotation.Qualifier("safe_delete_exchange") DirectExchange safe_delete_exchange) {
        return BindingBuilder.bind(safe_delete_queue).to(safe_delete_exchange).with(SAFE_DELETION_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}*/

