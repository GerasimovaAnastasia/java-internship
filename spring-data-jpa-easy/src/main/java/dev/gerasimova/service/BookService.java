package dev.gerasimova.service;

import dev.gerasimova.model.Book;
import dev.gerasimova.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервисный класс для управления бизнес-логикой работы с книгами.
 * Обеспечивает CRUD-операции над сущностью Book через BookRepository.
 *
 * @see Book
 * @see BookRepository
 */
@Service
public class BookService {
    private final BookRepository repository;

    /**
     * Конструктор с внедрением зависимости репозитория.
     *
     * @param repository репозиторий для работы с книгами в БД
     */
    public BookService(BookRepository repository) {
        this.repository = repository;
    }
    /**
     * Находит книгу по идентификатору.
     *
     * @param id идентификатор книги для поиска
     * @return Optional с найденной книгой или пустой Optional, если книга не найдена
     * @see BookRepository#findById(Object)
     */
    public Optional<Book> finBookById(Long id) {
        return repository.findById(id);
    }
    /**
     * Сохраняет или обновляет книгу в хранилище.
     * Если у книги не задан ID - создает новую запись.
     * Если ID задан - обновляет существующую запись.
     *
     * @param book книга для сохранения
     * @return предыдущую версию книги или null если книги не было
     * @see BookRepository#save(Object)
     */
    public Book saveBook(Book book) {
        return repository.save(book);
    }
    /**
     * Удаляет книгу из хранилища.
     *
     * @param book книга для удаления
     * @see BookRepository#delete(Object)
     */
    public void deleteBook(Book book) {
        repository.delete(book);
    }
    /**
     * Возвращает список всех книг из хранилища.
     *
     * @return список всех книг, может быть пустым
     * @see BookRepository#findAll()
     */
    public List<Book> getAllBooks() {
        return repository.findAll();
    }
    /**
     * Возвращает список всех книг конкретного автора из бд.
     *
     * @param author - автор
     * @return список всех книг конкретного автора, может быть пустым
     * @see BookRepository#findByAuthor(String author)
     */
    public List<Book> findByAuthor(String author) {
        return repository.findByAuthor(author);
    }
    /**
     * Возвращает книгу конкретного автора с заданным названием из бд.
     *
     * @param author - автор
     * @param title - название книги
     * @return книга найденная по названию и автору
     * @see BookRepository#findByTitleAndAuthor(String title, String author)
     */
    public Optional<Book> findByTitleAndAuthor(String title, String author) {
        return repository.findByTitleAndAuthor(title, author);
    }
}
