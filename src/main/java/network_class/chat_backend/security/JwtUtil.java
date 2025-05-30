package network_class.chat_backend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

import java.util.Date;

@Component
public class JwtUtil {
    // private static final String SECRET = "miSuperClaveSecreta1234567890MiSuperClaveSecreta1234567890"; // mínimo 256 bits para HS256 // TODO REMOVER ESTO AL FINAL

    // private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME_MS = 86400000; // 24 horas

    public String generateToken(String subject) {
        return Jwts.builder()
                   .setSubject(subject)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
                   .signWith(SECRET_KEY)
                   .compact();
    }

    public String validateTokenAndGetUsername(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(SECRET_KEY)
                   .build()
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }
}
