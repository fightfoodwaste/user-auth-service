package com.fightfoodwaste.authservice.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fightfoodwaste.authservice.DTO.SafeDeletionPayload;
import com.fightfoodwaste.authservice.message.UserRegisteredPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class JsonExtractImpl implements JsonExtract{

    private final ObjectMapper mapper;

    public JsonExtractImpl(){
        this.mapper = new ObjectMapper();
    }


    @Override
    public String convertUserRegistrationPaylodToJson(UserRegisteredPayload payload) {
        try{
            String jsonString = mapper.writeValueAsString(payload);
            return jsonString;
        }
        catch(Exception e) {
            return "";
        }
    }

    public String convertSafeDeletionPayloadToJson(SafeDeletionPayload payload){
        try{
            String jsonString = mapper.writeValueAsString(payload);
            return jsonString;
        }catch (Exception e){
            return "";
        }
    }
}
