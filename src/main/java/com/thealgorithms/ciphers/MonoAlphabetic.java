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
                int idx = key.indexOf(c); // Get the index in the key
                if (idx != -1) {
                    char originalChar = (char) (idx + 'A'); // Convert index back to character
                    sb.append(originalChar);
                }
            } else {
                sb.append(c); // Append non-alphabet characters directly
            }
        }
        return sb.toString();
    }
}
