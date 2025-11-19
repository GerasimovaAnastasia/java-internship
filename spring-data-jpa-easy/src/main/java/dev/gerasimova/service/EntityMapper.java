package dev.gerasimova.service;

import dev.gerasimova.dto.BookResponseDto;
import dev.gerasimova.dto.CreateBookDto;
import dev.gerasimova.dto.CreateBookWithAuthorDto;
import dev.gerasimova.model.Author;
import dev.gerasimova.model.Book;
import org.springframework.stereotype.Component;


/**
 * Компонент для преобразования между сущностями и DTO.
 * Обеспечивает маппинг данных между сервисным слоем и API.
 *
 * @see Book
 * @see Author
 * @see BookResponseDto
 * @see CreateBookDto
 * @see CreateBookWithAuthorDto
 */
@Component
public class EntityMapper {

    /**
     * Преобразует сущность книги в DTO для ответа.
     * Включает основные данные книги и фамилию автора.
     *
     * @param book сущность книги для преобразования
     * @return BookResponseDto с данными книги для ответа пользователю
     * @throws NullPointerException если book или author равен null
     */
    public BookResponseDto toDto(Book book) {
        return BookResponseDto.builder()
                .title(book.getTitle())
                .authorSurname(book.getAuthor().getSurname())
                .yearRelease(book.getYearRelease())
                .price(book.getPrice())
                .build();
    }
    /**
     * Преобразует DTO создания книги в сущность книги.
     * Не включает автора - он должен быть установлен отдельно.
     *
     * @param dto DTO с данными для создания книги
     * @return сущность книги без установленного автора
     * @see Book#setAuthor(Author)
     */
    public Book toEntity(CreateBookDto dto) {
        return Book.builder()
                .title(dto.title())
                .price(dto.price())
                .date(dto.yearRelease())
                .build();
    }
    /**
     * Преобразует DTO создания книги с автором в сущность автора.
     * Используется когда нужно создать и книгу и автора одновременно.
     *
     * @param dto DTO с данными автора из запроса
     * @return сущность автора для сохранения
     */
    public Author toEntity(CreateBookWithAuthorDto dto) {
        return Author.builder()
                .name(dto.authorName())
                .surname(dto.authorSurname())
                .build();
    }
}
