package com.example.blockchain;

import java.security.MessageDigest;

public class StringUtil {
    // Method to apply SHA-256 hash to a given input string
    public static String applySha256(String input) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Apply SHA-256 to the input and get the hash byte array
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            // Convert the byte array into a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                // Convert each byte to a hexadecimal string
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');  // Append leading zero if necessary
                hexString.append(hex);
            }
            return hexString.toString();  // Return the resulting hexadecimal string
        } catch (Exception e) {
            throw new RuntimeException(e);  // Handle any exceptions that occur
        }
    }
}

