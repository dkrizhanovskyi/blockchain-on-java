package com.example.blockchain.cryptography;

import java.security.MessageDigest;

/**
 * StringUtil is a utility class for common cryptographic operations used in the blockchain.
 * It includes methods for hashing strings using SHA-256.
 */
public class StringUtil {

    /**
     * Applies the SHA-256 hashing algorithm to a given input string and returns the resulting hash.
     * This method ensures that data is secured cryptographically and cannot be tampered with.
     *
     * @param input The input string to be hashed using SHA-256
     * @return The resulting hash as a hexadecimal string
     */
    public static String applySha256(String input) {
        try {
            // Create a SHA-256 digest instance
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            // Apply the digest to the input string's bytes and store the result in a byte array
            byte[] hash = digest.digest(input.getBytes("UTF-8"));

            // Convert the resulting byte array into a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);  // Convert each byte to a hex value
                if (hex.length() == 1) {
                    hexString.append('0');  // Append leading zero if necessary
                }
                hexString.append(hex);  // Append the hex string to the final result
            }

            // Return the resulting hexadecimal string (hash)
            return hexString.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);  // Catch and rethrow any exceptions that occur during hashing
        }
    }
}
