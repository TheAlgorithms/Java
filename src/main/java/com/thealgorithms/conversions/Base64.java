package com.thealgorithms.conversions;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Base64 is a group of binary-to-text encoding schemes that represent binary data
 * in an ASCII string format by translating it into a radix-64 representation.
 * Each base64 digit represents exactly 6 bits of data.
 *
 * Base64 encoding is commonly used when there is a need to encode binary data
 * that needs to be stored and transferred over media that are designed to deal
 * with textual data.
 *
 * Wikipedia Reference: https://en.wikipedia.org/wiki/Base64
 * Author: Nithin U.
 * Github: https://github.com/NithinU2802
 */

public final class Base64 {

    // Base64 character set
    private static final String BASE64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private static final char PADDING_CHAR = '=';

    private Base64() {
    }

    /**
     * Encodes the given byte array to a Base64 encoded string.
     *
     * @param input the byte array to encode
     * @return the Base64 encoded string
     * @throws IllegalArgumentException if input is null
     */
    public static String encode(byte[] input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        if (input.length == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        int padding = 0;

        // Process input in groups of 3 bytes
        for (int i = 0; i < input.length; i += 3) {
            // Get up to 3 bytes
            int byte1 = input[i] & 0xFF;
            int byte2 = (i + 1 < input.length) ? (input[i + 1] & 0xFF) : 0;
            int byte3 = (i + 2 < input.length) ? (input[i + 2] & 0xFF) : 0;

            // Calculate padding needed
            if (i + 1 >= input.length) {
                padding = 2;
            } else if (i + 2 >= input.length) {
                padding = 1;
            }

            // Combine 3 bytes into a 24-bit number
            int combined = (byte1 << 16) | (byte2 << 8) | byte3;

            // Extract four 6-bit groups
            result.append(BASE64_CHARS.charAt((combined >> 18) & 0x3F));
            result.append(BASE64_CHARS.charAt((combined >> 12) & 0x3F));
            result.append(BASE64_CHARS.charAt((combined >> 6) & 0x3F));
            result.append(BASE64_CHARS.charAt(combined & 0x3F));
        }

        // Replace padding characters
        if (padding > 0) {
            result.setLength(result.length() - padding);
            for (int i = 0; i < padding; i++) {
                result.append(PADDING_CHAR);
            }
        }

        return result.toString();
    }

    /**
     * Encodes the given string to a Base64 encoded string using UTF-8 encoding.
     *
     * @param input the string to encode
     * @return the Base64 encoded string
     * @throws IllegalArgumentException if input is null
     */
    public static String encode(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        return encode(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Decodes the given Base64 encoded string to a byte array.
     *
     * @param input the Base64 encoded string to decode
     * @return the decoded byte array
     * @throws IllegalArgumentException if input is null or contains invalid Base64 characters
     */
    public static byte[] decode(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        if (input.isEmpty()) {
            return new byte[0];
        }

        // Strict RFC 4648 compliance: length must be a multiple of 4
        if (input.length() % 4 != 0) {
            throw new IllegalArgumentException("Invalid Base64 input length; must be multiple of 4");
        }

        // Validate padding: '=' can only appear at the end (last 1 or 2 chars)
        int firstPadding = input.indexOf('=');
        if (firstPadding != -1 && firstPadding < input.length() - 2) {
            throw new IllegalArgumentException("Padding '=' can only appear at the end (last 1 or 2 characters)");
        }

        List<Byte> result = new ArrayList<>();

        // Process input in groups of 4 characters
        for (int i = 0; i < input.length(); i += 4) {
            // Get up to 4 characters
            int char1 = getBase64Value(input.charAt(i));
            int char2 = getBase64Value(input.charAt(i + 1));
            int char3 = input.charAt(i + 2) == '=' ? 0 : getBase64Value(input.charAt(i + 2));
            int char4 = input.charAt(i + 3) == '=' ? 0 : getBase64Value(input.charAt(i + 3));

            // Combine four 6-bit groups into a 24-bit number
            int combined = (char1 << 18) | (char2 << 12) | (char3 << 6) | char4;

            // Extract three 8-bit bytes
            result.add((byte) ((combined >> 16) & 0xFF));
            if (input.charAt(i + 2) != '=') {
                result.add((byte) ((combined >> 8) & 0xFF));
            }
            if (input.charAt(i + 3) != '=') {
                result.add((byte) (combined & 0xFF));
            }
        }

        // Convert List<Byte> to byte[]
        byte[] resultArray = new byte[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }

        return resultArray;
    }

    /**
     * Decodes the given Base64 encoded string to a string using UTF-8 encoding.
     *
     * @param input the Base64 encoded string to decode
     * @return the decoded string
     * @throws IllegalArgumentException if input is null or contains invalid Base64 characters
     */
    public static String decodeToString(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        byte[] decodedBytes = decode(input);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

    /**
     * Gets the numeric value of a Base64 character.
     *
     * @param c the Base64 character
     * @return the numeric value (0-63)
     * @throws IllegalArgumentException if character is not a valid Base64 character
     */
    private static int getBase64Value(char c) {
        if (c >= 'A' && c <= 'Z') {
            return c - 'A';
        } else if (c >= 'a' && c <= 'z') {
            return c - 'a' + 26;
        } else if (c >= '0' && c <= '9') {
            return c - '0' + 52;
        } else if (c == '+') {
            return 62;
        } else if (c == '/') {
            return 63;
        } else {
            throw new IllegalArgumentException("Invalid Base64 character: " + c);
        }
    }
}
