package dev.gerasimova.service;

import dev.gerasimova.dto.CreateUserDto;
import dev.gerasimova.dto.CreateUserWithRoleDto;
import dev.gerasimova.dto.LoginUserDto;
import dev.gerasimova.dto.UserResponseDto;
import dev.gerasimova.exception.UserException;
import dev.gerasimova.repository.UserRepository;
import dev.gerasimova.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервисный класс для авторизации пользователей.
 *
 * @see dev.gerasimova.model.User
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    /**
     * Сохраняет нового пользователя в хранилище с правами обычного пользователя.
     * Хэширует пароль перед сохранением в БД
     *
     * @param dto данных пользователя для сохранения
     * @return дто сохраненного аккаунта пользователя
     * @throws UserException бросает исключение, если аккаунт с заданным логином уже существует
     * @see UserRepository#save(Object)
     */
    @Transactional
    public UserResponseDto register(CreateUserDto dto) {
        log.info("Регистрация пользователя, права: USER");
        String encodedPassword = passwordEncoder.encode(dto.password());
        return userService.save(dto, encodedPassword);
    }
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
        log.info("Авторизация пользователя: %s".formatted(dto.username()));
        return jwtTokenProvider.generateToken(authentication.getName());
    }
    /**
     * Сохраняет нового пользователя в хранилище с заданными правами.
     * Хэширует пароль перед сохранением в БД
     *
     * @param dto данных пользователя для сохранения
     * @return дто сохраненного аккаунта пользователя
     * @throws UserException бросает исключение, если аккаунт с заданным логином уже существует
     * @see UserRepository#save(Object)
     */
    @Transactional
    public UserResponseDto createUser(CreateUserWithRoleDto dto) {
        String encodedPassword = passwordEncoder.encode(dto.password());
        log.info("Регистрация пользователя: %s с правами %s".formatted(dto.username(), dto.role()));
        return userService.saveWithRole(dto, encodedPassword);
    }
}
