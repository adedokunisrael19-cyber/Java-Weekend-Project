package ng.safebank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction {
    private final TransactionType type;
    private final BigDecimal amount;
    private final LocalDateTime timestamp;
    private final BigDecimal balanceAfterTransaction;

    public  Transaction(TransactionType type, BigDecimal amount, BigDecimal balanceAfterTransaction) {
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public BigDecimal getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public TransactionType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public BigDecimal getBalanceBeforeTransaction() {
        return balanceAfterTransaction;
    }
}
