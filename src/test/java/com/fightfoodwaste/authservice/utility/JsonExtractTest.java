package com.fightfoodwaste.authservice.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fightfoodwaste.authservice.DTO.SafeDeletionPayload;
import com.fightfoodwaste.authservice.message.UserRegisteredPayload;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class JsonExtractImplTest {

    @Mock
    private ObjectMapper objectMapper;

    private JsonExtractImpl jsonExtract;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jsonExtract = new JsonExtractImpl();
    }

    @Test
    void convertUserRegistrationPayloadToJson_ShouldConvertCorrectly() throws Exception {
        UserRegisteredPayload payload = new UserRegisteredPayload(1L, "John", "Doe", 1234567890L);
        when(objectMapper.writeValueAsString(payload)).thenReturn("{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"dateOfBirth\":1234567890}");

        String json = jsonExtract.convertUserRegistrationPaylodToJson(payload);

        Assertions.assertEquals("{\"auth_id\":1,\"first_name\":\"John\",\"last_name\":\"Doe\",\"date_of_birth\":1234567890}", json);
    }

    @Test
    void convertSafeDeletionPayloadToJson_ShouldConvertCorrectly() throws Exception {
        SafeDeletionPayload payload = new SafeDeletionPayload(1L);
        when(objectMapper.writeValueAsString(payload)).thenReturn("{\"id\":1}");

        String json = jsonExtract.convertSafeDeletionPayloadToJson(payload);

        assertEquals("{\"id\":1}", json);
    }
}
