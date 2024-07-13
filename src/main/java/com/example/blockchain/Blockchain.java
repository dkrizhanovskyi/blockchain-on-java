package com.example.blockchain;

import java.util.ArrayList;

public class Blockchain {
    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;

    public static void main(String[] args) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("Alice", "Bob", 50));
        Block genesisBlock = new Block(transactions, "0");
        genesisBlock.mineBlock(difficulty);
        blockchain.add(genesisBlock);

        transactions = new ArrayList<>();
        transactions.add(new Transaction("Bob", "Charlie", 30));
        Block secondBlock = new Block(transactions, blockchain.get(blockchain.size() - 1).hash);
        secondBlock.mineBlock(difficulty);
        blockchain.add(secondBlock);

        transactions = new ArrayList<>();
        transactions.add(new Transaction("Charlie", "Dave", 20));
        Block thirdBlock = new Block(transactions, blockchain.get(blockchain.size() - 1).hash);
        thirdBlock.mineBlock(difficulty);
        blockchain.add(thirdBlock);

        for (Block block : blockchain) {
            System.out.println(block);
        }

        System.out.println("Is blockchain valid? " + isChainValid());
    }

    public static boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }

            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }
}
