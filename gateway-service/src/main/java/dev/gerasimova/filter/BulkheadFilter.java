package dev.gerasimova.filter;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.ThreadPoolBulkhead;
import io.github.resilience4j.bulkhead.ThreadPoolBulkheadRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
@Slf4j
@Component
public class BulkheadFilter implements GatewayFilter {
    private final ThreadPoolBulkhead bulkhead;

    public BulkheadFilter(ThreadPoolBulkheadRegistry registry) {
        this.bulkhead = registry.bulkhead("bookServiceBulkhead");
    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return Mono.fromFuture(() -> {
                    Callable<Mono<Void>> callable = () -> chain.filter(exchange);
                    return ThreadPoolBulkhead.decorateCallable(bulkhead, callable)
                            .get().toCompletableFuture();
                })
                .flatMap(mono -> mono)
                .onErrorResume(BulkheadFullException.class, e -> {
                    log.warn("Bulkhead переполнен для пути: {}", exchange.getRequest().getPath());
                    exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                    return exchange.getResponse().setComplete();
                })
                .doOnSubscribe(s ->
                        log.debug("Bulkhead запрос. Доступно потоков: {}",
                                bulkhead.getMetrics().getAvailableThreadCount())
                );
    }
}