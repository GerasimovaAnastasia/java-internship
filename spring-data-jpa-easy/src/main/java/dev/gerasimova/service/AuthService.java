package dev.gerasimova.service;

import dev.gerasimova.dto.LoginUserDto;
import dev.gerasimova.utils.JwtTokenProvider;
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
    private final JwtTokenProvider jwtTokenProvider;
    /**
     * Выполняет аутентификацию пользователя.
     * AuthenticationManager проверяет корректность логина и пароля через UserDetailsService и PasswordEncoder.
     * При успешной аутентификации возвращает объект Authentication с данными пользователя и его правами доступа.
     *
     * @param dto данные пользователя для входа (логин и пароль)
     * @return токен в случае успешной авторизации
     */
    public String login(LoginUserDto dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password())
        );
        return jwtTokenProvider.generateToken(authentication.getName());
    }
}
