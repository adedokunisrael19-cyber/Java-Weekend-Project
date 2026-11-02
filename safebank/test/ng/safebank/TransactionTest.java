package ng.safebank;

import jdk.jfr.Category;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {


    @Test
    void shouldCreateTransactionWithCorrectDetails() {

        Transaction transaction = new Transaction(
                TransactionType.DEPOSIT,
                new BigDecimal("5000"),
                new BigDecimal("15000")
        );

        assertEquals(TransactionType.DEPOSIT, transaction.getType());

        assertEquals(
                new BigDecimal("5000"),
                transaction.getAmount()
        );

        assertEquals(
                new BigDecimal("15000"),
                transaction.getBalanceAfterTransaction()
        );

        assertNotNull(transaction.getTimestamp());
    }

}