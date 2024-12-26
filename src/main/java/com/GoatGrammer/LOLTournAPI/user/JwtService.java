package com.GoatGrammer.LOLTournAPI.user;

public interface JwtService {
    String generateToken(String username);
    boolean validateToken(String token);
    String extractEmail(String token);
}