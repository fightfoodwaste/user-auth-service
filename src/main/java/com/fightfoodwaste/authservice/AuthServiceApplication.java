package com.fightfoodwaste.authservice;

import com.fightfoodwaste.authservice.utility.ObjConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Bean
    public ObjConverter objConverter(){
        return new ObjConverter();
    }

}
