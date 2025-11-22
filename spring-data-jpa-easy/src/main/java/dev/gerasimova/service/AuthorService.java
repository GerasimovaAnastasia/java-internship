package dev.gerasimova.service;

import dev.gerasimova.model.Author;
import dev.gerasimova.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

 /**
  * Сервисный класс для управления бизнес-логикой работы с авторами.
  * Обеспечивает CRUD-операции над сущностью Author через AuthorRepository.
  *
  * @see dev.gerasimova.model.Book
  * @see dev.gerasimova.repository.AuthorRepository
**/
 @Service
 @RequiredArgsConstructor
 @Transactional(readOnly = true)
public class AuthorService {
    private final AuthorRepository authorRepository;
    /**
     * Находит автора по идентификатору.
     *
     * @param id идентификатор автора для поиска
     * @return Optional с найденным автором или пустой Optional, если автор не найден
     * @see AuthorRepository#findById(Object)
     */
    public Optional<Author> findAuthorById(Long id) {
        return authorRepository.findById(id);
    }
    /**
     * Сохраняет или обновляет автора в хранилище.
     * Если у автора не задан ID - создает новую запись.
     * Если ID задан - обновляет существующую запись.
     *
     * @param author автор для сохранения
     * @return предыдущую версию автора или null если автора не было
     * @see AuthorRepository#save(Object)
     */
    @Transactional
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }
    /**
     * Удаляет автора из хранилища.
     *
     * @param author автор для удаления
     * @see AuthorRepository#delete(Object)
     */
    @Transactional
    public void deleteAuthor(Author author) {
        authorRepository.delete(author);
    }
    /**
     * Возвращает список всех авторов из хранилища.
     *
     * @return список всех авторов, может быть пустым
     * @see AuthorRepository#findAll()
     */
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

     /**
      * Проверяет, существует ли автор с заданными именем и фамилией.
      *
      * @param name - имя автора
      * @param surname - фамилия автора
      * @return true/false
      * @see AuthorRepository#existsByNameAndSurname(String name, String surname)
      */
    public boolean existsByNameAndSurname(String name, String surname) {
        return authorRepository.existsByNameAndSurname(name, surname);
    }
}
