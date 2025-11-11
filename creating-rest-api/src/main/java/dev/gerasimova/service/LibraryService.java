package dev.gerasimova.service;


import dev.gerasimova.exception.BookException;
import dev.gerasimova.model.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Cервисный класс-библиотека для хранения книг и взаимодействия с ними.
 * Имплементирует интерфейс CommandLineRunner для сохранения стартовых данных после
 * инициализации spring-контекста.
 */
@Service
public class LibraryService implements CommandLineRunner {
    private final Map<Long, Book> bookMap;

    /**
     * Конструктор инициализирует Map для хранения книг.
     */
    public LibraryService() {
        this.bookMap = new HashMap<>();
    }

    /**
     * Метод заполняет Map тестовыми данными.
     */
    private void initStartData() {
        bookMap.put(1L, new Book(1L,"Transformation","Kafka", 400.0));
        bookMap.put(2L, new Book(2L,"Crime and punishment","Dostoevsky", 750.0));
        bookMap.put(3L, new Book(3L,"WarAndPeace", "Tolstoy", 1500.0));
    }

    /**
     * Метод запускающийся сразу после инициализации spring-контекста,
     * запускает метод для загрузки тестовых данных в Map.
     * @param args - параметры типа String
     */
    @Override
    public void run(String... args) {
        initStartData();
    }
    /**
     * Находит книгу по идентификатору.
     *
     * @param id уникальный идентификатор книги
     * @return книгу, если найдена, иначе BookException
     */
    public Book getBookById(Long id) {
        Book book = bookMap.get(id);
        if (book == null) {
            throw new BookException(id);
        }
        return book;
    }
    /**
     * Метод возвращает книгу по названию.
     * Название не может быть пустым или равным нулю.
     *
     * @param title - название книги
     * @return - книга с заданным названием.
     */
    public Book getBookByTitle(String title) {
        return bookMap.values().stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(() -> new BookException("Книга с названием '" + title + "' не найдена"));
    }
    /**
     * Сохраняет книгу в хранилище.
     * Если книга с таким ID уже существует - перезаписывает её.
     *
     * @param book книга для сохранения
     * @return предыдущую версию книги или null если книги не было
     */
    public Book saveBook(Book book) {
        return bookMap.put(book.getId(), book);
    }
    /**
     * Метод удаляет книгу из хранилища.
     *
     * @param id - уникальный идентификатор книги.
     */
    public void deleteBook(Long id) {
        if(bookMap.get(id) == null) {
            throw new BookException(id);
        }
        bookMap.remove(id);
    }
    /**
     * Метод возвращает все книги из хранилища.
     * @return список книг из хранилища.
     */
    public List<Book> getAllBooks() {
        return bookMap.values().stream().toList();
    }
}
