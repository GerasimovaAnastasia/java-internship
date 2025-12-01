package dev.gerasimova.service;

import dev.gerasimova.dto.CreateUserDto;
import dev.gerasimova.dto.CreateUserWithRoleDto;
import dev.gerasimova.dto.UserResponseDto;
import dev.gerasimova.exception.UserException;
import dev.gerasimova.mapper.UserMapper;
import dev.gerasimova.model.User;
import dev.gerasimova.model.UserRole;
import dev.gerasimova.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервисный класс для управления бизнес-логикой работы с пользователями.
 * Обеспечивает CRUD-операции над сущностью User через UserRepository.
 *
 * @see dev.gerasimova.model.User
 * @see UserRepository
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    /**
     * Находит аккаунт пользователя в хранилище по логину.
     *
     * @param username логин для поиска
     * @return аккаунт пользователя
     * @throws UsernameNotFoundException бросает исключение, если не нашел аккаунт
     * @see UserRepository#save(Object)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException(
                        "Пользователь '%s' не найден".formatted(username)
                ));
    }
    /**
     * Сохраняет нового пользователя в хранилище с правами обычного пользователя.
     *
     * @param dto данных пользователя для сохранения
     * @return дто сохраненного аккаунта пользователя
     *@throws UserException бросает исключение, если аккаунт с заданным логином уже существует
     * @see UserRepository#save(Object)
     */
    @Transactional
    public UserResponseDto save(CreateUserDto dto, String password) {
        if (userRepository.existsByUsername(dto.username())) {
            throw new UserException("Пользователь с именем '%s' уже существует".formatted(dto.username())
            );
        }
        User newUser = userMapper.toUser(dto, password, UserRole.ROLE_USER);
        userRepository.save(newUser);
        return userMapper.toUserResponseDto(newUser);
    }
    /**
     * Сохраняет нового пользователя в хранилище с заданными правами.
     *
     * @param dto данных пользователя для сохранения
     * @return дто сохраненного аккаунта пользователя
     * @throws UserException бросает исключение, если аккаунт с заданным логином уже существует
     * @see UserRepository#save(Object)
     */
    @Transactional
    public UserResponseDto saveWithRole(CreateUserWithRoleDto dto, String password) {
        if (userRepository.existsByUsername(dto.username())) {
            throw new UserException("Пользователь '%s' уже существует".formatted(dto.username()));
        }
        User newUser = userMapper.toUser(dto, password, dto.role());
        userRepository.save(newUser);
        return userMapper.toUserResponseDto(newUser);
    }
}
