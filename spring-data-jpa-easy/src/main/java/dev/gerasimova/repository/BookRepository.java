package dev.gerasimova.repository;

import dev.gerasimova.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностью Book в базе данных.
 * Предоставляет стандартные CRUD-операции через наследование от JpaRepository.
 *
 * @see JpaRepository
 * @see Book
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
