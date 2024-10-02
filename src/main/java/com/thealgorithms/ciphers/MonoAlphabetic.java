package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

    // *********** Unit Test Section **************

    // Method to provide test data for encryption
    private static Stream<Arguments> provideEncryptionData() {
        String key = "MNBVCXZLKJHGFDSAPOIUYTREWQ";
        return Stream.of(
            // Input data, key, expected encrypted output
            Arguments.of("HELLO", key, "GFSSD"),
            Arguments.of("JAVA", key, "MZSM")
        );
    }

    // Test for encryption
    @ParameterizedTest
    @MethodSource("provideEncryptionData")
    public void testEncrypt(String data, String key, String expected) {
        assertEquals(expected, MonoAlphabetic.encrypt(data, key));
    }

    // Method to provide test data for decryption
    private static Stream<Arguments> provideDecryptionData() {
        String key = "MNBVCXZLKJHGFDSAPOIUYTREWQ";
        return Stream.of(
            // Encrypted data, key, expected decrypted output
            Arguments.of("GFSSD", key, "HELLO"),
            Arguments.of("MZSM", key, "JAVA")
        );
    }

    // Test for decryption
    @ParameterizedTest
    @MethodSource("provideDecryptionData")
    public void testDecrypt(String data, String key, String expected) {
        assertEquals(expected, MonoAlphabetic.decrypt(data, key));
    }
}
