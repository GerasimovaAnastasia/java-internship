package dev.gerasimova.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
@Slf4j
@Configuration
public class RateLimiterConfig {
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> {
            String ip = exchange.getRequest()
                    .getRemoteAddress()
                    .getAddress()
                    .getHostAddress();

            log.info("IP: " + ip);
            log.info("Path: " + exchange.getRequest().getURI().getPath());
            if (ip == null || ip.isEmpty()) {
                ip = "unknown";
            }

            return Mono.just(ip);
        };
    }
}