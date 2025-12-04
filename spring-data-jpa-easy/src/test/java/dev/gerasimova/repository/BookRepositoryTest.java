package dev.gerasimova.repository;

import dev.gerasimova.model.Author;
import dev.gerasimova.model.Book;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Слайс-тесты для BookRepository с использованием DataJpaTest.
 * Тестирует сохранение и поиск книг в изолированной H2 in-memory базе данных.
 */
@DataJpaTest
@Transactional
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    /**
     * Создает и сохраняет автора в тестовой базе данных.
     *
     * @param name имя автора
     * @param surname фамилия автора
     * @return сохраненный автор
     */
    private Author createAndSaveAuthor(String name, String surname) {
        Author author = new Author();
        author.setName(name);
        author.setSurname(surname);
        return authorRepository.save(author);
    }
    /**
     * Создает и сохраняет книгу в тестовой базе данных.
     *
     * @param title название книги
     * @param price цена книги
     * @param year год издания
     * @param author автор книги
     * @return сохраненная книга
     */
    private Book createAndSaveBook(String title, double price, int year, Author author) {
        Book book = new Book();
        book.setTitle(title);
        book.setPrice(price);
        book.setYearRelease(year);
        book.setAuthor(author);
        return bookRepository.save(book);
    }
    /**
     * Проверяет корректность сохранения и поиска книги по идентификатору.
     * Тестирует базовую функциональность репозитория: save() и findById().
     */
    @Test
    void saveBookTest() {
        Author author = createAndSaveAuthor("Test Author name", "Test Author surname");
        Book newBook = createAndSaveBook("Test Book", 400.0, 2000, author);

        Long bookId = newBook.getId();
        Assertions.assertNotNull(bookId);

        Optional<Book> result = bookRepository.findById(bookId);
        Assertions.assertTrue(result.isPresent(),
                "Книга с title='Test Book' и authorSurname='TestSurname' должна существовать");

        Book foundBook = result.get();

        assertBooksEqual(newBook, foundBook);
    }
    /**
     * Проверяет работу кастомного метода findByTitleAndAuthorSurname.
     * Тестирует JPQL запрос с условием поиска по названию книги и фамилии автора.
     */
    @Test
    void findBookByAuthorSurnameAndTitleTest() {
        Author author = createAndSaveAuthor("TestName", "TestSurname");
        Book newBook = createAndSaveBook("Test Book", 400.0, 2000, author);

        Assertions.assertNotNull(newBook.getId());

        Optional<Book> result = bookRepository.findByTitleAndAuthorSurname("Test Book","TestSurname");

        Assertions.assertTrue(result.isPresent(),
                "Книга с title='Test Book' и authorSurname='TestSurname' должна существовать");

        Book foundBook = result.get();

        assertBooksEqual(newBook, foundBook);
    }

    /**
     * Метод для сравнения книг, новой и полученной из бд.
     * @param expected - созданная книга
     * @param actual - книга из репозитория
     */
    private void assertBooksEqual(Book expected, Book actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getTitle(), actual.getTitle());
        Assertions.assertEquals(expected.getAuthor(), actual.getAuthor());
        Assertions.assertEquals(expected.getPrice(), actual.getPrice());
        Assertions.assertEquals(expected.getYearRelease(), actual.getYearRelease());
    }
}
