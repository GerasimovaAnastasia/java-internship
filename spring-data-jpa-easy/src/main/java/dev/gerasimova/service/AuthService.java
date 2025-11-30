package dev.gerasimova.service;

import dev.gerasimova.dto.LoginUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
/**
 * Сервисный класс для авторизации пользователей.
 *
 * @see dev.gerasimova.model.User
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    /**
     * Выполняет аутентификацию пользователя.
     * AuthenticationManager проверяет корректность логина и пароля через UserDetailsService и PasswordEncoder.
     * При успешной аутентификации возвращает объект Authentication с данными пользователя и его правами доступа.
     *
     * @param dto данные пользователя для входа (логин и пароль)
     */
    public void login(LoginUserDto dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password())
        );
    }
}
