package com.thealgorithms.ciphers;

public final class MonoAlphabetic {

    private final String key;

    // Constructor to initialize the key for encryption and decryption
    public MonoAlphabetic(String key) {
        this.key = key.toUpperCase(); // Store the key in uppercase to match the encryption/decryption logic
    }

    // Encryption method
    public String encrypt(String data) {
        StringBuilder sb = new StringBuilder(data.toUpperCase());

        for (int i = 0; i < sb.length(); i++) {
            char currentChar = sb.charAt(i);
            if (Character.isLetter(currentChar)) { // Check if it's a letter
                int index = currentChar - 'A'; // Get the index for the character
                sb.setCharAt(i, key.charAt(index)); // Replace with the key character
            }
        }
        return sb.toString();
    }

    // Decryption method
    public String decrypt(String data) {
        StringBuilder sb = new StringBuilder(data.toUpperCase());

        for (int i = 0; i < sb.length(); i++) {
            char currentChar = sb.charAt(i);
            if (Character.isLetter(currentChar)) { // Check if it's a letter
                int index = key.indexOf(currentChar); // Find the character in the key
                if (index != -1) {
                    sb.setCharAt(i, (char) (index + 'A')); // Replace with the original character
                }
            }
        }
        return sb.toString();
    }

    // Static utility methods for encryption/decryption without creating an instance
    public static String encrypt(String data, String key) {
        return new MonoAlphabetic(key).encrypt(data);
    }

    public static String decrypt(String data, String key) {
        return new MonoAlphabetic(key).decrypt(data);
    }
}
