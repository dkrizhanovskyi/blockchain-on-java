package com.example.blockchain.consensus;

import com.example.blockchain.blockchain.Block;

/**
 * PoWConsensus implements the Proof of Work (PoW) consensus mechanism.
 * In PoW, miners must perform computational work (solving cryptographic puzzles) to add a new block to the blockchain.
 */
public class PoWConsensus implements Consensus {

    /**
     * Mines a block by performing Proof of Work.
     * This involves finding a valid nonce such that the block's hash has the required number of leading zeros, determined by the difficulty.
     *
     * @param block      The block to be mined
     * @param difficulty The difficulty level (number of leading zeros required in the hash)
     */
    @Override
    public void mineBlock(Block block, int difficulty) {
        System.out.println("Mining block with Proof of Work...");
        block.mineBlock(difficulty);  // Call the block's mining method, which performs the actual mining process
        System.out.println("Block successfully mined with hash: " + block.getHash());
    }

    /**
     * Validates a block according to Proof of Work rules.
     * This method checks whether the block's hash satisfies the difficulty requirement (i.e., the hash must have the required number of leading zeros).
     *
     * @param block      The block to be validated
     * @param difficulty The difficulty level to check against
     * @return True if the block is valid according to the PoW rules, false otherwise
     */
    @Override
    public boolean validateBlock(Block block, int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');  // Create a string of leading zeros (the difficulty target)
        boolean isValid = block.getHash().substring(0, difficulty).equals(target);  // Check if the block's hash meets the difficulty target
        if (!isValid) {
            System.out.println("Block validation failed: Hash does not meet the required difficulty.");
        } else {
            System.out.println("Block validation succeeded: Hash meets the required difficulty.");
        }
        return isValid;
    }
}
