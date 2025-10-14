package com.thealgorithms.compression;

/**
 * An implementation of the Run-Length Encoding (RLE) algorithm.
 *
 * <p>Run-Length Encoding is a simple form of lossless data compression in which
 * runs of data (sequences in which the same data value occurs in many
 * consecutive data elements) are stored as a single data value and count,
 * rather than as the original run.
 *
 * <p>This implementation provides methods for both compressing and decompressing
 * a string. For example:
 * <ul>
 * <li>Compressing "AAAABBBCCDAA" results in "4A3B2C1D2A".</li>
 * <li>Decompressing "4A3B2C1D2A" results in "AAAABBBCCDAA".</li>
 * </ul>
 *
 * <p>Time Complexity: O(n) for both compression and decompression, where n is the
 * length of the input string.
 *
 * <p>References:
 * <ul>
 * <li><a href="https://en.wikipedia.org/wiki/Run-length_encoding">Wikipedia: Run-length encoding</a></li>
 * </ul>
 */
public final class RunLengthEncoding {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private RunLengthEncoding() {
    }

    /**
     * Compresses a string using the Run-Length Encoding algorithm.
     *
     * @param text The string to be compressed. Must not be null.
     * @return The compressed string. Returns an empty string if the input is empty.
     */
    public static String compress(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        StringBuilder compressed = new StringBuilder();
        int count = 1;

        for (int i = 0; i < text.length(); i++) {
            // Check if it's the last character or if the next character is different
            if (i == text.length() - 1 || text.charAt(i) != text.charAt(i + 1)) {
                compressed.append(count);
                compressed.append(text.charAt(i));
                count = 1; // Reset count for the new character
            } else {
                count++;
            }
        }
        return compressed.toString();
    }

    /**
     * Decompresses a string that was compressed using the Run-Length Encoding algorithm.
     *
     * @param compressedText The compressed string. Must not be null.
     * @return The original, uncompressed string.
     */
    public static String decompress(String compressedText) {
        if (compressedText == null || compressedText.isEmpty()) {
            return "";
        }

        StringBuilder decompressed = new StringBuilder();
        int count = 0;

        for (char ch : compressedText.toCharArray()) {
            if (Character.isDigit(ch)) {
                // Build the number for runs of 10 or more (e.g., "12A")
                count = count * 10 + ch - '0';
            } else {
                // Append the character 'count' times
                decompressed.append(String.valueOf(ch).repeat(Math.max(0, count)));
                count = 0; // Reset count for the next sequence
            }
        }
        return decompressed.toString();
    }
}
