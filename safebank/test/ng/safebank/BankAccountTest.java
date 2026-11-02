package ng.safebank;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;
import exception.WithdrawalLimitExceededException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void shouldCreateAccountWithValidDetails() {

        BankAccount account = new BankAccount(
                "Israel",
                new BigDecimal("50000")
        );

        assertEquals("Israel", account.getAccountHolder());

        assertEquals(
                new BigDecimal("50000"),
                account.getBalance()
        );
    }

    @Test
    void shouldAllowZeroOpeningBalance() {

        BankAccount account = new BankAccount(
                "Israel",
                BigDecimal.ZERO
        );

        assertEquals(BigDecimal.ZERO, account.getBalance());
    }
    @Test
    void shouldRejectNegativeOpeningBalance() {

        assertThrows(IllegalArgumentException.class, () ->
                new BankAccount(
                        "Israel",
                        new BigDecimal("-5000")
                )
        );
    }

    @Test
    void shouldRejectBlankAccountHolderName() {

        assertThrows(IllegalArgumentException.class, () ->
                new BankAccount(
                        " ",
                        new BigDecimal("50000")
                )
        );
    }

    @Test
    void shouldRecordOpeningBalanceInTransactionHistory() {

        BankAccount account = new BankAccount(
                "Israel",
                new BigDecimal("50000")
        );

        List<Transaction> history = account.getTransactionHistory();

        assertEquals(1, history.size());

        Transaction transaction = history.get(0);

        assertEquals(TransactionType.OPENING_BALANCE, transaction.getType());

        assertEquals(
                new BigDecimal("50000"),
                transaction.getAmount()
        );

        assertEquals(
                new BigDecimal("50000"),
                transaction.getBalanceAfterTransaction()
        );
    }

    @Test
    void shouldDepositMoneySuccessfully() throws InvalidAmountException {

        BankAccount account = new BankAccount(
                "Israel",
                new BigDecimal("10000")
        );

        account.deposit(new BigDecimal("5000"));

        assertEquals(
                new BigDecimal("15000"),
                account.getBalance()
        );
    }

    @Test
    void shouldRejectZeroDeposit() {

        BankAccount account = new BankAccount(
                "Israel",
                new BigDecimal("10000")
        );

        assertThrows(InvalidAmountException.class, () ->
                account.deposit(BigDecimal.ZERO)
        );
    }

    @Test
    void shouldRejectNegativeDeposit() {

        BankAccount account = new BankAccount(
                "Israel",
                new BigDecimal("10000")
        );

        assertThrows(InvalidAmountException.class, () ->
                account.deposit(new BigDecimal("-500"))
        );
    }

    @Test
    void shouldNotChangeBalanceOrHistoryWhenDepositFails() {

        BankAccount account = new BankAccount(
                "Israel",
                new BigDecimal("10000")
        );

        int initialHistorySize = account.getTransactionHistory().size();

        assertThrows(InvalidAmountException.class, () ->
                account.deposit(new BigDecimal("-500"))
        );

        assertEquals(
                new BigDecimal("10000"),
                account.getBalance()
        );

        assertEquals(
                initialHistorySize,
                account.getTransactionHistory().size()
        );
    }

    @Test
    void shouldWithdrawMoneySuccessfully()
            throws InvalidAmountException,
            InsufficientFundsException,
            WithdrawalLimitExceededException {

        BankAccount account = new BankAccount(
                "Israel",
                new BigDecimal("20000")
        );

        account.withdraw(new BigDecimal("5000"));

        assertEquals(
                new BigDecimal("15000"),
                account.getBalance()
        );
    }

    @Test
    void shouldRejectZeroWithdrawal() {

        BankAccount account = new BankAccount(
                "Israel",
                new BigDecimal("20000")
        );

        assertThrows(InvalidAmountException.class, () ->
                account.withdraw(BigDecimal.ZERO)
        );
    }

    @Test
    void shouldRejectNegativeWithdrawal() {

        BankAccount account = new BankAccount(
                "Israel",
                new BigDecimal("20000")
        );

        assertThrows(InvalidAmountException.class, () ->
                account.withdraw(new BigDecimal("-500"))
        );
    }

    @Test
    void shouldRejectWithdrawalExceedingBalance() {

        BankAccount account = new BankAccount(
                "Israel",
                new BigDecimal("20000")
        );

        assertThrows(InsufficientFundsException.class, () ->
                account.withdraw(new BigDecimal("25000"))
        );
    }

    @Test
    void shouldRejectWithdrawalExceedingLimit() {

        BankAccount account = new BankAccount(
                "Israel",
                new BigDecimal("200000")
        );

        assertThrows(WithdrawalLimitExceededException.class, () ->
                account.withdraw(new BigDecimal("120000"))
        );
    }

    @Test
    void shouldNotChangeBalanceOrHistoryWhenWithdrawalFails() {

        BankAccount account = new BankAccount(
                "Israel",
                new BigDecimal("20000")
        );

        int initialHistorySize = account.getTransactionHistory().size();

        assertThrows(InsufficientFundsException.class, () ->
                account.withdraw(new BigDecimal("25000"))
        );

        assertEquals(
                new BigDecimal("20000"),
                account.getBalance()
        );

        assertEquals(
                initialHistorySize,
                account.getTransactionHistory().size()
        );
    }

    @Test
    void shouldRecordAllSuccessfulTransactionsInOrder()
            throws InvalidAmountException,
            InsufficientFundsException,
            WithdrawalLimitExceededException {

        BankAccount account = new BankAccount(
                "Israel",
                new BigDecimal("50000")
        );

        account.deposit(new BigDecimal("10000"));
        account.withdraw(new BigDecimal("5000"));

        List<Transaction> history = account.getTransactionHistory();

        assertEquals(3, history.size());

        assertEquals(
                TransactionType.OPENING_BALANCE,
                history.get(0).getType()
        );

        assertEquals(
                TransactionType.DEPOSIT,
                history.get(1).getType()
        );

        assertEquals(
                TransactionType.WITHDRAWAL,
                history.get(2).getType()
        );

        assertEquals(
                new BigDecimal("55000"),
                history.get(2).getBalanceAfterTransaction()
        );
    }

}