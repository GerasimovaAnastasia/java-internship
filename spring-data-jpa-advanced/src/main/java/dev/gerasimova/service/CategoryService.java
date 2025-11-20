package dev.gerasimova.service;

import dev.gerasimova.model.Category;
import dev.gerasimova.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервисный класс для управления бизнес-логикой работы с авторами.
 * Обеспечивает CRUD-операции над сущностью Category через CategoryRepository.
 *
 * @see dev.gerasimova.model.Category
 * @see dev.gerasimova.repository.CategoryRepository
**/
@Service
@RequiredArgsConstructor
public class CategoryService {
   private final CategoryRepository categoryRepository;
    /**
     * Находит категорию по идентификатору.
     *
     * @param id идентификатор категории для поиска
     * @return Optional с найденной категорией или пустой Optional, если категория не найдена
     * @see CategoryRepository#findById(Object)
     */
    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    /**
     * Сохраняет или обновляет категорию в хранилище.
     * Если у категории не задан ID - создает новую запись.
     * Если ID задан - обновляет существующую запись.
     *
     * @param category категория для сохранения
     * @return сохраненную категорию
     * @see CategoryRepository#save(Object)
     */
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Удаляет категорию из хранилища.
     *
     * @param category категория для удаления
     * @see CategoryRepository#delete(Object)
     */
    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    /**
     * Возвращает список всех категорий из хранилища.
     *
     * @return список всех категорий, может быть пустым
     * @see CategoryRepository#findAll()
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
