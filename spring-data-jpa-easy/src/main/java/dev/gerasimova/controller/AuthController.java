package dev.gerasimova.controller;

import dev.gerasimova.dto.AuthResponse;
import dev.gerasimova.dto.CreateUserDto;
import dev.gerasimova.dto.LoginUserDto;
import dev.gerasimova.dto.UserResponseDto;
import dev.gerasimova.service.AuthService;
import dev.gerasimova.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Контроллер для endpoints, связанных с регистрацией и авторизацией пользователей.
 *
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация", description = "API для регистрации и входа пользователей")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    /**
     * Endpoint осуществляет регистрацию нового пользователя в системе.
     * @param userDto - входные данные пользователя для регистрации (логин/пароль)
     * @return - логин пользователя
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody CreateUserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userDto));
    }
    /**
     * Endpoint осуществляет авторизацию пользователя в системе.
     * @param userDto - входные данные пользователя для входа (логин/пароль)
     * @return - информацию об итоге авторизации
     * @throws BadCredentialsException - если входные данные невалидны.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserDto userDto) {
        try {
            String token = authService.login(userDto);
            return ResponseEntity.ok().body(new AuthResponse(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Неверный логин или пароль!");
        }
    }
}
