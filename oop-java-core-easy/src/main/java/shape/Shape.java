package shape;
/**
 * Абстрактный базовый класс для геометрических фигур.
 * Определяет общие свойства и поведение для всех фигур.
 * Содержит абстрактный метод для расчета площади, который должны реализовать наследники.
 *
 * @see Circle
 * @see Rectangle
 * @see Drawable
 */
public abstract class Shape {
    protected String color;

    /**
     * Создает фигуру с указанным цветом.
     *
     * @param color цвет фигуры (не может быть null или пустым)
     * @throws IllegalArgumentException если цвет равен null или пустой
     */
    public Shape(String color) {
        if (color == null || color.isBlank()) {
            throw new IllegalArgumentException("Цвет не может быть null или пустым");
        }
        this.color = color;
    }
    /**
     * Абстрактный метод для вычисления площади фигуры.
     * Должен быть реализован в классах-наследниках.
     *
     * @return площадь фигуры
     */
    public abstract double getArea();

    /**
     * Возвращает цвет фигуры.
     *
     * @return цвет фигуры
     */
    public String getColor() {
        return color;
    }

    /**
     * Устанавливает цвет фигуры.
     *
     * @param color новый цвет фигуры (не может быть null или пустым)
     * @throws IllegalArgumentException если цвет равен null или пустой
     */
    public void setColor(String color) {
        if (color == null || color.isBlank()) {
            throw new IllegalArgumentException("Цвет не может быть null или пустым");
        }
        this.color = color;
    }
}
