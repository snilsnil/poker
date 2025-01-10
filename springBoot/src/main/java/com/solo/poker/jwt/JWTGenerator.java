package com.solo.poker.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

import javax.crypto.SecretKey;

import com.solo.poker.user.User;

@Service
public class JWTGenerator {

    private final SecretKey key;

    public JWTGenerator() {

        // 비밀키 생성
        this.key = Jwts.SIG.HS256.key().build();
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId", user.getUserId())
                .claim("username", user.getUsername())
                .claim("email", user.getEmail())
                .claim("time", LocalTime.now().toString())
                .signWith(key).compact();
    }
}
