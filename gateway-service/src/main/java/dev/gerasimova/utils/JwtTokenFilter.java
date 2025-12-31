package dev.gerasimova.utils;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenFilter implements WebFilter {
    @Value("${jwt.header}")
    private String AUTH_HEADER;
    @Value("${jwt.basic-token-prefix}")
    private String BASIC_TOKEN_PREFIX;
    private final JwtTokenProvider jwtTokenProvider;
    @NonNull
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = exchange.getRequest().getURI().getPath();

        String method = request.getMethod().name();
        log.info("Gateway запрос: {} {}", method, path);

        if (isPublicEndpoint(path)) {
            log.debug("Публичный endpoint, пропускаем проверку: {}", path);
            return chain.filter(exchange);
        }
        String token = extractTokenFromRequest(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        try {
            UserDetails userDetails = jwtTokenProvider.getUserDetails(token);
            log.debug("Аутентифицирован пользователь: {} для: {}", userDetails.getUsername(), path);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(), null, userDetails.getAuthorities()
            );
            SecurityContext securityContext = new SecurityContextImpl(authentication);
            return chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(
                            Mono.just(securityContext)
                    ));

        } catch (Exception e) {
            log.error("Ошибка аутентификации для {}: {}", path, e.getMessage(), e);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }
    /**
     * Извлекает JWT токен из заголовка Authorization.
     *
     * @param request HTTP запрос
     * @return JWT токен или null если не найден
     */
    private String extractTokenFromRequest(ServerHttpRequest request) {
        String authHeader = request.getHeaders().getFirst(AUTH_HEADER);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith(BASIC_TOKEN_PREFIX)) {
            return authHeader.substring(BASIC_TOKEN_PREFIX.length());
        }
        return null;
    }
    /**
     * Проверяет, является ли endpoint публичным
     */
    private boolean isPublicEndpoint(String uri) {
        return uri.startsWith("/book-service/api/auth/") ||
                uri.startsWith("/actuator/health") ||
                uri.startsWith("/actuator/info") ||
                uri.startsWith("/book-service/") ||
                uri.startsWith("/v3/api-docs") ||
                uri.startsWith("/swagger-ui") ||
                uri.startsWith("/swagger-resources") ||
                uri.startsWith("/webjars/") ||
                uri.equals("/favicon.ico") ||
                uri.equals("/");
    }

}
