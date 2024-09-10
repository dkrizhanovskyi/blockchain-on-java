package com.example.blockchain.transactions;

/**
 * The Transaction class represents a transaction between two parties on the blockchain.
 * A transaction includes the sender's address, the recipient's address, and the transaction amount.
 * Future enhancements can include digital signatures for authentication and verification.
 */
public class Transaction {

    private final String sender;
    private final String recipient;
    private final double amount;

    /**
     * Constructor for the Transaction class.
     * Initializes a transaction with the sender, recipient, and amount.
     *
     * @param sender    The address of the sender
     * @param recipient The address of the recipient
     * @param amount    The amount to be transferred in the transaction
     */
    public Transaction(String sender, String recipient, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }

    /**
     * Retrieves the sender's address.
     *
     * @return The sender's address or public key.
     */
    public String getSender() {
        return sender;
    }

    /**
     * Retrieves the recipient's address.
     *
     * @return The recipient's address or public key.
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Retrieves the transaction amount.
     *
     * @return The amount to be transferred in the transaction.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Converts the transaction to a string representation.
     * This is useful for hashing the transaction data in the Block class.
     *
     * @return A string representing the transaction data.
     */
    @Override
    public String toString() {
        return "Transaction{" +
                "sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", amount=" + amount +
                '}';
    }
}
