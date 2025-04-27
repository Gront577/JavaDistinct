package example.Model;

/**
 * Класс, представляющий транзакцию по счету.
 * Содержит информацию о типе транзакции и сумме.
 */
public class Transaction {
    private String type;
    private double amount;

    /**
     * Создает новую транзакцию.
     *
     * @param type   тип транзакции (например, "DEPOSIT" или "WITHDRAW")
     * @param amount сумма транзакции
     */
    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    /**
     * Возвращает тип транзакции.
     *
     * @return тип транзакции
     */
    public String getType() {
        return type;
    }

    /**
     * Возвращает сумму транзакции.
     *
     * @return сумма транзакции
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Возвращает строковое представление транзакции.
     *
     * @return строка в формате "Тип: Сумма"
     */
    @Override
    public String toString() {
        return type + ": " + amount;
    }
}