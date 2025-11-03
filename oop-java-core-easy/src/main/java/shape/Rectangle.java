package shape;
/**
 * Класс, представляющий геометрическую фигуру прямоугольник.
 * Наследует от абстрактного класса {@link Shape} и реализует интерфейс {@link Drawable}.
 * Содержит функциональность для расчета площади и вывода информации о фигуре.
 *
 * @see Shape
 * @see Drawable
 * @see Circle
 */
public class Rectangle extends Shape implements Drawable {

    private final double width;
    private final double height;
    /**
     * Создает прямоугольник с указанным цветом, шириной и высотой.
     *
     * @param color цвет прямоугольника
     * @param width ширина прямоугольника (должна быть положительным числом)
     * @param height высота прямоугольника (должна быть положительным числом)
     * @throws IllegalArgumentException если ширина или высота не положительные
     */
    public Rectangle(String color, double width, double height) {
        super(color);
        if (width <= 0) {
            throw new IllegalArgumentException("Ширина должна быть положительным числом: " + width);
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Высота должна быть положительным числом: " + height);
        }
        this.width = width;
        this.height = height;
    }
    /**
     * Вычисляет площадь прямоугольника.
     * Формула: ширина * высота
     *
     * @return площадь прямоугольника
     */
    @Override
    public double getArea() {
        return width * height;
    }
    /**
     * Выводит информацию о фигуре в консоль.
     * Реализует метод интерфейса {@link Drawable}.
     * Выводит цвет, размеры и площадь прямоугольника.
     *
     * @see Drawable#draw()
     */
    @Override
    public void draw() {
        System.out.printf("Рисую прямоугольник: цвет: %s, размер: %.2fx%.2f, площадь: %.2f%n",
                color, width, height, getArea());
    }
    /**
     * Возвращает ширину прямоугольника.
     *
     * @return ширина прямоугольника
     */
    public double getWidth() {
        return width;
    }

    /**
     * Возвращает высоту прямоугольника.
     *
     * @return высота прямоугольника
     */
    public double getHeight() {
        return height;
    }
}
