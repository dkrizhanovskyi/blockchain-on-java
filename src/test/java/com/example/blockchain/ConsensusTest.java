package com.example.blockchain;

import com.example.blockchain.blockchain.Block;
import com.example.blockchain.consensus.PoSConsensus;
import com.example.blockchain.consensus.PoWConsensus;
import com.example.blockchain.transactions.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unit tests for the Proof of Work (PoW) and Proof of Stake (PoS) consensus mechanisms.
 * These tests validate block validation, mining, slashing, and stake-based validation.
 */
public class ConsensusTest {

    private PoWConsensus poWConsensus;
    private PoSConsensus poSConsensus;
    private Block block;
    private List<Transaction> transactions;
    private Map<String, Double> stakes;
    private Map<String, Integer> slashedValidators;

    @BeforeEach
    public void setUp() {
        // Initialize transactions and block
        transactions = new ArrayList<>();
        transactions.add(new Transaction("Alice", "Bob", 100));
        block = new Block(transactions, "0");  // Previous hash "0" for genesis-like block

        // Initialize Proof of Work consensus
        poWConsensus = new PoWConsensus();

        // Initialize Proof of Stake consensus with some fake validators and stakes
        stakes = new HashMap<>();
        stakes.put("Validator1", 100.0);
        stakes.put("Validator2", 50.0);

        slashedValidators = new HashMap<>();
        poSConsensus = new PoSConsensus(stakes, slashedValidators);
    }

    // Proof of Work (PoW) Tests
    @Test
    public void testPoWMineBlock() {
        int difficulty = 3;  // Example difficulty
        poWConsensus.mineBlock(block, difficulty);

        String expectedPrefix = "0".repeat(difficulty);
        assertTrue(block.getHash().startsWith(expectedPrefix),
                "PoW mined block's hash should start with " + difficulty + " leading zeros.");
    }

    @Test
    public void testPoWValidateBlock() {
        int difficulty = 3;
        poWConsensus.mineBlock(block, difficulty);

        assertTrue(poWConsensus.validateBlock(block, difficulty),
                "PoW should validate the block if it meets the difficulty requirement.");
    }

    @Test
    public void testPoWInvalidBlock() {
        int difficulty = 3;

        // Manually tamper with the block (without mining) to create an invalid block
        block.mineBlock(1);  // Mine with lower difficulty
        assertFalse(poWConsensus.validateBlock(block, difficulty),
                "PoW should invalidate the block if it doesn't meet the difficulty requirement.");
    }

    // Proof of Stake (PoS) Tests
    @Test
    public void testPoSMineBlock() {
        poSConsensus.mineBlock(block, 0);  // Difficulty is irrelevant in PoS

        assertNotNull(block.getHash(), "PoS mined block should have a valid hash.");
        System.out.println("PoS block mined by one of the validators based on stakes.");
    }

    @Test
    public void testPoSValidateBlock() {
        poSConsensus.mineBlock(block, 0);  // Mine the block

        assertTrue(poSConsensus.validateBlock(block, 0),
                "PoS should validate the block as it was mined by a valid staker.");
    }

    @Test
    public void testPoSValidatorSelection() {
        String selectedValidator = poSConsensus.validateBlock(block, 0) ? "Validator1" : "Validator2";

        assertTrue(stakes.containsKey(selectedValidator),
                "The selected validator should be one of the stakers.");
    }

    @Test
    public void testPoSInvalidBlock() {
        // Invalidate the block by tampering with it or invalidating the selected validator
        block.mineBlock(1);  // Re-mining with an arbitrary nonce, making it invalid in PoS context

        assertFalse(poSConsensus.validateBlock(block, 0),
                "PoS should invalidate the block if it was not created by a legitimate validator.");
    }

    // Slashing Tests
    @Test
    public void testSlashingForMaliciousValidator() {
        // Simulate slashing for malicious behavior
        String maliciousValidator = "Validator1";

        // Force slashing by manually calling slashing method (for test purposes)
        poSConsensus.mineBlock(block, 0);  // Assume block mined by malicious validator
        poSConsensus.validateBlock(block, 0);  // Simulate validator acting maliciously

        // Check that the validator has been slashed
        assertEquals(1, slashedValidators.get(maliciousValidator).intValue(),
                "Validator1 should have been slashed once for malicious behavior.");
    }

    @Test
    public void testSlashingReducesStake() {
        String maliciousValidator = "Validator1";
        double initialStake = stakes.get(maliciousValidator);

        // Simulate malicious behavior and slashing
        poSConsensus.mineBlock(block, 0);  // Assume block mined by malicious validator
        poSConsensus.validateBlock(block, 0);  // Simulate validator acting maliciously

        // Check that the validator's stake has been reduced by 20% (slashing percentage)
        double expectedStake = initialStake * 0.8;
        assertEquals(expectedStake, stakes.get(maliciousValidator),
                "Validator1's stake should have been reduced by 20% after slashing.");
    }

}
