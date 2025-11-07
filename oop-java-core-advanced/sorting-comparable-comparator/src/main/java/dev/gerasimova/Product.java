package dev.gerasimova;

/**
 * Класс, представляющий товар с ценой, названием и весом.
 * Реализует интерфейс Comparable для естественной сортировки по цене.
 * Содержит валидацию входных данных.
 */
public class Product implements Comparable<Product>{
    private double price;
    private String name;
    private double weight;

    /**
     * Создает объект товара с указанными параметрами.
     *
     * @param price цена товара
     * @param name название товара
     * @param weight вес товара
     * @throws IllegalArgumentException если цена, вес отрицательные или название пустое
     */
    public Product(double price, String name, double weight) {
        setWeight(weight);
        setPrice(price);
        setName(name);
    }

    /**
     * Сравнивает товары по цене для естественной сортировки.
     *
     * @param o товар для сравнения
     * @return отрицательное число, если текущий товар дешевле,
     *         положительное - если дороже, 0 - если цены равны
     */
    @Override
    public int compareTo(Product o) {
        if (this.price > o.getPrice()) return 1;
        if (this.price < o.getPrice()) return -1;
        return 0;
    }
    /**
     * Возвращает цену товара.
     *
     * @return цена товара
     */
    public double getPrice() {
        return price;
    }

    /**
     * Устанавливает цену товара.
     *
     * @param price цена товара
     * @throws IllegalArgumentException если цена отрицательная
     */
    public void setPrice(double price) {
        if(price < 0.0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной!");
        }
        this.price = price;
    }

    /**
     * Возвращает вес товара.
     *
     * @return вес товара
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Устанавливает вес товара.
     *
     * @param weight вес товара
     * @throws IllegalArgumentException если вес отрицательный
     */
    public void setWeight(double weight) {
        if(weight < 0.0) {
            throw new IllegalArgumentException("Вес не может быть отрицательным!");
        }
        this.weight = weight;
    }

    /**
     * Возвращает название товара.
     *
     * @return название товара
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название товара.
     *
     * @param name название товара
     * @throws IllegalArgumentException если название пустое
     */
    public void setName(String name) {
        if(name.isBlank()) {
            throw new IllegalArgumentException("Название не может быть пустым!");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
