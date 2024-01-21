package com.fightfoodwaste.authservice.service;

import com.fightfoodwaste.authservice.env.EnvVariables;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class EncryptionServiceImpl implements EncryptionService{

    private final EnvVariables envVariables;


    private byte[] getDecodedKey(){
        return Base64.getDecoder().decode(this.envVariables.getEncryptionKey());
    }
    @Override
    public String encrypt(String data) throws Exception{
        Cipher cipher = Cipher.getInstance(this.envVariables.getEncryptionAlgorithm());
        byte[] decodedKey = getDecodedKey();
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(decodedKey, this.envVariables.getEncryptionAlgorithm()));
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}
