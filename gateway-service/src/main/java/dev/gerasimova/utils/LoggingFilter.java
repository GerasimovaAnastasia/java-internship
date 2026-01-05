package dev.gerasimova.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Slf4j
@Component
public class LoggingFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        var request = exchange.getRequest();

        log.info("REQUEST: {} {} from {}",
                request.getMethod(),
                request.getURI(),
                request.getRemoteAddress());

        long startTime = System.currentTimeMillis();

        return chain.filter(exchange)
                .doOnSuccess(aVoid -> {
                    ServerHttpResponse response = exchange.getResponse();
                    long duration = System.currentTimeMillis() - startTime;

                    log.info("RESPONSE: {} {} - Status: {} - Time: {}ms",
                            request.getMethod(),
                            request.getURI(),
                            response.getStatusCode(),
                            duration);
                })
                .doOnError(throwable -> {
                    long duration = System.currentTimeMillis() - startTime;

                    log.error("ERROR: {} {} - Error: {} - Time: {}ms",
                            request.getMethod(),
                            request.getURI(),
                            throwable.getMessage(),
                            duration);
                });
    }

}
