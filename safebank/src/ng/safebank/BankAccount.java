package ng.safebank;

import exception.InsufficientFundsException;
import exception.InvalidAmountException;
import exception.WithdrawalLimitExceededException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String accountHolder;
    private BigDecimal balance;
    private List<Transaction> transactionHistory;

    public BankAccount(String accountHolder, BigDecimal openingBalance) {

        if (accountHolder == null || accountHolder.isBlank()) {
            throw new IllegalArgumentException("Account holder name cannot be blank.");
        }

        if (openingBalance == null || openingBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative.");
        }
        this.accountHolder = accountHolder;
        this.balance = openingBalance;
        this.transactionHistory = new ArrayList<>();

        Transaction openingTransaction = new Transaction(TransactionType.OPENING_BALANCE, openingBalance, this.balance);
        this.transactionHistory.add(openingTransaction);

    }

    public void deposit(BigDecimal amount) throws InvalidAmountException {
        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidAmountException("Amount cannot be negative.");
        }

        BigDecimal newBalance = this.balance.add(amount);
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, amount, newBalance);
        balance = newBalance;
        transactionHistory.add(transaction);
    }


    public void withdraw(BigDecimal amount)
            throws InvalidAmountException,
            InsufficientFundsException,
            WithdrawalLimitExceededException {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero."
            );
        }

        if (amount.compareTo(new BigDecimal("100000")) > 0) {
            throw new WithdrawalLimitExceededException(
                    "Withdrawal cannot exceed ₦100,000."
            );
        }

        if (amount.compareTo(balance) > 0) {
            throw new InsufficientFundsException(
                    "Insufficient funds."
            );
        }

        BigDecimal newBalance = balance.subtract(amount);

        Transaction transaction = new Transaction(
                TransactionType.WITHDRAWAL,
                amount,
                newBalance
        );

        balance = newBalance;
        transactionHistory.add(transaction);
    }


    public List<Transaction> getTransactionHistory() {
        return List.copyOf(transactionHistory);
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
