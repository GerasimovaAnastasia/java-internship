package dev.gerasimova;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.stream.Collectors;
/**
 * Сервис для работы с коллекциями email-адресов.
 * Предоставляет методы для создания тестовых данных, удаления дубликатов и анализа доменов.
 */
public class CollectionService {

    /**
     * Создает список почтовых адресов с тестовыми данными.
     * Содержит дубликаты для демонстрации работы методов.
     *
     * @return список почтовых адресов
     */
    public static List<String> createSampleEmails() {

        String email1 = "march@gmail.com";
        String email2 = "april@yandex.ru";
        String email3 = "may@yandex.ru";
        String email4 = "june@mail.com";
        String email5 = "july@yandex.ru";
        String email6 = "august@gmail.com";
        String email7 = "august@gmail.com";

        List<String> list = new ArrayList<>();

        list.add(email1);
        list.add(email2);
        list.add(email3);
        list.add(email4);
        list.add(email5);
        list.add(email6);
        list.add(email7);

        return list;
    }
    /**
     * Удаляет дубликаты из списка email-адресов.
     *
     * @param list список email-адресов
     * @return множество уникальных email-адресов
     */
    public static Set<String> removeDuplicates(List<String> list) {
        return new HashSet<>(list);
    }

    /**
     * Подсчитывает количество email-адресов для каждого домена.
     *
     * @param list множество email-адресов
     * @return карта, где ключ - домен, значение - количество адресов
     */
    public static Map<String, Integer> countDomains(Set<String> list) {

        return list.stream()
                .map(email -> email.substring(email.indexOf('@') + 1))
                .collect(Collectors.groupingBy(
                        domain -> domain,
                        Collectors.summingInt(e -> 1)
                ));
    }
}
