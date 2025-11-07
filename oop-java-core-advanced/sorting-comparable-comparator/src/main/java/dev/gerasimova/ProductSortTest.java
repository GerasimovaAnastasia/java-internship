package dev.gerasimova;

import java.util.List;

/**
 * Тестовый класс для проверки различных способов сортировки товаров.
 * Проверяет работу Comparable и Comparator для сортировки по разным критериям.
 */
public class ProductSortTest {

    /**
     * Приватный конструктор.
     */
    private ProductSortTest() {
        throw new UnsupportedOperationException("ProductSortTest class");
    }

    /**
     * Запускает все тесты для задачи динамической сортировки (Comparable и Comparator).
     *
     * @see #testComparableSort()
     * @see #testNameComparatorSort()
     * @see #testWeightComparatorSort()
     */
    public static void runCollectionTests() {
        String[] descriptions = {
                "Проверяем работу естественной сортировки через Comparable по цене",
                "Проверяем работу сортировки через Comparator по имени",
                "Проверяем работу сортировки через Comparator по весу в обратном порядке"
        };

        TestMethod[] tests = {
                ProductSortTest::testComparableSort,
                ProductSortTest::testNameComparatorSort,
                ProductSortTest::testWeightComparatorSort
        };

        TestRunnerUtil.runTestSuite("ЗАДАЧА 5: Динамическая Сортировка (Comparable и Comparator)", descriptions, tests);
    }

    /**
     * Тестирует естественную сортировку товаров по цене через Comparable.
     * Проверяет, что товары отсортированы по возрастанию цены.
     *
     * @return true если товары отсортированы по цене, false в противном случае
     * @see Product#compareTo(Product)
     */
    public static boolean testComparableSort() {
        List<Product> products = ProductService.createSampleData();
        System.out.println("До сортировки: " + products);

        ProductService.sortWithComparable(products);
        System.out.println("После сортировки: " + products);

        boolean isSorted = true;
        for (int i = 0; i < products.size() - 1; i++) {
            if (products.get(i).getPrice() > products.get(i + 1).getPrice()) {
                isSorted = false;
                break;
            }
        }

        return isSorted;
    }

    /**
     * Тестирует сортировку товаров по имени через Comparator.
     * Проверяет, что товары отсортированы в алфавитном порядке по названию.
     *
     * @return true если товары отсортированы по имени, false в противном случае
     * @see ProductService#sortWithComparatorForName(List)
     */
    public static boolean testNameComparatorSort() {
        List<Product> products = ProductService.createSampleData();
        System.out.println("До сортировки: " + products);

        ProductService.sortWithComparatorForName(products);
        System.out.println("После сортировки: " + products);

        boolean isSorted = true;
        for (int i = 0; i < products.size() - 1; i++) {
            if (products.get(i).getName().compareTo(products.get(i + 1).getName()) > 0) {
                isSorted = false;
                break;
            }
        }

        return isSorted;
    }

    /**
     * Тестирует сортировку товаров по весу в обратном порядке через Comparator.
     * Проверяет, что товары отсортированы по убыванию веса.
     *
     * @return true если товары отсортированы по весу по убыванию, false в противном случае
     * @see ProductService#sortWithComparatorForWeight(List)
     */
    public static boolean testWeightComparatorSort() {
        List<Product> products = ProductService.createSampleData();
        System.out.println("До сортировки: " + products);

        ProductService.sortWithComparatorForWeight(products);
        System.out.println("После сортировки: " + products);

        boolean isSorted = true;
        for (int i = 0; i < products.size() - 1; i++) {
            if (products.get(i).getWeight() < products.get(i + 1).getWeight()) {
                isSorted = false;
                break;
            }
        }

        return isSorted;
    }
}