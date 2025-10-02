package com.thealgorithms.conversions;

import java.util.Base64;

/**
 * Converts text to Base64 and Base64 back to text
 *
 * @author Ayrton Souza
 */
public final class Base64Util {

    private Base64Util() {
    }

    /**
     * Encodes a given string into Base64 format.
     *
     * @param input The text to be converted to Base64.
     * @return Base64 representation of the input text.
     * @throws IllegalArgumentException if input is null.
     */
    public static String encode(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    /**
     * Decodes a Base64 string back to original text.
     *
     * @param base64 The Base64 encoded string.
     * @return The decoded original text.
     * @throws IllegalArgumentException if base64 is null or not a valid Base64 string.
     */
    public static String decode(String base64) {
        if (base64 == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64);
            return new String(decodedBytes);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Base64 input", e);
        }
    }
}