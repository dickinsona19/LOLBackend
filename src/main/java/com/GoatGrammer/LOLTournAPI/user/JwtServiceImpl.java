package com.GoatGrammer.LOLTournAPI.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    private final String SECRET_KEY;
    private final long EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000; // 7 days in milliseconds

    // Constructor to initialize the secret key
    public JwtServiceImpl() {
        // Generate a secure random key
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[32]; // 256 bits = 32 bytes
        secureRandom.nextBytes(key);
        SECRET_KEY = Base64.getEncoder().encodeToString(key); // Base64 encode the key
    }

    @Override
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            // Check if the token is not expired
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false; // Token is invalid
        }
    }

    // Extract email from the token (if you don't have this method already)
    public String extractEmail(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
