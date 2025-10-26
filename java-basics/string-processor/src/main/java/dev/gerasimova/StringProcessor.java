package dev.gerasimova;

import java.util.Set;

/**
 * Класс для работы со строками.
 * Содержит алгоритм подсчета гласных и согласных букв в строке,
 * алгоритм подсчета слов в строке.
 */
public class StringProcessor {

    /**
     * Реализует алгоритм подсчета гласных и согласных букв в строке и печатает результат.
     * <p>
     * @param str строка для анализа.
     */
    public static void countVowelsAndConsonants (String str) {

        System.out.println("Подсчет гласных и согласных букв в строке: "+ str);

        Set<Character> vowels = Set.of('a', 'e', 'y', 'u', 'i', 'o',
                'а', 'о', 'у', 'э', 'и', 'ы', 'е', 'ё', 'ю', 'я');

        Set<Character> consonants = Set.of('q', 'w', 'r', 't', 'p', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm',
                'й', 'ц', 'к', 'н', 'г', 'ш', 'щ', 'з', 'х', 'ъ', 'ф', 'в', 'п', 'р', 'л', 'д', 'ж', 'ч', 'с', 'м', 'т', 'ь', 'б');

        int countVowels = 0;
        int countConsonants = 0;

        str = str.toLowerCase();

        for (char c: str.toCharArray()) {
            if (consonants.contains(c)) {
                countConsonants+=1;
            }
            if (vowels.contains(c)) {
                countVowels+=1;
            }
        }

        System.out.println("Гласные: " + countVowels);
        System.out.println("Согласные: " + countConsonants);
        System.out.println("____________________________________");

    }
    /**
     * Реализует алгоритм подсчета слов в строке и печатает результат.
     * <p>
     * @param str строка для анализа.
     */
    public static void wordCount (String str) {

        System.out.println("Подсчет слов в строке: "+ str);

        int countWord;

        String[] words = str.trim().replaceAll("[^А-Яа-яЁёA-Za-z\\s]", "").split("\\s+");
        countWord = words.length;

        System.out.println("Количество слов: " + countWord);
    }
    /**
     * Реализует проверку переданной строки (пустая строка или null).
     * <p>
     * @param str строка для проверки.
     * @return возвращает true/false в зависимости от результата проверки.
     */
    public static boolean isStringEmpty(String str) {
        if (str == null || str.isBlank()) {
            System.out.println("Ошибка: строка пуста или null!");
            return true;
        }
        return false;
    }

}
