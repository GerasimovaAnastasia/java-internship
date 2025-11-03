package test;

import model.Person;
import model.Student;
import model.Teacher;
import test.utils.TestRunnerUtil;
import test.utils.VoidTestMethod;
/**
 * Тестовый класс для демонстрации наследования и полиморфизма.
 * Показывает работу полиморфизма через массив базового класса Person,
 * содержащий объекты разных наследующих классов.
 *
 * @see Person
 * @see Student
 * @see Teacher
 * @see TestRunnerUtil
 */
public final class InheritancePolymorphismTest {

    /**
     * Приватный конструктор.
     */
    private InheritancePolymorphismTest() {
        throw new UnsupportedOperationException("InheritancePolymorphismTest class");
    }

    /**
     * Запускает все тесты для задачи по наследованию и полиморфизму.
     *
     * @return true если все тесты пройдены, false в противном случае
     * @see #shouldDemonstrateRuntimePolymorphism()
     */
    public static boolean runInheritancePolymorphismTests() {

        String[] descriptions = {
                "Демонстрация работы полиморфизма"
        };

        VoidTestMethod[] tests = {
                InheritancePolymorphismTest::shouldDemonstrateRuntimePolymorphism
        };

        return TestRunnerUtil.runVoidTestSuite("ЗАДАЧА 5: Наследование и полиморфизм", descriptions, tests);
    }
    /**
     * Демонстрирует полиморфизм через массив базового класса Person.
     * Создает массив типа Person, содержащий объекты Student и Teacher,
     * и вызывает метод introduce() для каждого объекта. В runtime определяется,
     * какая именно реализация метода должна быть вызвана.
     *
     * @see Person#introduce()
     * @see Student#introduce()
     * @see Teacher#introduce()
     */
    public static void shouldDemonstrateRuntimePolymorphism() {

        Person[] array = new Person[]{
                new Student("Maria", 21, 4.3),
                new Student("Alex", 20, 4.5),
                new Teacher("Victor", 44, "professor"),
        };

        for (Person p: array) {

            p.introduce();
        }
    }
}
