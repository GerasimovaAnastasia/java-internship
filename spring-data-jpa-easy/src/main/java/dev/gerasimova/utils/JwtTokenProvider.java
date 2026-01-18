package dev.gerasimova.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
/**
 * Компонент для работы с JWT токенами.
 * Обеспечивает генерацию, валидацию и извлечение данных из JWT токенов.
 *
 * @see Jwts
 */
@RefreshScope
@Component
@Slf4j
public class JwtTokenProvider {
    private final SecretKey secretKey;
    @Getter
    @Value("${jwt.expiration}")
    private Long expirationMs;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }
    @PostConstruct
    public void init() {
        log.info("JwtTokenProvider инициализирован с expirationMs = {} ms ({} minutes)",
                expirationMs, expirationMs / 60000);
    }

    @EventListener(RefreshScopeRefreshedEvent.class)
    public void onRefresh(RefreshScopeRefreshedEvent event) {

        log.info("JWT expiration изменен на: {} ms ({} minutes)",
                expirationMs, expirationMs / 60000);
    }
    /**
     * Генерирует JWT токен для пользователя
     * @param username имя пользователя
     * @return JWT токен
     */
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
    /**
     * Проверяет валидность токена (для тестов)
     * @param token JWT токен
     * @return true если токен валиден, false в противном случае
     */
    public boolean validateToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Токен не может быть пустым");
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
    /**
     * Извлекает имя пользователя из JWT токена. (для тестов)
     *
     * @param token JWT токен
     * @return имя пользователя (subject токена)
     * @throws JwtException если токен невалиден
     */
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

}
