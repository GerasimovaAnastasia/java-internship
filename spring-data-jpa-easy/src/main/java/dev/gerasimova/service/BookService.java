package dev.gerasimova.service;

import dev.gerasimova.dto.BookResponseDto;
import dev.gerasimova.dto.CreateBookDto;
import dev.gerasimova.dto.CreateBookWithAuthorDto;
import dev.gerasimova.dto.UpdateBookDto;
import dev.gerasimova.exception.AuthorException;
import dev.gerasimova.exception.BookException;
import dev.gerasimova.model.Author;
import dev.gerasimova.model.Book;
import dev.gerasimova.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final EntityMapper entityMapper;
    @Value("${testTask10}")
    private boolean test;
    /**
     * Находит книгу по идентификатору.
     *
     * @param id идентификатор книги для поиска
     * @return выводит дто для пользователей с информацией о книге
     * @see BookRepository#findById(Object)
     */
    public BookResponseDto getBookById(Long id) {
        Book book = findBookById(id);
        return entityMapper.toDto(book);
    }
    /**
     * Находит книгу по идентификатору.
     *
     * @param id идентификатор книги для поиска
     * @return Book, если книга найдена или выбрасывает исключение BookException()
     * @see BookRepository#findById(Object)
     */
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookException(id));
    }
    /**
     * Сохраняет книгу в хранилище.
     *
     * @param dto книги для сохранения
     * @return дто сохраненной книги
     * @see BookRepository#save(Object)
     */
    public BookResponseDto saveBook(CreateBookDto dto) {
        Author author = authorService.findAuthorById(dto.authorID())
                .orElseThrow(() -> new AuthorException(dto.authorID()));
        Book book = entityMapper.toEntity(dto);
        book.setAuthor(author);
        Book savedBook = bookRepository.save(book);
        return entityMapper.toDto(savedBook);
    }
    /**
     * Обновляет книгу в хранилище.
     *
     * @param dto книги для обновления
     * @return дто сохраненной книги
     * @see BookRepository#save(Object)
     */
    public BookResponseDto updateBook(UpdateBookDto dto, Long id) {
        Book existingBook = findBookById(id);
        Author author = authorService.findAuthorById(dto.authorID())
                .orElseThrow(() -> new AuthorException(dto.authorID()));

        existingBook.setTitle(dto.title());
        existingBook.setAuthor(author);
        existingBook.setYearRelease(dto.yearRelease());
        existingBook.setPrice(dto.price());

        Book savedBook = bookRepository.save(existingBook);
        return entityMapper.toDto(savedBook);
    }
    /**
     * Удаляет книгу из хранилища.
     *
     * @param id уникальный идентификатор книги для удаления
     * @see BookRepository#delete(Object)
     */
    public void deleteBook(Long id) {
        bookRepository.delete(findBookById(id));
    }
    /**
     * Возвращает список всех книг из хранилища.
     *
     * @return список всех книг, может быть пустым
     * @see BookRepository#findAll()
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAllWithAuthor();
    }
    /**
     * Возвращает список всех книг конкретного автора из бд.
     *
     * @param authorSurname - фамилия автора
     * @return список всех книг конкретного автора, может быть пустым
     * @see BookRepository#findByAuthorSurname(String)
     */
    public List<Book> findByAuthor(String authorSurname) {
        return bookRepository.findByAuthorSurname(authorSurname);
    }
    /**
     * Возвращает книгу конкретного автора с заданным названием из бд.
     *
     * @param author - автор
     * @param title - название книги
     * @return книга найденная по названию и автору
     * @see BookRepository#findByTitleAndAuthorSurname(String title, String author)
     */
    public Optional<Book> findByTitleAndAuthor(String title, String author) {
        return bookRepository.findByTitleAndAuthorSurname(title, author);
    }
    /**
     * Возвращает список книг по части названия книги без учета регистра и сортирует их по году издания.
     *
     * @param searchText - часть названия
     * @return список книг
     * @see BookRepository#searchByTitleOrderByYear(String)
     */
    public List<BookResponseDto> searchByTitleOrderByYear(String searchText) {
        return bookRepository.searchByTitleOrderByYear(searchText).stream()
                .map(entityMapper::toDto)
                .toList();
    }

    /**
     * Сохраняет в бд 2 сущности: новую книгу с новым автором, сели при сохранении падает исключение,
     * происходит rollback - откат, не сохраняется ни одна сущность.
     *
     * @param dto - дто с данными о новом авторе и новой книге
     * @return дто с новой книгой
     * @throws AuthorException - если автор уже существует
     */
    @Transactional(rollbackOn = Exception.class)
    public BookResponseDto createBookWithAuthor(CreateBookWithAuthorDto dto) {
        if (authorService.existsByNameAndSurname(dto.authorName(), dto.authorSurname())) {
            throw new AuthorException("Автор уже существует: " + dto.authorName() + " " + dto.authorSurname());
        }
        Author saveAuthor = authorService.saveAuthor(entityMapper.toEntity(dto));
        if (test) {
            throw new RuntimeException("Тест отката транзакции!");
        }
        Book book = Book.builder()
                .title(dto.title())
                .author(saveAuthor)
                .price(dto.price())
                .date(dto.yearRelease())
                .build();

        return entityMapper.toDto(bookRepository.save(book));
    }
    /**
     * Метод для получения списка книг/списка книг конкретного автора/
     * конкретной книги по названию и автору.
     *
     * @return - список книг или одну книг при поиске по автору и названию или пустой список.
     */
    public List<BookResponseDto> searchBook(String authorSurname, String title) {
        boolean hasAuthor = authorSurname != null && !authorSurname.isBlank();
        boolean hasTitle = title != null && !title.isBlank();

        if (hasAuthor && hasTitle) {
            return findByTitleAndAuthor(title, authorSurname)
                    .map(entityMapper::toDto)
                    .map(List::of)
                    .orElse(Collections.emptyList());
        } else if (hasAuthor) {
            return findByAuthor(authorSurname).stream()
                    .map(entityMapper::toDto)
                    .toList();
        } else {
            return getAllBooks().stream()
                    .map(entityMapper::toDto)
                    .toList();
        }
    }
}
