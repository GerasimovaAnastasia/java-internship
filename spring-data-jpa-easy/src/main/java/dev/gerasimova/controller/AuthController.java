package dev.gerasimova.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Контроллер для endpoints, связанных с регистрацией и авторизацией пользователей.
 *
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @PostMapping()
    public ResponseEntity<String> register() {
        return ResponseEntity.ok("Регистрация обязательна!");
    }
}
