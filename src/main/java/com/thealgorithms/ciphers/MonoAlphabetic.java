package com.thealgorithms.ciphers;

public final class MonoAlphabetic {

    // Private constructor to prevent instantiation of utility class
    private MonoAlphabetic() {
        throw new UnsupportedOperationException("Utility class");
    }

    // Encryption method
    public static String encrypt(String data, String key) {
        int idx;
        char c;
        StringBuilder sb = new StringBuilder(data.toUpperCase());

        for (int i = 0; i < sb.length(); i++) {
            idx = sb.charAt(i) - 65; // Subtract ASCII value of 'A' to get index
            c = key.charAt(idx); // Find the character at the corresponding key position
            sb.setCharAt(i, c); // Replace with the key character
        }
        return sb.toString();
    }

    // Decryption method
    public static String decrypt(String data, String key) {
        int idx;
        char c;
        StringBuilder sb = new StringBuilder(data.toUpperCase());

        for (int i = 0; i < sb.length(); i++) {
            c = sb.charAt(i); // Get the character from encrypted data
            idx = getIndex(c, key); // Get the corresponding index from the key
            c = (char) (idx + 65); // Convert index back to character
            sb.setCharAt(i, c); // Replace with the original character
        }
        return sb.toString();
    }

    // Helper method to get index of a character in the key
    public static int getIndex(char c, String key) {
        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) == c) {
                return i; // Return the index if the character matches
            }
        }
        return -1; // Return -1 if character not found (should not happen for valid inputs)
    }
}
