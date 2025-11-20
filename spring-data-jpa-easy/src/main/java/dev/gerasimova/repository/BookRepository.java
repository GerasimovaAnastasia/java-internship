package dev.gerasimova.repository;

import dev.gerasimova.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с сущностью Book в базе данных.
 * Предоставляет стандартные CRUD-операции через наследование от JpaRepository.
 *
 * @see JpaRepository
 * @see Book
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b JOIN FETCH b.author")
    List<Book> findAllWithAuthor();
    @Query("SELECT b FROM Book b JOIN FETCH b.author WHERE b.author.surname = :surname")
    List<Book> findByAuthorSurname(@Param("surname") String authorSurname);

    @Query("SELECT b FROM Book b JOIN FETCH b.author WHERE b.author.surname = :surname AND " +
            "b.title = :title")
    Optional<Book> findByTitleAndAuthorSurname(@Param("title") String title, @Param("surname") String author);

    @Query("SELECT b FROM Book b JOIN FETCH b.author WHERE lower(b.title) LIKE lower(concat('%', :searchText, '%')) " +
            "ORDER BY b.yearRelease DESC")
    List<Book> searchByTitleOrderByYear(@Param("searchText") String searchText);
}
