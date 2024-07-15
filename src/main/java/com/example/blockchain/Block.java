package com.example.blockchain;

import java.util.Date;
import java.util.List;

public class Block {
    // Public fields for the block's hash and the previous block's hash
    public String hash;
    public String previousHash;
    
    // Private fields for the list of transactions, timestamp, and nonce
    private List<Transaction> transactions;
    private long timeStamp;
    private int nonce;

    // Constructor for the Block class
    public Block(List<Transaction> transactions, String previousHash) {
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();  // Set the current time as the timestamp
        this.hash = calculateHash();  // Calculate the block's hash
    }

    // Method to calculate the hash of the block
    public String calculateHash() {
        // Concatenate previousHash, timeStamp, nonce, and transactions to form the input for the hash function
        String input = previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + transactions.toString();
        return StringUtil.applySha256(input);  // Return the SHA-256 hash of the input
    }

    // Method to mine the block
    public void mineBlock(int difficulty) {
        // Create a string with 'difficulty' number of zeros
        String target = new String(new char[difficulty]).replace('\0', '0');
        // Increment nonce until the hash meets the difficulty target
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();  // Recalculate the hash with the new nonce
        }
        System.out.println("Block Mined!!! : " + hash);  // Print a message when the block is mined
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

