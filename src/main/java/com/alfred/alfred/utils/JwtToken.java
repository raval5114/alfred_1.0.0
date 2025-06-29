package com.alfred.alfred.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import com.alfred.alfred.models.dto.auth.tokenBody.TokenBody;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class JwtToken {

    private final String SECRET_KEY = "my-super-secret-key-for-jwt-alfred-256-bit-value";
    private final long EXPIRATION_TIME = 1000L * 60 * 60 * 32; // ~32 hours

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Generate token with id as subject and roles as custom claim
    public String generateToken(TokenBody tokenBody) {
        return Jwts.builder()
                .setSubject(tokenBody.getId())
                .claim("roles", tokenBody.getData())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract userId from subject
    public String extractUserId(String token) {
        return extractClaims(token).getSubject();
    }

    // Extract roles from custom claim
    public List<Map<String, String>> extractRoles(String token) {
        Claims claims = extractClaims(token);
        return (List<Map<String, String>>) claims.get("roles");
    }

    public boolean isTokenValid(String token, String expectedUserId) {
        try {
            String tokenUserId = extractUserId(token);
            return (tokenUserId.equals(expectedUserId) && !isTokenExpired(token));
        } catch (JwtException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
