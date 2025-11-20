package dev.gerasimova.repository;

import dev.gerasimova.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Репозиторий для работы с сущностью Author в базе данных.
 * Предоставляет стандартные CRUD-операции через наследование от JpaRepository.
 *
 * @see JpaRepository
 * @see Author
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    boolean existsByNameAndSurname(String name, String surname);
}
