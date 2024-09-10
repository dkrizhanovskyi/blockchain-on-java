package com.example.blockchain.consensus;

import com.example.blockchain.blockchain.Block;

/**
 * The Consensus interface defines the structure for consensus algorithms used in the blockchain.
 * Implementing classes will provide the logic for consensus mechanisms such as Proof of Work (PoW), Proof of Stake (PoS), etc.
 */
public interface Consensus {

    /**
     * Validates whether a block satisfies the conditions required by the consensus mechanism.
     * This method ensures that the block follows the specific consensus rules (e.g., sufficient proof of work).
     *
     * @param block The block to be validated
     * @param difficulty The difficulty level (typically for PoW)
     * @return True if the block satisfies the consensus rules, false otherwise
     */
    boolean validateBlock(Block block, int difficulty);

    /**
     * Mines a new block according to the rules of the consensus mechanism.
     * This could involve finding a valid nonce (Proof of Work) or staking a certain amount (Proof of Stake).
     *
     * @param block The block to be mined
     * @param difficulty The difficulty level for mining (typically for PoW)
     */
    void mineBlock(Block block, int difficulty);
}
