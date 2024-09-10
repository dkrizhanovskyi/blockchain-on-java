package com.example.blockchain.blockchain;

import java.util.ArrayList;
import java.util.List;
import com.example.blockchain.transactions.Transaction;

/**
 * The Blockchain class manages the entire blockchain.
 * It includes functionality for adding blocks, validating the chain, and ensuring the integrity of the blockchain.
 */
public class Blockchain {

    // The blockchain is represented as a list of blocks
    private final List<Block> blockchain = new ArrayList<>();

    // Difficulty level for mining (the number of leading zeros in the hash)
    private final int difficulty;

    /**
     * Constructor for the Blockchain class.
     * Initializes the blockchain with a set difficulty level and adds the genesis block.
     *
     * @param difficulty  The mining difficulty level (how hard it is to mine a block)
     */
    public Blockchain(int difficulty) {
        this.difficulty = difficulty;

        // Create the genesis block (the first block in the blockchain)
        List<Transaction> genesisTransactions = new ArrayList<>();
        Block genesisBlock = new Block(genesisTransactions, "0");  // Previous hash is "0" for the genesis block
        genesisBlock.mineBlock(difficulty);  // Mine the genesis block with the specified difficulty
        blockchain.add(genesisBlock);  // Add the genesis block to the blockchain
    }

    /**
     * Adds a new block to the blockchain.
     * This method validates and mines the new block before adding it to the chain.
     *
     * @param newBlock  The new block to be added
     */
    public void addBlock(Block newBlock) {
        // Mine the block with the specified difficulty
        newBlock.mineBlock(difficulty);
        // Add the block to the blockchain
        blockchain.add(newBlock);
    }

    /**
     * Validates the blockchain by checking the hashes of all the blocks.
     * This ensures the integrity of the chain and detects any tampering.
     *
     * @return True if the blockchain is valid, False otherwise.
     */
    public boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
    
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
    
            // Recalculate and compare hashes
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("Current block hash is invalid.");
                return false;
            }
    
            // Check if the current block's previousHash matches the previous block's hash
            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                System.out.println("Previous block hash is invalid.");
                return false;
            }
        }
        return true;
    }             

    /**
     * Retrieves the blockchain.
     *
     * @return The list of blocks in the blockchain.
     */
    public List<Block> getBlockchain() {
        return blockchain;
    }

    /**
     * Displays the details of the entire blockchain.
     */
    public void displayBlockchain() {
        for (Block block : blockchain) {
            System.out.println(block.toString());
        }
    }
}
