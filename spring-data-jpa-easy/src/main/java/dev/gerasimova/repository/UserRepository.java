package dev.gerasimova.repository;

import dev.gerasimova.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью User в базе данных.
 * Предоставляет стандартные CRUD-операции через наследование от JpaRepository.
 *
 * @see JpaRepository
 * @see User
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
