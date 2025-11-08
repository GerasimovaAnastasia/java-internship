package dev.gerasimova;

import dev.gerasimova.model.EmailSender;
import dev.gerasimova.model.PushSender;
import dev.gerasimova.model.SmsSender;
import dev.gerasimova.service.ServiceSender;
import java.util.List;

/**
 * Тестовый класс для демонстрации полиморфизма через интерфейс.
 * Показывает работу полиморфизма через список интерфейса NotificationSender,
 * содержащий объекты разных реализаций.
 *
 *
 * @see EmailSender
 * @see SmsSender
 * @see PushSender
 * @see ServiceSender
 */
public final class InheritancePolymorphismTest {

    /**
     * Приватный конструктор.
     */
    private InheritancePolymorphismTest() {
        throw new UnsupportedOperationException("InheritancePolymorphismTest class");
    }

    /**
     * Запускает все тесты для задачи по полиморфизму.
     *
     * @see #shouldDemonstrateRuntimePolymorphism()
     */
    public static void runInheritancePolymorphismTests() {

        String[] descriptions = {
                "Демонстрация работы полиморфизма"
        };

        VoidTestMethod[] tests = {
                InheritancePolymorphismTest::shouldDemonstrateRuntimePolymorphism
        };

        TestRunnerUtil.runVoidTestSuite("ЗАДАЧА 3: Наследование и полиморфизм", descriptions, tests);
    }
    /**
     * Демонстрирует полиморфизм через список интерфейса NotificationSender.
     * Создает список типа NotificationSender, содержащий разные реализации,
     * и вызывает метод send() для каждого объекта. В runtime определяется,
     * какая именно реализация метода должна быть вызвана.
     *
     * @see ServiceSender#messageHandling(List)
     * @see EmailSender#send(String)
     * @see PushSender#send(String)
     */
    public static void shouldDemonstrateRuntimePolymorphism() {

        ServiceSender.messageHandling(List.of(new EmailSender(), new PushSender()));
    }
}
