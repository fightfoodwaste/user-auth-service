package com.fightfoodwaste.authservice.utility;

import com.fightfoodwaste.authservice.DTO.RegisterRequest;
import com.fightfoodwaste.authservice.message.UserRegisteredPayload;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ObjConverterTest {

    private ObjConverter objConverter;

    @BeforeEach
    void setUp() {
        objConverter = new ObjConverter();
    }

    @Test
    void toUserRegistrationPayload_ShouldConvertCorrectly() {
        Long id = 1L;
        RegisterRequest request = new RegisterRequest("a","a","firstName", "lastName", new Date());

        UserRegisteredPayload result = objConverter.toUserRegistrationPayload(id, request);

        Assertions.assertEquals(id, result.getAuth_id());
        Assertions.assertEquals(request.getFirst_name(), result.getFirst_name());
        Assertions.assertEquals(request.getLast_name(), result.getLast_name());
        Assertions.assertEquals(request.getDate_of_birth().getTime(), result.getDate_of_birth());
    }
}
