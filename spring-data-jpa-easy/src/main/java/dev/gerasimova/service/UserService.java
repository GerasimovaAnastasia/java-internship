package dev.gerasimova.service;

import dev.gerasimova.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
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
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException(
                        "Пользователь '%s' не найден".formatted(username)
                ));
    }
}
