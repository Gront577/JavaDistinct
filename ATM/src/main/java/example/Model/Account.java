package example.Model;

import example.Exception.InsufficientFundsException;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий банковский счет.
 * Содержит информацию о балансе и истории транзакций.
 */
public class Account {
    private String accountId;
    private double balance;
    private List<Transaction> transactions;

    /**
     * Создает новый счет с указанным идентификатором.
     *
     * @param accountId уникальный идентификатор счета
     */
    public Account(String accountId) {
        this.accountId = accountId;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    /**
     * Возвращает идентификатор счета.
     *
     * @return идентификатор счета
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Возвращает текущий баланс счета.
     *
     * @return текущий баланс
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Возвращает список всех транзакций по счету.
     *
     * @return список транзакций
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Пополняет счет на указанную сумму.
     *
     * @param amount сумма для пополнения
     */
    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("DEPOSIT", amount));
    }

    /**
     * Снимает указанную сумму со счета.
     *
     * @param amount сумма для снятия
     * @throws InsufficientFundsException если на счете недостаточно средств
     */
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        balance -= amount;
        transactions.add(new Transaction("WITHDRAW", amount));
    }
}