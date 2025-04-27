package example.Exception;

/**
 * Исключение, выбрасываемое при попытке снять сумму, превышающую баланс счета.
 */
public class InsufficientFundsException extends Exception {
    /**
     * Создает новое исключение с указанным сообщением.
     *
     * @param message сообщение об ошибке
     */
    public InsufficientFundsException(String message) {
        super(message);
    }
}