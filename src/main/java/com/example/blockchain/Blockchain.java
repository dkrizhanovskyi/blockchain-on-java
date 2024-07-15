package com.example.blockchain;

import java.util.ArrayList;

public class Blockchain {
    // Static list to store the blockchain
    public static ArrayList<Block> blockchain = new ArrayList<>();
    // Difficulty level for mining
    public static int difficulty = 5;

    public static void main(String[] args) {
        // Create the genesis block with initial transactions
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("Alice", "Bob", 50));
        Block genesisBlock = new Block(transactions, "0");  // The previous hash for the genesis block is "0"
        genesisBlock.mineBlock(difficulty);  // Mine the genesis block
        blockchain.add(genesisBlock);  // Add the genesis block to the blockchain

        // Create the second block with new transactions
        transactions = new ArrayList<>();
        transactions.add(new Transaction("Bob", "Charlie", 30));
        Block secondBlock = new Block(transactions, blockchain.get(blockchain.size() - 1).hash);  // Previous hash is the hash of the last block in the blockchain
        secondBlock.mineBlock(difficulty);  // Mine the second block
        blockchain.add(secondBlock);  // Add the second block to the blockchain

        // Create the third block with new transactions
        transactions = new ArrayList<>();
        transactions.add(new Transaction("Charlie", "Dave", 20));
        Block thirdBlock = new Block(transactions, blockchain.get(blockchain.size() - 1).hash);  // Previous hash is the hash of the last block in the blockchain
        thirdBlock.mineBlock(difficulty);  // Mine the third block
        blockchain.add(thirdBlock);  // Add the third block to the blockchain

        // Print out all the blocks in the blockchain
        for (Block block : blockchain) {
            System.out.println(block);
        }

        // Check if the blockchain is valid and print the result
        System.out.println("Is blockchain valid? " + isChainValid());
    }

    // Method to validate the blockchain
    public static boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        // Iterate through the blockchain to check hashes
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            // Check if the current block's hash is valid
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }

            // Check if the previous block's hash matches the current block's previous hash
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;  // Return true if all blocks are valid
    }
}

