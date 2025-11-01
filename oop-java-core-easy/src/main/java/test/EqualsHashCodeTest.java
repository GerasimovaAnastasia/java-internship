package test;

import model.Student;
import test.utils.TestMethod;
import test.utils.TestRunnerUtil;
/**
 * Тестовый класс для проверки корректности реализации методов equals() и hashCode().
 * Проверяет соблюдение контракта между equals() и hashCode() в классе Student.
 *
 * @see Student
 * @see TestRunnerUtil
 * @see Object#equals(Object)
 * @see Object#hashCode()
 */
public final class EqualsHashCodeTest {

    /**
     * Приватный конструктор.
     */
    private EqualsHashCodeTest() {
        throw new UnsupportedOperationException("EqualsHashCodeTest class");
    }

    /**
     * Запускает все тесты для задачи по equals() и hashCode().
     *
     * @return true если все тесты пройдены, false в противном случае
     * @see #shouldReturnTrueWhenSameNameAndAge()
     * @see #shouldReturnFalseWhenDifferentName()
     * @see #shouldHaveSameHashCodeForEqualObjects()
     */
    public static boolean runEqualsHashCodeTests() {
        String[] descriptions = {
                "Объекты равны при одинаковых имени и возрасте",
                "Объекты не равны при разных именах",
                "HashCode одинаков для равных объектов"
        };

        TestMethod[] tests = {
                EqualsHashCodeTest::shouldReturnTrueWhenSameNameAndAge,
                EqualsHashCodeTest::shouldReturnFalseWhenDifferentName,
                EqualsHashCodeTest::shouldHaveSameHashCodeForEqualObjects
        };

        return TestRunnerUtil.runTestSuite("ЗАДАЧА 3: equals() и hashCode()", descriptions, tests);
    }
    /**
     * Проверяет, что объекты Student считаются равными при одинаковых имени и возрасте.
     * Разные значения среднего балла не должны влиять на результат сравнения.
     *
     * @return true если объекты равны, false в противном случае
     * @see Student#equals(Object)
     */
    public static boolean shouldReturnTrueWhenSameNameAndAge() {
        Student student1 = new Student("Иван", 20, 4.5);
        Student student2 = new Student("Иван", 20, 3.8);

        boolean areEqual = student1.equals(student2);
        System.out.println("Студенты с одинаковым именем и возрастом: " + areEqual);
        return areEqual;
    }
    /**
     * Проверяет, что объекты Student считаются разными при разных именах.
     * Одинаковые возраст и средний балл не должны влиять на результат сравнения.
     *
     * @return true если объекты не равны, false в противном случае
     * @see Student#equals(Object)
     */
    public static boolean shouldReturnFalseWhenDifferentName() {
        Student student1 = new Student("Иван", 20, 4.5);
        Student student2 = new Student("Василий", 20, 3.8);

        boolean areEqual = student1.equals(student2);
        System.out.println("Студенты с разными именами: " + areEqual);
        return !areEqual;
    }
    /**
     * Проверяет соблюдение контракта между equals() и hashCode().
     * Если два объекта равны по equals(), их hashCode() должны быть одинаковыми.
     *
     * @return true если hashCode одинаков для равных объектов, false в противном случае
     * @see Student#hashCode()
     * @see Student#equals(Object)
     */
    public static boolean shouldHaveSameHashCodeForEqualObjects() {
        Student student1 = new Student("Мария", 21, 4.9);
        Student student2 = new Student("Мария", 21, 5.0);

        boolean sameHashCode = student1.hashCode() == student2.hashCode();
        System.out.println("HashCode для равных объектов: " + sameHashCode);
        return sameHashCode;
    }
}
