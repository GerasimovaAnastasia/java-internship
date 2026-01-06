package dev.gerasimova.service;

import dev.gerasimova.dto.CreateBookDto;
import dev.gerasimova.dto.BookCreatedEvent;
import dev.gerasimova.dto.BookResponseDto;
import dev.gerasimova.dto.UpdateBookDto;
import dev.gerasimova.dto.CreateBookWithAuthorDto;
import dev.gerasimova.dto.PaginationParam;
import dev.gerasimova.dto.BookNotificationRequest;
import dev.gerasimova.exception.AuthorException;
import dev.gerasimova.exception.BookException;
import dev.gerasimova.mapper.AuthorMapper;
import dev.gerasimova.mapper.BookMapper;
import dev.gerasimova.model.Author;
import dev.gerasimova.model.Book;
import dev.gerasimova.repository.BookRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервисный класс для управления бизнес-логикой работы с книгами.
 * Обеспечивает CRUD-операции над сущностью Book через BookRepository.
 *
 * @see Book
 * @see BookRepository
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;
    private final KafkaTemplate<String, BookCreatedEvent> kafkaTemplate;

    @Value("${kafka.topics.book-events:book_events}")
    private String bookEventsTopic;
    @Value("${testTask10}")
    private boolean test;
    /**
     * Находит книгу по идентификатору.
     *
     * @param id идентификатор книги для поиска
     * @return выводит дто для пользователей с информацией о книге
     */
    public BookResponseDto getBookById(Long id) {
        Book book = findBookById(id);
        return bookMapper.toBookResponseDto(book);
    }
    /**
     * Находит книгу по идентификатору.
     *
     * @param id идентификатор книги для поиска
     * @return Book, если книга найдена или выбрасывает исключение BookException()
     */
    private Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookException(id));
    }
    /**
     * Сохраняет книгу в хранилище.
     *
     * @param dto книги для сохранения
     * @return дто сохраненной книги
     * @see BookRepository#save(Object)
     */
    @Transactional(noRollbackFor = FeignException.class)
    public BookResponseDto saveBook(CreateBookDto dto) {
        Author author = authorService.findAuthorById(dto.authorID())
                .orElseThrow(() -> new AuthorException(dto.authorID()));
        Book book = bookMapper.toBook(dto);
        book.setAuthor(author);
        Book savedBook = bookRepository.save(book);
        BookCreatedEvent event = BookCreatedEvent.from(savedBook);
        try {
            kafkaTemplate.send(bookEventsTopic, savedBook.getId().toString(), event);
            log.info("Событие отправлено в Kafka topic '{}' для книги с  ID: {}",
                    bookEventsTopic, savedBook.getId());
        } catch (Exception e) {
            log.error("Ошибка отправки события в Kafka для книги {}: {}",
                    savedBook.getId(), e.getMessage());
        }
        return bookMapper.toBookResponseDto(savedBook);
    }
    /**
     * Обновляет книгу в хранилище.
     *
     * @param dto книги для обновления
     * @return дто сохраненной книги
     * @see BookRepository#save(Object)
     */
    @Transactional
    public BookResponseDto updateBook(UpdateBookDto dto, Long id) {
        Book existingBook = findBookById(id);
        Author author = authorService.findAuthorById(dto.authorID())
                .orElseThrow(() -> new AuthorException(dto.authorID()));

        existingBook.setTitle(dto.title());
        existingBook.setAuthor(author);
        existingBook.setYearRelease(dto.yearRelease());
        existingBook.setPrice(dto.price());

        Book savedBook = bookRepository.save(existingBook);
        return bookMapper.toBookResponseDto(savedBook);
    }
    /**
     * Удаляет книгу из хранилища.
     *
     * @param id уникальный идентификатор книги для удаления
     * @see BookRepository#delete(Object)
     */
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.delete(findBookById(id));
    }
    /**
     * Возвращает список всех книг из хранилища.
     *
     * @return список всех книг, может быть пустым
     * @see BookRepository#findAllBook(Pageable)
     */
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAllBook(pageable);
    }
    /**
     * Возвращает список всех книг конкретного автора из бд.
     *
     * @param authorSurname - фамилия автора
     * @return список всех книг конкретного автора, может быть пустым
     * @see BookRepository#findByAuthorSurname(String, Pageable)
     */
    public Page<Book> findByAuthor(String authorSurname,Pageable pageable ) {
        return bookRepository.findByAuthorSurname(authorSurname, pageable);
    }
    /**
     * Возвращает книгу по названию из бд.
     *
     * @param title - название книги
     * @return найденная книга
     * @throws BookException - если книги с заданным названием не существует
     * @see BookRepository#findByTitle(String, Pageable)
     */
    public Page<Book> findByTitle(String title, Pageable pageable) {
        return bookRepository.findByTitle(title, pageable);
    }
    /**
     * Возвращает книгу конкретного автора с заданным названием из бд.
     *
     * @param author - автор
     * @param title - название книги
     * @return книга найденная по названию и автору
     * @see BookRepository#findByTitleAndAuthorSurname(String, String)
     */
    public Book findByTitleAndAuthor(String title, String author) {
        return bookRepository.findByTitleAndAuthorSurname(title, author)
                .orElseThrow(()-> new BookException("Книги с названием " + title + "и автором "+ author +" не существует"));
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
                .map(bookMapper::toBookResponseDto)
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
    @Transactional()
    public BookResponseDto createBookWithAuthor(CreateBookWithAuthorDto dto) {
        if (authorService.existsByNameAndSurname(dto.authorName(), dto.authorSurname())) {
            throw new AuthorException("Автор уже существует: " + dto.authorName() + " " + dto.authorSurname());
        }
        Author saveAuthor = authorService.saveAuthor(authorMapper.toAuthor(dto));
        if (test) {
            throw new RuntimeException("Тест отката транзакции!");
        }
        Book book = Book.builder()
                .title(dto.title())
                .author(saveAuthor)
                .price(dto.price())
                .date(dto.yearRelease())
                .build();

        return bookMapper.toBookResponseDto(bookRepository.save(book));
    }
    /**
     * Метод для получения списка книг/списка книг конкретного автора/
     * конкретной книги по названию и автору.
     *
     * @return - список книг или одну книг при поиске по автору и названию или пустой список.
     */
    public Page<BookResponseDto> searchBook(String authorSurname, String title, PaginationParam paginationParam) {
        boolean hasAuthor = authorSurname != null && !authorSurname.isBlank();
        boolean hasTitle = title != null && !title.isBlank();
        Pageable pageable = convertURLtoPageable(paginationParam.page(), paginationParam.size(), paginationParam.sort());

        if (hasAuthor && hasTitle) {
            List<Book> list = List.of(findByTitleAndAuthor(title, authorSurname));
            Page<Book> bookPage = new PageImpl<>(list, pageable, list.size());
            return bookPage
                    .map(bookMapper::toBookResponseDto);
        } else if (hasAuthor) {
            return findByAuthor(authorSurname, pageable)
                    .map(bookMapper::toBookResponseDto);
        } else if (hasTitle) {
            return findByTitle(title, pageable)
                    .map(bookMapper::toBookResponseDto);
        } else {
            return getAllBooks(pageable)
                    .map(bookMapper::toBookResponseDto);
        }
    }
    /**
     * Преобразует параметры URL в объект Pageable для пагинации и сортировки.
     * Используется для создания объекта пагинации на основе параметров HTTP-запроса.
     *
     * @param page номер страницы (начинается с 0)
     * @param size количество элементов на странице
     * @param sort строка сортировки в формате "поле,направление" или просто "поле"
     * @return объект Pageable с настройками пагинации и сортировки
     * @see Pageable
     * @see PageRequest
     */
    private Pageable convertURLtoPageable(int page, int size, String sort) {
        Sort sortObj = (sort != null && !sort.isBlank())
                ? parseSort(sort)
                : Sort.unsorted();
       return PageRequest.of(page, size, sortObj);
    }
    /**
     * Парсит строку сортировки в объект Sort.
     * Поддерживает два формата: простое поле ("title") и поле с направлением ("price,desc").
     *
     * @param sort строка для парсинга, может быть null или пустой
     * @return объект Sort, либо Sort.unsorted() если строка пустая
     * @see Sort
     */
    private Sort parseSort(String sort) {
        if (sort == null || sort.isBlank()) {
            return Sort.unsorted();
        }
        if (sort.contains(",")) {
            String[] parts = sort.split(",");
            if (parts.length == 2) {
                return Sort.by(Sort.Direction.fromString(parts[1]), parts[0]);
            }
        }
        return Sort.by(sort);
    }
}
