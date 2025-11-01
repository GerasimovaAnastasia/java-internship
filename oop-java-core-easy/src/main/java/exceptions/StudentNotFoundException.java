package exceptions;
/**
 * Исключение, выбрасываемое при попытке найти студента по не существующему ID.
 * Наследует от {@link RuntimeException}, что делает его неконтролируемым исключением.
 * Используется в сервисном слое для обработки бизнес-логики связанной со студентами.
 *
 * @see RuntimeException
 */
public class StudentNotFoundException extends RuntimeException {

    /**
     * Создает новое исключение с указанием ID ненайденного студента.
     *
     * @param studentId ID студента, который не был найден
     * @throws IllegalArgumentException если studentId равен null
     */
    public StudentNotFoundException(String studentId) {
        super("Студент с ID '" + studentId + "' не найден");
    }

    /**
     * Создает новое исключение с указанием ID ненайденного студента и причиной.
     *
     * @param studentId ID студента, который не был найден
     * @param cause исключение, которое стало причиной этого исключения
     * @throws IllegalArgumentException если studentId равен null
     */
    public StudentNotFoundException(String studentId, Throwable cause) {
        super("Студент с ID '" + studentId + "' не найден", cause);
    }

}
