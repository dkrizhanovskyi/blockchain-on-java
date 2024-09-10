package com.example.blockchain.blockchain;

import java.util.Date;
import java.util.List;
import com.example.blockchain.cryptography.StringUtil;
import com.example.blockchain.transactions.Transaction;

/**
 * The Block class represents a single block in the blockchain.
 * Each block stores transactions, a timestamp, and a reference to the previous block's hash.
 * This class also contains functionality for calculating and validating the block's hash.
 */
public class Block {

    // Hash of the current block
    private String hash;

    // Hash of the previous block (linking to the blockchain)
    private final String previousHash;

    // List of transactions included in the block
    private final List<Transaction> transactions;

    // Timestamp when the block was created
    private final long timeStamp;

    // A nonce used for mining purposes (incremented to find a valid hash)
    private int nonce;

    /**
     * Constructor for the Block class.
     * Initializes the block with a list of transactions and the previous block's hash.
     *
     * @param transactions  List of transactions to be included in this block
     * @param previousHash  The hash of the previous block in the chain
     */
    public Block(List<Transaction> transactions, String previousHash) {
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();  // Calculate the initial hash
    }

    /**
     * Calculates the hash of the block by concatenating the previousHash, timeStamp, nonce, and transactions.
     * Uses the SHA-256 algorithm for secure hashing.
     *
     * @return The calculated hash as a String.
     */
    public String calculateHash() {
        String dataToHash = previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + transactions.toString();
        return StringUtil.applySha256(dataToHash);
    }

    /**
     * Performs proof-of-work mining to find a hash that meets the required difficulty level.
     * Increments the nonce until a valid hash is found.
     *
     * @param difficulty  The difficulty level (number of leading zeros in the hash)
     */
    public void mineBlock(int difficulty) {
        // Create a string with 'difficulty' number of zeros (target)
        String target = new String(new char[difficulty]).replace('\0', '0');
    
        // Increment nonce until the hash meets the difficulty target
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();  // Recalculate hash with the incremented nonce
        }
        System.out.println("Block mined! Hash: " + hash);
    }    

    /**
     * Retrieves the hash of the block.
     *
     * @return The current hash of the block.
     */
    public String getHash() {
        return hash;
    }

    /**
     * Retrieves the hash of the previous block.
     *
     * @return The previous block's hash.
     */
    public String getPreviousHash() {
        return previousHash;
    }

    /**
     * Retrieves the list of transactions in the block.
     *
     * @return The list of transactions.
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "Block{" +
                "hash='" + hash + '\'' +
                ", previousHash='" + previousHash + '\'' +
                ", transactions=" + transactions +
                ", timeStamp=" + timeStamp +
                ", nonce=" + nonce +
                '}';
    }
}
