package com.example.blockchain;

import com.example.blockchain.transactions.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Transaction class.
 * These tests validate the creation, integrity, and representation of transactions.
 */
public class TransactionTest {

    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        // Initialize a transaction for testing
        transaction = new Transaction("Alice", "Bob", 50.0);
    }

    @Test
    public void testTransactionCreation() {
        // Ensure the transaction is created with correct values
        assertEquals("Alice", transaction.getSender(), "Sender should be Alice.");
        assertEquals("Bob", transaction.getRecipient(), "Recipient should be Bob.");
        assertEquals(50.0, transaction.getAmount(), "Transaction amount should be 50.");
    }

    @Test
    public void testTransactionStringRepresentation() {
        // Check the toString() method for correct output format
        String expectedString = "Transaction{sender='Alice', recipient='Bob', amount=50.0}";
        assertEquals(expectedString, transaction.toString(), "Transaction string representation should match.");
    }

    @Test
    public void testTransactionAmount() {
        // Ensure that negative transaction amounts are not allowed
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Transaction("Alice", "Bob", -100.0);
        });
        assertEquals("Amount must be positive", exception.getMessage(),
                "Should throw an exception if the transaction amount is negative.");
    }

    @Test
    public void testTransactionIntegrity() {
        // Ensure that transaction details do not change
        String initialSender = transaction.getSender();
        String initialRecipient = transaction.getRecipient();
        double initialAmount = transaction.getAmount();

        // Check that the transaction details remain unchanged
        assertEquals(initialSender, transaction.getSender(), "Sender should not change.");
        assertEquals(initialRecipient, transaction.getRecipient(), "Recipient should not change.");
        assertEquals(initialAmount, transaction.getAmount(), "Transaction amount should not change.");
    }
}
