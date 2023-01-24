package com.informationengineering.internetapplicationproject.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class TokenHandler {

    private final static String SECRET_KEY = "secret";
    private final static long EXPIRATION_TIME = 1000 * 60 * 60 * 5; //5 hours

    public String generateToken(String username) {
        return Jwts.builder()
                .addClaims(new HashMap<>())
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }


    Claims getTokenData(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

    }

    public String getUsername(String token) {
        Claims claims = getTokenData(token);
        return claims.getSubject();
    }

    public Date getExpirationTime(String token) {
        Claims claims = getTokenData(token);
        return claims.getExpiration();
    }

    public boolean isTokenExpired(String token) {
        Date expirationTime = getExpirationTime(token);
        return expirationTime.before(new Date());
    }


    public boolean validateToken(String token, String username) {
        String tokenUsername = getUsername(token);
        return tokenUsername.equals(username) && !isTokenExpired(token);
    }


}
