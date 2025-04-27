package example.Service;

import example.Model.Account;
import example.Exception.InsufficientFundsException;
import example.Exception.InvalidAmountException;

/**
 * Сервис для работы с банковским счетом.
 * Предоставляет методы для пополнения, снятия и проверки баланса.
 */
public class AccountService {
    private Account account;

    /**
     * Создает новый сервис для работы с указанным счетом.
     *
     * @param account счет, с которым будет работать сервис
     */
    public AccountService(Account account) {
        this.account = account;
    }

    /**
     * Пополняет счет на указанную сумму.
     *
     * @param amount сумма для пополнения
     * @throws InvalidAmountException если сумма меньше или равна 0
     */
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }
        account.deposit(amount);
    }

    /**
     * Снимает указанную сумму со счета.
     *
     * @param amount сумма для снятия
     * @throws InsufficientFundsException если на счете недостаточно средств
     * @throws InvalidAmountException     если сумма меньше или равна 0
     */
    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }
        account.withdraw(amount);
    }

    /**
     * Возвращает текущий баланс счета.
     *
     * @return текущий баланс
     */
    public double GetBalance() {
        return account.getBalance();
    }
}