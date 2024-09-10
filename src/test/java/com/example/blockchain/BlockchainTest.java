package com.example.blockchain;

import com.example.blockchain.blockchain.Block;
import com.example.blockchain.blockchain.Blockchain;
import com.example.blockchain.transactions.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for the Blockchain class.
 * These tests validate the functionality of adding blocks, transactions, and ensuring the blockchain's integrity.
 */
public class BlockchainTest {

    private Blockchain blockchain;

    @BeforeEach
    public void setUp() {
        // Initialize the blockchain with a difficulty level
        blockchain = new Blockchain(4);  // Example difficulty set to 4
    }

    @Test
    public void testAddGenesisBlock() {
        // The genesis block is automatically created in the Blockchain constructor
        List<Block> blocks = blockchain.getBlockchain();
        assertEquals(1, blocks.size(), "Blockchain should have one block (the genesis block).");

        Block genesisBlock = blocks.get(0);
        assertEquals("0", genesisBlock.getPreviousHash(), "Genesis block should have previous hash of '0'.");
    }

    @Test
    public void testAddBlock() {
        // Create and add a block with a transaction
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("Alice", "Bob", 50));

        Block newBlock = new Block(transactions, blockchain.getBlockchain().get(0).getHash());
        blockchain.addBlock(newBlock);

        List<Block> blocks = blockchain.getBlockchain();
        assertEquals(2, blocks.size(), "Blockchain should have two blocks after adding a new block.");

        Block latestBlock = blocks.get(1);
        assertEquals(newBlock.getHash(), latestBlock.getHash(), "Latest block hash should match the added block.");
    }

    @Test
    public void testBlockchainValidity() {
        // Create a few blocks and check the blockchain validity
        List<Transaction> transactions1 = new ArrayList<>();
        transactions1.add(new Transaction("Bob", "Charlie", 30));
        Block block1 = new Block(transactions1, blockchain.getBlockchain().get(0).getHash());
        blockchain.addBlock(block1);

        List<Transaction> transactions2 = new ArrayList<>();
        transactions2.add(new Transaction("Charlie", "Dave", 20));
        Block block2 = new Block(transactions2, blockchain.getBlockchain().get(1).getHash());
        blockchain.addBlock(block2);

        // Verify that the blockchain is valid after adding these blocks
        assertTrue(blockchain.isChainValid(), "Blockchain should be valid after adding new blocks.");
    }

    @Test
    public void testBlockMining() {
        // Initialize transactions
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("Alice", "Bob", 50.0));
        
        // Create a block with a previous hash "0"
        Block block = new Block(transactions, "0"); 
        int difficulty = 3;  // Expecting 3 leading zeros
        block.mineBlock(difficulty);

        // Debugging: print the mined block's hash and difficulty
        System.out.println("Expected difficulty: " + difficulty);
        System.out.println("Mined block hash: " + block.getHash());

        // Check if the hash starts with the expected number of leading zeros
        assertTrue(block.getHash().startsWith(new String(new char[difficulty]).replace('\0', '0')),
                "Block's hash should start with " + difficulty + " leading zeros.");
    }

}
