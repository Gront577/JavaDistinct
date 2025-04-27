package example.service;

import example.Model.Account;
import example.Exception.InsufficientFundsException;
import example.Exception.InvalidAmountException;
import example.Service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        Account account = new Account("12345");
        accountService = new AccountService(account);
    }

    @Test
    public void testDeposit() throws InvalidAmountException {
        accountService.deposit(100.0);
        assertEquals(100.0, accountService.GetBalance());
    }

    @Test
    public void testWithdraw() throws InsufficientFundsException, InvalidAmountException {
        accountService.deposit(100.0);
        accountService.withdraw(50.0);
        assertEquals(50.0, accountService.GetBalance());
    }

    @Test
    public void testInsufficientFundsException() {
        assertThrows(InsufficientFundsException.class, () -> accountService.withdraw(50.0));
    }

    @Test
    public void testInvalidAmountException() {
        assertThrows(InvalidAmountException.class, (Executable) () -> accountService.deposit(-10.0));
    }
}