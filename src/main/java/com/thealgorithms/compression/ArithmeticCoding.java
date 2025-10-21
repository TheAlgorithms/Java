package com.thealgorithms.compression;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An implementation of the Arithmetic Coding algorithm.
 *
 * <p>
 * Arithmetic coding is a form of entropy encoding used in lossless data
 * compression. It encodes an entire message into a single number, a fraction n
 * where (0.0 <= n < 1.0). Unlike Huffman coding, which assigns a specific
 * bit sequence to each symbol, arithmetic coding represents the message as a
 * sub-interval of the [0, 1) interval.
 * </p>
 *
 * <p>
 * This implementation uses BigDecimal for precision to handle the shrinking
 * intervals, making it suitable for educational purposes to demonstrate the
 * core logic.
 * </p>
 *
 * <p>
 * Time Complexity: O(n*m) for compression and decompression where n is the
 * length of the input and m is the number of unique symbols, due to the need
 * to calculate symbol probabilities.
 * </p>
 *
 * <p>
 * References:
 * <ul>
 * <li><a href="https://en.wikipedia.org/wiki/Arithmetic_coding">Wikipedia:
 * Arithmetic coding</a></li>
 * </ul>
 * </p>
 */
public final class ArithmeticCoding {

    private ArithmeticCoding() {
    }

    /**
     * Compresses a string using the Arithmetic Coding algorithm.
     *
     * @param uncompressed The string to be compressed.
     * @return The compressed representation as a BigDecimal number.
     * @throws IllegalArgumentException if the input string is null or empty.
     */
    public static BigDecimal compress(String uncompressed) {
        if (uncompressed == null || uncompressed.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty.");
        }

        Map<Character, Symbol> probabilityTable = calculateProbabilities(uncompressed);

        BigDecimal low = BigDecimal.ZERO;
        BigDecimal high = BigDecimal.ONE;

        for (char symbol : uncompressed.toCharArray()) {
            BigDecimal range = high.subtract(low);
            Symbol sym = probabilityTable.get(symbol);

            high = low.add(range.multiply(sym.high()));
            low = low.add(range.multiply(sym.low()));
        }

        return low; // Return the lower bound of the final interval
    }

    /**
     * Decompresses a BigDecimal number back into the original string.
     *
     * @param compressed       The compressed BigDecimal number.
     * @param length           The length of the original uncompressed string.
     * @param probabilityTable The probability table used during compression.
     * @return The original, uncompressed string.
     */
    public static String decompress(BigDecimal compressed, int length, Map<Character, Symbol> probabilityTable) {
        StringBuilder decompressed = new StringBuilder();

        // Create a sorted list of symbols for deterministic decompression, matching the
        // order used in calculateProbabilities
        List<Map.Entry<Character, Symbol>> sortedSymbols = new ArrayList<>(probabilityTable.entrySet());
        sortedSymbols.sort(Map.Entry.comparingByKey());

        BigDecimal low = BigDecimal.ZERO;
        BigDecimal high = BigDecimal.ONE;

        for (int i = 0; i < length; i++) {
            BigDecimal range = high.subtract(low);

            // Find which symbol the compressed value falls into
            for (Map.Entry<Character, Symbol> entry : sortedSymbols) {
                Symbol sym = entry.getValue();

                // Calculate the actual range for this symbol in the current interval
                BigDecimal symLow = low.add(range.multiply(sym.low()));
                BigDecimal symHigh = low.add(range.multiply(sym.high()));

                // Check if the compressed value falls within this symbol's range
                if (compressed.compareTo(symLow) >= 0 && compressed.compareTo(symHigh) < 0) {
                    decompressed.append(entry.getKey());

                    // Update the interval for the next iteration
                    low = symLow;
                    high = symHigh;
                    break;
                }
            }
        }

        return decompressed.toString();
    }

    /**
     * Calculates the frequency and probability range for each character in the
     * input string in a deterministic order.
     *
     * @param text The input string.
     * @return A map from each character to a Symbol object containing its
     * probability range.
     */
    public static Map<Character, Symbol> calculateProbabilities(String text) {
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }

        // Sort the characters to ensure a deterministic order for the probability table
        List<Character> sortedKeys = new ArrayList<>(frequencies.keySet());
        Collections.sort(sortedKeys);

        Map<Character, Symbol> probabilityTable = new HashMap<>();
        BigDecimal currentLow = BigDecimal.ZERO;
        int total = text.length();

        for (char symbol : sortedKeys) {
            BigDecimal probability = BigDecimal.valueOf(frequencies.get(symbol)).divide(BigDecimal.valueOf(total), MathContext.DECIMAL128);
            BigDecimal high = currentLow.add(probability);
            probabilityTable.put(symbol, new Symbol(currentLow, high));
            currentLow = high;
        }

        return probabilityTable;
    }

    /**
     * Helper class to store the probability range [low, high) for a symbol.
     */
    public record Symbol(BigDecimal low, BigDecimal high) {
    }
}
