package com.thealgorithms.ciphers;

/**
 * This class implements the Triple Data Encryption Standard (3DES) algorithm.
 * 3DES uses three 64-bit keys to encrypt data in the pattern: encrypt-decrypt-encrypt.
 * Decryption follows the reverse pattern: decrypt-encrypt-decrypt.
 */
public class TripleDES {

    private final DES des1;
    private final DES des2;
    private final DES des3;

    /**
     * Constructs a TripleDES instance with three 64-bit binary keys.
     * @param key1 First 64-bit binary key
     * @param key2 Second 64-bit binary key
     * @param key3 Third 64-bit binary key
     */
    public TripleDES(String key1, String key2, String key3) {
        des1 = new DES(key1);
        des2 = new DES(key2);
        des3 = new DES(key3);
    }

    /**
     * Encrypts a message using the 3DES algorithm (encrypt-decrypt-encrypt).
     * @param message The plaintext message to encrypt
     * @return The encrypted message as a binary string
     */
    public String encrypt(String message) {
        return encrypt(message.getBytes());
    }

    /**
     * Encrypts a byte array using the 3DES algorithm (encrypt-decrypt-encrypt).
     * @param data The byte array to encrypt
     * @return The encrypted message as a binary string
     */
    public String encrypt(byte[] data) {
        String encrypted1 = des1.encrypt(data);
        byte[] decrypted2 = des2.decryptToBytes(encrypted1);
        return des3.encrypt(decrypted2);
    }

    /**
     * Decrypts a message using the 3DES algorithm (decrypt-encrypt-decrypt).
     * @param message The encrypted message (binary string) to decrypt
     * @return The decrypted plaintext message
     */
    public String decrypt(String message) {
        return new String(decryptToBytes(message));
    }

    /**
     * Decrypts a message using the 3DES algorithm (decrypt-encrypt-decrypt).
     * @param message The encrypted message (binary string) to decrypt
     * @return The decrypted byte array
     */
    public byte[] decryptToBytes(String message) {
        byte[] decrypted3 = des3.decryptToBytes(message);
        String encrypted2 = des2.encrypt(decrypted3);
        return des1.decryptToBytes(encrypted2);
    }
}