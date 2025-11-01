package test;

import model.Student;
import service.ServiceStudent;
import test.utils.TestMethod;
import test.utils.TestRunnerUtil;

import java.util.List;
/**
 * Тестовый класс для проверки обработки исключений при работе с файлами.
 * Содержит тесты для проверки корректного чтения файлов и обработки ошибок ввода-вывода.
 *
 * @see ServiceStudent
 * @see Student
 * @see TestRunnerUtil
 */
public final class ExceptionHandlingTest {

    /**
     * Приватный конструктор.
     */
    private ExceptionHandlingTest() {
        throw new UnsupportedOperationException("ExceptionHandlingTest class");
    }

    /**
     * Запускает все тесты для задачи по обработке исключений.
     *
     * @return true если все тесты пройдены, false в противном случае
     * @see #shouldReadStudentsFromFileSuccessfully()
     * @see #shouldHandleMissingFileGracefully()
     */
    public static boolean runExceptionHandlingTests() {
        String[] descriptions = {
                "Успешное чтение студентов из файла",
                "Корректная обработка отсутствующего файла"
        };

        TestMethod[] tests = {
                ExceptionHandlingTest::shouldReadStudentsFromFileSuccessfully,
                ExceptionHandlingTest::shouldHandleMissingFileGracefully
        };

        return TestRunnerUtil.runTestSuite("ЗАДАЧА 9: Обработка исключений", descriptions, tests);
    }

    /**
     * Проверяет успешное чтение студентов из существующего файла.
     * Создает тестовый файл, читает его и проверяет корректность загруженных данных.
     *
     * @return true если файл прочитан успешно и данные корректны, false в противном случае
     * @see ServiceStudent#createTestFile(String)
     * @see ServiceStudent#readFromFileStudents(String)
     */
    public static boolean shouldReadStudentsFromFileSuccessfully() {

        ServiceStudent.createTestFile("students_test.txt");

        List<Student> students = ServiceStudent.readFromFileStudents("students_test.txt");

        boolean testPassed = students.size() == 3;
        boolean namesCorrect = students.stream()
                .map(Student::getName)
                .allMatch(name -> List.of("Maria", "Alex", "John").contains(name));

        System.out.println("Загружено студентов: " + students.size());
        System.out.println("Имена корректны: " + namesCorrect);

        return testPassed && namesCorrect;
    }
    /**
     * Проверяет корректную обработку ситуации, когда файл не существует.
     * Метод должен вернуть пустой список вместо выбрасывания исключения.
     *
     * @return true если метод корректно обработал отсутствующий файл, false в противном случае
     * @see ServiceStudent#readFromFileStudents(String)
     */
    public static boolean shouldHandleMissingFileGracefully() {

        List<Student> lines = ServiceStudent.readFromFileStudents("non_existent_file.txt");
        boolean testPassed = lines.isEmpty();

        System.out.println("Результат для несуществующего файла: " + lines.size() + " строк");
        return testPassed;
    }
}
