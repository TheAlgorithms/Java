package com.thealgorithms.ciphers;

import java.security.SecureRandom;

/**
 * The One-Time Pad Cipher is a symmetric encryption technique
 * that XORs plaintext with a truly random key of equal length.
 * 
 * ⚠️ Important:
 *  - The key must be random and used only once.
 *  - Key reuse makes it insecure.
 * 
 * Example:
 *   Plaintext:  HELLO
 *   Key:        XMCKL
 *   Ciphertext: EQNVZ
 * 
 * Reference:
 *   Shannon, C. E. (1949). Communication Theory of Secrecy Systems.
 */
public class OneTimePadCipher {

    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Encrypts or decrypts a message using the One-Time Pad method.
     * 
     * @param input The input string (plaintext or ciphertext)
     * @param key The key (must be the same length as input)
     * @return The resulting encrypted/decrypted string
     */
    public static String xorCipher(String input, String key) {
        if (input.length() != key.length()) {
            throw new IllegalArgumentException("Input and key lengths must match!");
        }

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char encryptedChar = (char) (input.charAt(i) ^ key.charAt(i));
            output.append(encryptedChar);
        }
        return output.toString();
    }

    /**
     * Generates a random key of the same length as the message.
     * 
     * @param length The desired key length
     * @return A random key string
     */
    public static String generateRandomKey(int length) {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < length; i++) {
            // Generate printable ASCII range (32–126)
            key.append((char) (RANDOM.nextInt(95) + 32));
        }
        return key.toString();
    }

    public static void main(String[] args) {
        String message = "HELLO WORLD";
        String key = generateRandomKey(message.length());

        String encrypted = xorCipher(message, key);
        String decrypted = xorCipher(encrypted, key);

        System.out.println("Message:   " + message);
        System.out.println("Key:       " + key);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}
