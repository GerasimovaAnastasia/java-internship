package dev.gerasimova.config;

import dev.gerasimova.filter.BulkheadFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.time.Duration;

@Configuration
public class GatewayConfig {
    
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder,
                                           BulkheadFilter bulkheadFilter) {
        return builder.routes()
            .route("library-api-v1", r -> r.path("/library/v1/books/**")
                .and()
                .method(HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE)
                .filters(f -> f
                    .filter(bulkheadFilter)
                    .retry(config -> {
                        config.setRetries(3);
                        config.setSeries(HttpStatus.Series.SERVER_ERROR);
                        config.setMethods(HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE);
                        config.setStatuses(
                            HttpStatus.SERVICE_UNAVAILABLE,
                            HttpStatus.INTERNAL_SERVER_ERROR
                        );
                        config.setBackoff(
                            Duration.ofMillis(500),
                            Duration.ofSeconds(5),
                            2,
                            false
                        );
                    })
                    .circuitBreaker(config -> {
                        config.setName("bookServiceBreaker");
                        config.setFallbackUri(URI.create("forward:/fallback/book-service"));
                    })
                    .rewritePath("/library/v1/books/(?<segment>.*)",
                            "/api/books/${segment}")
                )
                .uri("lb://BOOK-SERVICE")
            )
            .build();
    }
}