package com.example.todo_management.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JWTTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationMilliseconds;

    //Create generate token utility method
    public String generateToken(Authentication authentication){

        String username= authentication.getName();
        Date currentDate =new Date();
        Date expireDate=new Date(currentDate.getTime()+jwtExpirationMilliseconds);

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
                .compact();
    }

    //Get Username from token
    public String getUsernameFromToken(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    //validate token
    public boolean validateToken(String token){

        Jwts.parser()
                .verifyWith((SecretKey) Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
                .build()
                .parse(token);

        return true;
    }


}
