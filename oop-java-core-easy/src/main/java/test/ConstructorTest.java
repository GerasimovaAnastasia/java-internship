package test;

import model.Student;
import test.utils.TestMethod;
import test.utils.TestRunnerUtil;
/**
 * Тестовый класс для проверки перегрузки конструкторов класса Student.
 * Содержит тесты для всех трех конструкторов: пустого, с именем и возрастом, полного.
 *
 * @see Student
 * @see TestRunnerUtil
 */
public final class ConstructorTest {

    /**
     * Приватный конструктор.
     */
    private ConstructorTest() {
        throw new UnsupportedOperationException("ConstructorTest class");
    }

    /**
     * Запускает все тесты для задачи по перегрузке конструкторов.
     *
     * @return true если все тесты пройдены, false в противном случае
     * @see #shouldCreateWithEmptyConstructor()
     * @see #shouldCreateWithNameAndAgeConstructor()
     * @see #shouldCreateWithFullConstructor()
     */
    public static boolean runConstructorTests() {
        String[] descriptions = {
                "Создание объекта пустым конструктором",
                "Создание объекта с именем и возрастом",
                "Создание объекта со всеми полями"
        };

        TestMethod[] tests = {
                ConstructorTest::shouldCreateWithEmptyConstructor,
                ConstructorTest::shouldCreateWithNameAndAgeConstructor,
                ConstructorTest::shouldCreateWithFullConstructor
        };

        return TestRunnerUtil.runTestSuite("ЗАДАЧА 2: Перегрузка конструкторов", descriptions, tests);
    }
    /**
     * Проверяет создание объекта Student с помощью пустого конструктора.
     *
     * @return true если объект успешно создан, false в противном случае
     * @see Student#Student()
     */
    public static boolean shouldCreateWithEmptyConstructor() {
        try {
            Student student = new Student();
            System.out.println("Пустой конструктор: создан объект " + student);
            return true;
        } catch (Exception e) {
            System.out.println("Пустой конструктор: ошибка - " + e.getMessage());
            return false;
        }
    }
    /**
     * Проверяет создание объекта Student с помощью конструктора с именем и возрастом.
     * Дополнительно проверяет корректность установки значений полей.
     *
     * @return true если объект создан и поля установлены корректно, false в противном случае
     * @see Student#Student(String, int)
     */
    public static boolean shouldCreateWithNameAndAgeConstructor() {
        try {
            Student student = new Student("Иван", 20);
            boolean nameCorrect = "Иван".equals(student.getName());
            boolean ageCorrect = student.getAge() == 20;
            System.out.println("Конструктор (name, age): создан " + student);
            return nameCorrect && ageCorrect;
        } catch (Exception e) {
            System.out.println("Конструктор (name, age): ошибка - " + e.getMessage());
            return false;
        }
    }
    /**
     * Проверяет создание объекта Student с помощью полного конструктора.
     * Проверяет корректность установки всех полей: имени, возраста и среднего балла.
     *
     * @return true если объект создан и все поля установлены корректно, false в противном случае
     * @see Student#Student(String, int, double)
     */
    public static boolean shouldCreateWithFullConstructor() {
        try {
            Student student = new Student("Мария", 21, 4.9);
            boolean nameCorrect = "Мария".equals(student.getName());
            boolean ageCorrect = student.getAge() == 21;
            boolean averageGrade = student.getAverageGrade() == 4.9;
            System.out.println("Полный конструктор: создан " + student);

            return nameCorrect && ageCorrect && averageGrade;
        } catch (Exception e) {
            System.out.println("Полный конструктор: ошибка - " + e.getMessage());
            return false;
        }
    }
}
