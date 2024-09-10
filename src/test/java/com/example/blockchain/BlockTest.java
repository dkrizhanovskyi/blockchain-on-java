package com.example.blockchain;

import com.example.blockchain.blockchain.Block;
import com.example.blockchain.transactions.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for the Block class.
 * These tests validate the functionality of block creation, hashing, and mining.
 */
public class BlockTest {

    private Block block;
    private List<Transaction> transactions;

    @BeforeEach
    public void setUp() {
        // Initialize the transactions and block for testing
        transactions = new ArrayList<>();
        transactions.add(new Transaction("Alice", "Bob", 100));
        block = new Block(transactions, "0");  // Create a new block with a dummy previous hash "0"
    }

    @Test
    public void testBlockHashCalculation() {
        // Calculate the hash of the block and ensure it is not null or empty
        String calculatedHash = block.calculateHash();
        assertNotNull(calculatedHash, "Hash should not be null after calculation.");
        assertFalse(calculatedHash.isEmpty(), "Hash should not be empty after calculation.");
    }

    @Test
    public void testBlockIntegrity() {
        // Ensure the block's initial hash matches the hash after recalculating it
        String initialHash = block.getHash();
        String recalculatedHash = block.calculateHash();
        assertEquals(initialHash, recalculatedHash, "Block's hash should remain consistent.");
    }

    @Test
    public void testBlockMining() {
        Block block = new Block(transactions, "0"); // Create a block with a previous hash "0"
        int difficulty = 3;  // Expecting 3 leading zeros
        block.mineBlock(difficulty);

        // Debugging: print the mined block's hash and difficulty
        System.out.println("Expected difficulty: " + difficulty);
        System.out.println("Mined block hash: " + block.getHash());

        // Check if the hash starts with the expected number of leading zeros
        assertTrue(block.getHash().startsWith(new String(new char[difficulty]).replace('\0', '0')),
                "Block's hash should start with " + difficulty + " leading zeros.");
    }

    @Test
    public void testBlockWithMultipleTransactions() {
        // Add more transactions to the block and verify the hash is recalculated correctly
        transactions.add(new Transaction("Charlie", "Dave", 50));
        transactions.add(new Transaction("Eve", "Frank", 25));
        
        Block newBlock = new Block(transactions, block.getHash());
        String newBlockHash = newBlock.calculateHash();
        assertNotNull(newBlockHash, "New block hash should be calculated.");
    }

    @Test
    public void testBlockHashChangesAfterMining() {
        // Mine the block with difficulty 3, and ensure the hash changes after mining
        String initialHash = block.getHash();
        block.mineBlock(3);
        String minedHash = block.getHash();
        assertNotEquals(initialHash, minedHash, "Block's hash should change after mining.");
    }

    @Test
    public void testBlockPreviousHash() {
        // Ensure the block contains the correct previousHash value
        Block newBlock = new Block(transactions, block.getHash());
        assertEquals(block.getHash(), newBlock.getPreviousHash(), "New block should reference the previous block's hash.");
    }
}
