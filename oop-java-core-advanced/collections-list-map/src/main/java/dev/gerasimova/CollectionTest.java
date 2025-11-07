package dev.gerasimova;

import java.util.Map;
import java.util.Set;

/**
 * Тестовый класс для задачи по коллекциям List и Map.
 *
 * @see CollectionService
 * @see TestRunnerUtil
 */
public class CollectionTest {

    /**
     * Приватный конструктор.
     */
    private CollectionTest() {
        throw new UnsupportedOperationException("CollectionTest class");
    }

    /**
     * Запускает все тесты для задачи по коллекциям List и Map.
     *
     * @see #shouldDeleteDuplicates()
     * @see #shouldCountDomains()
     */
    public static void runCollectionTests() {
        String[] descriptions = {
                "Проверяем наличие дубликатов",
                "проверяем работу HashMap"
        };

        TestMethod[] tests = {
                CollectionTest::shouldDeleteDuplicates,
                CollectionTest::shouldCountDomains
        };

        TestRunnerUtil.runTestSuite("ЗАДАЧА 4: Коллекции List и Map", descriptions, tests);
    }
    /**
     * Тестирует удаление дубликатов из списка email-адресов.
     * Проверяет, что дублирующиеся адреса удаляются корректно.
     *
     * @return true если дубликаты удалены правильно, false в противном случае
     */
    public static boolean shouldDeleteDuplicates() {
        Set<String> set = CollectionService.removeDuplicates(CollectionService.createSampleEmails());

        long count = set.stream()
                .filter(str -> str.equals("august@gmail.com"))
                .count();
        return count == 1;
    }
    /**
     * Тестирует подсчет доменов email-адресов.
     * Проверяет корректность подсчета количества адресов для каждого домена.
     *
     * @return true если подсчет доменов выполнен правильно, false в противном случае
     */
    public static boolean shouldCountDomains() {
        Set<String> set = CollectionService.removeDuplicates(CollectionService.createSampleEmails());
        Map<String, Integer> domainCount = CollectionService.countDomains(set);

        boolean testPassed = true;

        if (domainCount.get("gmail.com") != 2) {
            System.out.println(STR."gmail.com: ожидалось 2, получено \{domainCount.get("gmail.com")}");
            testPassed = false;
        } else {
            System.out.println("gmail.com: 2 (правильно)");
        }
        if (domainCount.get("yandex.ru") != 3) {
            System.out.println(STR."yandex.ru: ожидалось 3, получено \{domainCount.get("yandex.ru")}");
            testPassed = false;
        } else {
            System.out.println("yandex.ru: 3 (правильно)");
        }

        if (domainCount.get("mail.com") != 1) {
            System.out.println(STR."mail.com: ожидалось 1, получено \{domainCount.get("mail.com")}");
            testPassed = false;
        } else {
            System.out.println("mail.com: 1 (правильно)");
        }

        return testPassed;
    }
}
