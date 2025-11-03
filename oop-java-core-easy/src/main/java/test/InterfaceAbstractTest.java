package test;

import shape.Shape;
import shape.Circle;
import shape.Rectangle;
import shape.Drawable;
import test.utils.TestMethod;
import test.utils.TestRunnerUtil;

import java.util.Arrays;
import java.util.List;
/**
 * Тестовый класс для проверки функциональности интерфейсов и абстрактных классов.
 * Содержит тесты для расчета площади фигур.
 *
 * @see Circle
 * @see Rectangle
 * @see Shape
 * @see Drawable
 * @see TestRunnerUtil
 */
public final class InterfaceAbstractTest {

    /**
     * Приватный конструктор.
     */
    private InterfaceAbstractTest() {
        throw new UnsupportedOperationException("InterfaceAbstractTest class");
    }

    /**
     * Запускает набор тестов для проверки работы с интерфейсами и абстрактными классами.
     *
     * @return true если все тесты прошли успешно, false в противном случае
     * @see #shouldCalculateTotalAreaOfDifferentShapes()
     */
    public static boolean runInterfaceAbstractTests() {
        String[] descriptions = {
                "Расчет общей площади фигур"
        };

        TestMethod[] tests = {
                InterfaceAbstractTest::shouldCalculateTotalAreaOfDifferentShapes};

        return TestRunnerUtil.runTestSuite("ЗАДАЧА 6: Интерфейсы и абстрактные классы", descriptions, tests);
    }
    /**
     * Тестирует расчет общей площади различных фигур.
     * Создает список фигур (круг и прямоугольник), вычисляет их общую площадь
     * и сравнивает с ожидаемым значением.
     *
     * @return true если расчет площади корректен, false в противном случае
     * @see Circle#getArea()
     * @see Rectangle#getArea()
     * @see Math#PI
     * @see Math#abs(double)
     */
    public static boolean shouldCalculateTotalAreaOfDifferentShapes() {

        List<Shape> shapes = Arrays.asList(
                new Circle("red", 1.0),
                new Rectangle("blue", 2.0, 3.0)
        );

        double totalArea = 0.0;
        for (Shape shape : shapes) {
            totalArea += shape.getArea();
        }

        double expectedArea = Math.PI + 6.0;
        boolean testPassed = Math.abs(totalArea - expectedArea) < 0.01;

        System.out.printf("Ожидаемая площадь: %.2f%n", expectedArea);
        System.out.printf("Фактическая площадь: %.2f%n", totalArea);
        shouldDrawAllShapes();

        return testPassed;
    }
    /**
     * Демонстрирует вывод информации о всех фигурах через интерфейс Drawable.
     * Создает список объектов, реализующих интерфейс Drawable, и вызывает
     * метод draw() для каждого из них.
     */
    public static void shouldDrawAllShapes() {
        List<Drawable> shapes = Arrays.asList(
                new Circle("red", 1.0),
                new Rectangle("blue", 2.0, 3.0)
        );
        for (Drawable shape : shapes) {
            shape.draw();
        }
    }
}
