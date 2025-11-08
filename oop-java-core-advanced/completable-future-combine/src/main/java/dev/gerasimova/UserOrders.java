package dev.gerasimova;

import java.util.List;
/**
 * Представляет заказы пользователя.
 * Содержит список заказов.
 */
class UserOrders {
    private final List<String> orders;

    public UserOrders(List<String> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return String.format("Orders{%s}", orders);
    }
}
