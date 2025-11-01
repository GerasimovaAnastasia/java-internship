package shape;
/**
 * Интерфейс для объектов, которые могут быть отрисованы.
 *
 * @see Shape
 * @see Circle
 * @see Rectangle
 */
public interface Drawable {
    /**
     * Выводит информацию об объекте.
     *
     * @see Circle#draw()
     * @see Rectangle#draw()
     */
    void draw();
}
