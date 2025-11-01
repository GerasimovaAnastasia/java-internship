package shape;
/**
 * Класс, представляющий геометрическую фигуру круг.
 * Наследует от абстрактного класса {@link Shape} и реализует интерфейс {@link Drawable}.
 * Содержит функциональность для расчета площади и вывода информации о фигуре.
 *
 * @see Shape
 * @see Drawable
 * @see Rectangle
 */
public class Circle extends Shape implements Drawable {

    private final double radius;
    /**
     * Создает круг с указанным цветом и радиусом.
     *
     * @param color цвет круга
     * @param radius радиус круга (должен быть положительным числом)
     * @throws IllegalArgumentException если радиус не положительный
     */
    public Circle(String color, double radius) {
        super(color);
        if (radius <= 0) {
            throw new IllegalArgumentException("Радиус должен быть положительным числом: " + radius);
        }
        this.radius = radius;
    }
    /**
     * Вычисляет площадь круга.
     * Формула: pi * радиус^2
     *
     * @return площадь круга
     * @see Math#PI
     */
    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    /**
     * Выводит информацию о фигуре в консоль.
     * Реализует метод интерфейса {@link Drawable}.
     * Выводит цвет, радиус и площадь круга.
     *
     * @see Drawable#draw()
     */
    @Override
    public void draw() {
        System.out.printf("Рисую круг: цвет: %s, радиус: %.2f, площадь: %.2f%n",
                color, radius, getArea());
    }

    /**
     * Возвращает радиус круга.
     *
     * @return радиус круга
     */
    public double getRadius() {
        return radius;
    }

}
