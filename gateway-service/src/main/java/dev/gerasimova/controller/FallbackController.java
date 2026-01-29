package dev.gerasimova.controller;

import dev.gerasimova.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class FallbackController {

    @GetMapping("/fallback/book-service")
    public ResponseEntity<?> bookServiceFallback(ServerHttpRequest request) {
        ErrorResponse error = new ErrorResponse(
                Instant.now(),
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "Service Unavailable",
                "Book Service is temporarily unavailable",
                request.getURI().getPath()
        );

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(error);
    }
}
