package com.thealgorithms.ciphers;

public final class MonoAlphabetic {

    private MonoAlphabetic() {
        throw new UnsupportedOperationException("Utility class");
    }

    // Encryption method
    public static String encrypt(String data, String key) {
        if (!data.matches("[A-Z]+")) {
            throw new IllegalArgumentException("Input data contains invalid characters. Only uppercase A-Z are allowed.");
        }
        StringBuilder sb = new StringBuilder();

        // Encrypt each character
        for (char c : data.toCharArray()) {
            int idx = charToPos(c); // Get the index of the character
            sb.append(key.charAt(idx)); // Map to the corresponding character in the key
        }
        return sb.toString();
    }

    // Decryption method
    public static String decrypt(String data, String key) {
        StringBuilder sb = new StringBuilder();

        // Decrypt each character
        for (char c : data.toCharArray()) {
            int idx = key.indexOf(c); // Find the index of the character in the key
            if (idx == -1) {
                throw new IllegalArgumentException("Input data contains invalid characters.");
            }
            sb.append(posToChar(idx)); // Convert the index back to the original character
        }
        return sb.toString();
    }

    // Helper method: Convert a character to its position in the alphabet
    private static int charToPos(char c) {
        return c - 'A'; // Subtract 'A' to get position (0 for A, 1 for B, etc.)
    }

    // Helper method: Convert a position in the alphabet to a character
    private static char posToChar(int pos) {
        return (char) (pos + 'A'); // Add 'A' to convert position back to character
    }
}
