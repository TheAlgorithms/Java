package com.thealgorithms.ciphers;

/**
 * This class implements the Triple DES (3DES) encryption algorithm.
 * 3DES applies DES encryption three times with three different keys:
 * - Encryption: Encrypt with key1, Decrypt with key2, Encrypt with key3
 * - Decryption: Decrypt with key3, Encrypt with key2, Decrypt with key1
 * 
 * Each key must be a 64-bit binary string (same format as DES).
 * 
 * Reference: https://en.wikipedia.org/wiki/Triple_DES
 */
public class TripleDES {

    private DES des1;
    private DES des2;
    private DES des3;

    /**
     * Constructor for TripleDES with three different keys
     * @param key1 First 64-bit binary key
     * @param key2 Second 64-bit binary key  
     * @param key3 Third 64-bit binary key
     */
    public TripleDES(String key1, String key2, String key3) {
        this.des1 = new DES(key1);
        this.des2 = new DES(key2);
        this.des3 = new DES(key3);
    }

    /**
     * Constructor for TripleDES with two keys (key1 and key3 are the same)
     * This is a common 3DES variant called "EDE with two keys"
     * @param key1 First and third 64-bit binary key
     * @param key2 Second 64-bit binary key
     */
    public TripleDES(String key1, String key2) {
        this(key1, key2, key1);
    }

    /**
     * Encrypts a message using 3DES algorithm (Encrypt-Decrypt-Encrypt)
     * @param message The plaintext message to encrypt
     * @return The encrypted message as a binary string
     */
    public String encrypt(String message) {
        // Convert message to bytes
        byte[] messageBytes = message.getBytes();
        
        // Step 1: Encrypt with first key
        byte[] step1 = des1.encryptBytes(messageBytes);
        
        // Step 2: Decrypt with second key
        byte[] step2 = des2.decryptBytes(step1);
        
        // Step 3: Encrypt with third key
        byte[] step3 = des3.encryptBytes(step2);
        
        // Convert final result back to binary string
        return bytesToBinaryString(step3);
    }

    /**
     * Decrypts a message using 3DES algorithm (Decrypt-Encrypt-Decrypt)
     * @param message The encrypted binary string to decrypt
     * @return The decrypted plaintext message
     */
    public String decrypt(String message) {
        // Convert binary string to bytes
        byte[] messageBytes = binaryStringToBytes(message);
        
        // Step 1: Decrypt with third key
        byte[] step1 = des3.decryptBytes(messageBytes);
        
        // Step 2: Encrypt with second key
        byte[] step2 = des2.encryptBytes(step1);
        
        // Step 3: Decrypt with first key
        byte[] step3 = des1.decryptBytes(step2);
        
        // Convert final result back to string
        return new String(step3);
    }

    /**
     * Converts a byte array to a binary string
     * @param bytes The byte array to convert
     * @return Binary string representation
     */
    private String bytesToBinaryString(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        }
        return result.toString();
    }

    /**
     * Converts a binary string to a byte array
     * @param binaryString The binary string to convert
     * @return Byte array representation
     */
    private byte[] binaryStringToBytes(String binaryString) {
        int len = binaryString.length();
        byte[] result = new byte[len / 8];
        for (int i = 0; i < len; i += 8) {
            result[i / 8] = (byte) Integer.parseInt(binaryString.substring(i, i + 8), 2);
        }
        return result;
    }

    /**
     * Gets the first key
     * @return The first 64-bit binary key
     */
    public String getKey1() {
        return des1.getKey();
    }

    /**
     * Gets the second key
     * @return The second 64-bit binary key
     */
    public String getKey2() {
        return des2.getKey();
    }

    /**
     * Gets the third key
     * @return The third 64-bit binary key
     */
    public String getKey3() {
        return des3.getKey();
    }

    /**
     * Sets the first key
     * @param key1 The new 64-bit binary key
     */
    public void setKey1(String key1) {
        des1.setKey(key1);
    }

    /**
     * Sets the second key
     * @param key2 The new 64-bit binary key
     */
    public void setKey2(String key2) {
        des2.setKey(key2);
    }

    /**
     * Sets the third key
     * @param key3 The new 64-bit binary key
     */
    public void setKey3(String key3) {
        des3.setKey(key3);
    }
}