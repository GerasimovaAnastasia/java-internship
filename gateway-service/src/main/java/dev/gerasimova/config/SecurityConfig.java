package dev.gerasimova.config;

import dev.gerasimova.utils.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenFilter jwtFilter;
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(authz -> authz
                        .pathMatchers(
                                "/book-service/api/auth/login",
                                "/book-service/api/auth/register",
                                "/book-service/api/auth/users",
                                "/actuator/health",
                                "/favicon.ico",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-resources/**",
                                "/configuration/**"
                        ).permitAll()
                        .anyExchange().authenticated()
                )
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .addFilterBefore(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }
}
