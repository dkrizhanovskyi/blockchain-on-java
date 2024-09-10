package com.example.blockchain.consensus;

import com.example.blockchain.blockchain.Block;

import java.util.Map;
import java.util.Random;

/**
 * PoSConsensus implements the Proof of Stake (PoS) consensus mechanism.
 * In PoS, the validator is selected based on the amount of stake they hold (usually cryptocurrency).
 * Validators who act maliciously will be penalized by slashing their stake.
 */
public class PoSConsensus implements Consensus {

    // Map to represent each validator's stake (key: validator's address, value: amount of stake)
    private final Map<String, Double> stakes;

    // Map to track slashed validators (key: validator's address, value: number of offenses)
    private final Map<String, Integer> slashedValidators;

    // Percentage of stake to be slashed in case of malicious behavior
    private static final double SLASHING_PERCENTAGE = 0.2;

    /**
     * Constructor for PoSConsensus.
     * Initializes the Proof of Stake mechanism with a list of validators and their stakes.
     *
     * @param stakes A map representing each validator's stake (address -> stake amount)
     */
    public PoSConsensus(Map<String, Double> stakes, Map<String, Integer> slashedValidators) {
        this.stakes = stakes;
        this.slashedValidators = slashedValidators;
    }

    /**
     * Selects a validator based on their stake.
     * Validators with higher stakes have a higher probability of being selected.
     *
     * @return The address of the selected validator.
     */
    private String selectValidator() {
        double totalStake = stakes.values().stream().mapToDouble(Double::doubleValue).sum();
        double randomValue = new Random().nextDouble() * totalStake;
        double cumulativeStake = 0.0;

        for (Map.Entry<String, Double> entry : stakes.entrySet()) {
            cumulativeStake += entry.getValue();
            if (randomValue <= cumulativeStake) {
                return entry.getKey(); // Return the address of the selected validator
            }
        }
        return null;  // In case no validator is selected (should not happen if stakes are valid)
    }

    /**
     * Mines a new block by selecting a validator based on their stake.
     * This is different from Proof of Work (PoW) because no computationally expensive work is performed.
     *
     * @param block      The block to be mined
     * @param difficulty The difficulty level (not applicable in PoS, but provided for consistency)
     */
    @Override
    public void mineBlock(Block block, int difficulty) {
        String selectedValidator = selectValidator();

        if (selectedValidator != null) {
            System.out.println("Block mined by validator: " + selectedValidator);
            block.mineBlock(0); // No real difficulty involved in PoS, but we use a placeholder
        } else {
            System.out.println("No validator selected for this block.");
        }
    }

    /**
     * Validates the block according to the Proof of Stake rules.
     * In PoS, the main validation is to ensure the selected validator has enough stake.
     * Slashing is applied if the validator behaves maliciously (e.g., double-signing).
     *
     * @param block      The block to be validated
     * @param difficulty The difficulty level (not applicable in PoS)
     * @return True if the block is valid, false otherwise
     */
    @Override
    public boolean validateBlock(Block block, int difficulty) {
        String selectedValidator = selectValidator();

        // Simulate malicious behavior (for demonstration)
        boolean isMalicious = new Random().nextBoolean();  // Random chance for malicious behavior

        if (isMalicious) {
            slashValidator(selectedValidator);
            System.out.println("Validator " + selectedValidator + " acted maliciously and has been slashed.");
            return false;  // Block is invalid if validator acted maliciously
        }

        return selectedValidator != null && stakes.containsKey(selectedValidator);
    }

    /**
     * Penalizes a validator by reducing their stake by the SLASHING_PERCENTAGE.
     *
     * @param validator The address of the validator to be slashed
     */
    private void slashValidator(String validator) {
        if (stakes.containsKey(validator)) {
            double stake = stakes.get(validator);
            double slashedAmount = stake * SLASHING_PERCENTAGE;
            stakes.put(validator, stake - slashedAmount);

            // Record the slashing event
            slashedValidators.put(validator, slashedValidators.getOrDefault(validator, 0) + 1);

            System.out.println("Validator " + validator + " had their stake reduced by " + slashedAmount + ".");
        }
    }
}
