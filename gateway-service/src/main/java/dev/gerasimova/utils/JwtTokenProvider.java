package dev.gerasimova.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

/**
 * Компонент для работы с JWT токенами.
 * Обеспечивает генерацию, валидацию и извлечение данных из JWT токенов.
 *
 * @see Jwts
 */
@Component
@Slf4j
public class JwtTokenProvider {
    private final SecretKey secretKey;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret) {
        if (secret.isBlank()) {
            throw new IllegalArgumentException("JWT secret не может быть пустым");
        }
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }
    public UserDetails getUserDetails(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();

        String roles = claims.get("roles", String.class);
        String[] rolesArray = roles != null ?
                roles.split(",") : new String[0];

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password("")
                .authorities(rolesArray)
                .build();
    }

    /**
     * Проверяет валидность токена
     * @param token JWT токен
     * @return true если токен валиден, false в противном случае
     */
    public boolean validateToken(String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
