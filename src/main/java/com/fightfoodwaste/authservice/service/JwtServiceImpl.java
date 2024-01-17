package com.fightfoodwaste.authservice.service;

import com.fightfoodwaste.authservice.env.EnvVariables;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService{

    private final EnvVariables envVariables;

    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }


    public String generateToken(Long id) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, id);
    }

    public String createToken(Map<String, Object> claims, Long id) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(id.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    public Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(envVariables.getKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
