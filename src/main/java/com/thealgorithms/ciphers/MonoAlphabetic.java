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
        data = data.toUpperCase();

        for (char c : data.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                int idx = c - 'A'; // Index in alphabet
                sb.append(key.charAt(idx)); // Append the character from the key
            } else {
                sb.append(c); // Append non-alphabet characters directly
            }
        }
        return sb.toString();
    }

    // Decryption method
    public static String decrypt(String data, String key) {
        StringBuilder sb = new StringBuilder();
        data = data.toUpperCase();

        for (char c : data.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                int idx = charToPos(c); // Get the index in the key
                char encryptedChar = key.charAt(idx);
                sb.append(encryptedChar);
            }
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
