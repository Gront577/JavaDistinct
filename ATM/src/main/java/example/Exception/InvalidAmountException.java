package example.Exception;

/**
 * Исключение, выбрасываемое при попытке выполнить операцию с недопустимой суммой.
 */
public class InvalidAmountException extends Exception {
    /**
     * Создает новое исключение с указанным сообщением.
     *
     * @param message сообщение об ошибке
     */
    public InvalidAmountException(String message) {
        super(message);
    }
}