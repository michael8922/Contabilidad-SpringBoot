package com.proyecto.contabilidad.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    public Claims extractAllClaims(String token) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            System.out.println(" Error al validar token: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            return false;
        }
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).get("username", String.class);
    }
}
