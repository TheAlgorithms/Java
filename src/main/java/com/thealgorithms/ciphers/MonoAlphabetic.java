package com.thealgorithms.ciphers;

public final class MonoAlphabetic {

    // Private constructor to prevent instantiation of utility class
    private MonoAlphabetic() {
        throw new UnsupportedOperationException("Utility class");
    }

    // Encryption method
    public static String encrypt(String data, String key) {
        StringBuilder sb = new StringBuilder();

        // Convert to uppercase to match the key mapping
        data = data.toUpperCase();

        for (char c : data.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                // Get the index (0-25) for the character
                int idx = c - 'A';
                // Append the character at the corresponding index in the key
                sb.append(key.charAt(idx));
            } else {
                // If character is not A-Z, append it as is
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // Decryption method
    public static String decrypt(String data, String key) {
        StringBuilder sb = new StringBuilder();

        // Convert to uppercase to match the key mapping
        data = data.toUpperCase();

        for (char c : data.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                // Get the index from the key for the character
                int idx = getIndex(c, key);
                // Append the original character
                if (idx != -1) {
                    char originalChar = (char) (idx + 'A');
                    sb.append(originalChar);
                }
            } else {
                // If character is not A-Z, append it as is
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // Helper method to get index of a character in the key
    private static int getIndex(char c, String key) {
        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) == c) {
                return i; // Return the index if the character matches
            }
        }
        return -1; // Return -1 if character not found (should not happen for valid inputs)
    }
}
