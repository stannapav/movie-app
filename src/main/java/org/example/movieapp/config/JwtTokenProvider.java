package org.example.movieapp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class JwtTokenProvider {
    private static final long EXPIRATION_TIME = 60 * 60 * 1000;
    
    private static final String SECRET_KEY = "SWhAdGVKYXZhNWNyaXB0QlVUMUwwNTNDQFQ1"; 
    private static final SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String createToken(String email, List<GrantedAuthority> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", email); // Set the subject (email)
        claims.put("roles", roles); // Set the roles

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .claims(claims) 
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(secretKey) 
                .compact();
    }
    
    public boolean validateToken(String token) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwts.parser()
                    .verifyWith(secretKey) 
                    .build()
                    .parseSignedClaims(token);
            
            return true; 
        } catch (JwtException e) {
            log.error("Invalid token: {}", e.getMessage());
            return false;  
        }
    }
    
    public String extractUsername(String token) {
        JwtParser parser = Jwts.parser()
                .verifyWith(secretKey)
                .build();

        Claims claims = parser.parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }
    
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        
        Object rolesObject = claims.get("roles");

        List<String> roles;
        
        if (rolesObject instanceof List) {
            roles = ((List<?>) rolesObject).stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
        }
        else if (rolesObject instanceof Map) {
            roles = ((Map<?, ?>) rolesObject).keySet().stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("Unsupported roles format in JWT token");
        }
        
        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
    }

    public String authenticateUser(String email) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, null, List.of(() -> "ROLE_" + "USER"));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        return createToken(email, List.of(() -> "ROLE_" + "USER"));
    }
}