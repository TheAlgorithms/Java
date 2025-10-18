package com.thealgorithms.compression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An implementation of the Lempel-Ziv-Welch (LZW) algorithm.
 *
 * <p>
 * LZW is a universal lossless data compression algorithm created by Abraham
 * Lempel, Jacob Ziv, and Terry Welch. It works by building a dictionary of
 * strings encountered during compression and replacing occurrences of those
 * strings with a shorter code.
 * </p>
 *
 * <p>
 * This implementation handles standard ASCII characters and provides methods for
 * both compression and decompression.
 * <ul>
 * <li>Compressing "TOBEORNOTTOBEORTOBEORNOT" results in a list of integer
 * codes.</li>
 * <li>Decompressing that list of codes results back in the original
 * string.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Time Complexity: O(n) for both compression and decompression, where n is the
 * length of the input string.
 * </p>
 *
 * <p>
 * References:
 * <ul>
 * <li><a href="https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Welch">Wikipedia:
 * Lempel–Ziv–Welch</a></li>
 * </ul>
 * </p>
 */
public final class LZW {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private LZW() {
    }

    /**
     * Compresses a string using the LZW algorithm.
     *
     * @param uncompressed The string to be compressed. Can be null.
     * @return A list of integers representing the compressed data. Returns an empty
     * list if the input is null or empty.
     */
    public static List<Integer> compress(String uncompressed) {
        if (uncompressed == null || uncompressed.isEmpty()) {
            return new ArrayList<>();
        }

        // Initialize dictionary with single characters (ASCII 0-255)
        int dictSize = 256;
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < dictSize; i++) {
            dictionary.put("" + (char) i, i);
        }

        String w = "";
        List<Integer> result = new ArrayList<>();
        for (char c : uncompressed.toCharArray()) {
            String wc = w + c;
            if (dictionary.containsKey(wc)) {
                // If the new string is in the dictionary, extend the current string
                w = wc;
            } else {
                // Otherwise, output the code for the current string
                result.add(dictionary.get(w));
                // Add the new string to the dictionary
                dictionary.put(wc, dictSize++);
                // Start a new current string
                w = "" + c;
            }
        }

        // Output the code for the last remaining string
        result.add(dictionary.get(w));
        return result;
    }

    /**
     * Decompresses a list of integers back into a string using the LZW algorithm.
     *
     * @param compressed A list of integers representing the compressed data. Can be
     *                   null.
     * @return The original, uncompressed string. Returns an empty string if the
     * input is null or empty.
     */
    public static String decompress(List<Integer> compressed) {
        if (compressed == null || compressed.isEmpty()) {
            return "";
        }

        // Initialize dictionary with single characters (ASCII 0-255)
        int dictSize = 256;
        Map<Integer, String> dictionary = new HashMap<>();
        for (int i = 0; i < dictSize; i++) {
            dictionary.put(i, "" + (char) i);
        }

        // Decompress the first code
        String w = "" + (char) (int) compressed.removeFirst();
        StringBuilder result = new StringBuilder(w);

        for (int k : compressed) {
            String entry;
            if (dictionary.containsKey(k)) {
                // The code is in the dictionary
                entry = dictionary.get(k);
            } else if (k == dictSize) {
                // Special case for sequences like "ababab"
                entry = w + w.charAt(0);
            } else {
                throw new IllegalArgumentException("Bad compressed k: " + k);
            }

            result.append(entry);

            // Add new sequence to the dictionary
            dictionary.put(dictSize++, w + entry.charAt(0));

            w = entry;
        }
        return result.toString();
    }
}
