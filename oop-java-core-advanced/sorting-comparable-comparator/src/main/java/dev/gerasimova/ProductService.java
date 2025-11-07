package dev.gerasimova;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

/**
 * Сервисный класс для работы с товарами и их сортировкой.
 * Предоставляет методы для создания тестовых данных и различных способов сортировки.
 */
public class ProductService {

    /**
     * Создает список товаров с тестовыми данными.
     *
     * @return список товаров для тестирования сортировки
     */
    public static List<Product> createSampleData() {

        List<Product> productList = new ArrayList<>();

        productList.add(new Product(34,"A",7));
        productList.add(new Product(32,"C",6));
        productList.add(new Product(14,"B",9));

        return productList;
    }

    /**
     * Сортирует список товаров по цене используя естественный порядок (Comparable).
     *
     * @param productList список товаров для сортировки
     * @see Product#compareTo(Product)
     */
    public static void sortWithComparable(List<Product> productList) {
        Collections.sort(productList);
    }

    /**
     * Сортирует список товаров по названию в алфавитном порядке используя Comparator.
     *
     * @param productList список товаров для сортировки
     * @see Comparator#comparing(java.util.function.Function)
     */
    public static void sortWithComparatorForName(List<Product> productList) {
        productList.sort(Comparator.comparing(Product::getName));
    }

    /**
     * Сортирует список товаров по весу в обратном порядке (по убыванию) используя Comparator.
     *
     * @param productList список товаров для сортировки
     * @see Comparator#comparingDouble(java.util.function.ToDoubleFunction)
     * @see Comparator#reversed()
     */
    public static void sortWithComparatorForWeight(List<Product> productList) {
        productList.sort(Comparator.comparingDouble(Product::getWeight).reversed());
    }
}
