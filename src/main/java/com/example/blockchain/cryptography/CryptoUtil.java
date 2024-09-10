package com.example.blockchain.cryptography;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * CryptoUtil provides utility methods for cryptographic operations such as RSA encryption,
 * decryption, and key pair generation.
 */
public class CryptoUtil {

    /**
     * Generates an RSA key pair for encryption and decryption.
     *
     * @return The generated KeyPair
     * @throws Exception if an error occurs during key generation
     */
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);  // Key size of 2048 bits
        return keyGen.generateKeyPair();
    }

    /**
     * Encrypts data using the provided public key.
     *
     * @param publicKey The public key used for encryption
     * @param data      The data to be encrypted
     * @return The encrypted data in Base64 format
     * @throws Exception if an error occurs during encryption
     */
    public static String encryptData(PublicKey publicKey, String data) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Decrypts data using the provided private key.
     *
     * @param privateKey     The private key used for decryption
     * @param encryptedData  The encrypted data in Base64 format
     * @return The decrypted data as a string
     * @throws Exception if an error occurs during decryption
     */
    public static String decryptData(PrivateKey privateKey, String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }
}
