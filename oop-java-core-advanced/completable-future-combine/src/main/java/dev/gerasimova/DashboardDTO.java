package dev.gerasimova;
/**
 * Класс создан для объединенных данных пользователя.
 * Содержит профиль пользователя и его заказы для отображения в интерфейсе.
 */
class DashboardDTO {
    private final UserProfile profile;
    private final UserOrders orders;

    /**
     * Создает объект с объединенными данными пользователя (заказами и профилем).
     * Используется для общего вывода.
     * @param profile - профиль пользователя.
     * @param orders - список заказов пользователя.
     */

    public DashboardDTO(UserProfile profile, UserOrders orders) {
        this.profile = profile;
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "DashboardDTO{" +
                "profile=" + profile +
                ", orders=" + orders +
                '}';
    }
}
