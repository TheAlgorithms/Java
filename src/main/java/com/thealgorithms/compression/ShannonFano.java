package com.thealgorithms.compression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * An implementation of the Shannon-Fano algorithm for generating prefix codes.
 *
 * <p>Shannon-Fano coding is an entropy encoding technique for lossless data
 * compression. It assigns variable-length codes to symbols based on their
 * frequencies of occurrence. It is a precursor to Huffman coding and works by
 * recursively partitioning a sorted list of symbols into two sub-lists with
 * nearly equal total frequencies.
 *
 * <p>The algorithm works as follows:
 * <ol>
 * <li>Count the frequency of each symbol in the input data.</li>
 * <li>Sort the symbols in descending order of their frequencies.</li>
 * <li>Recursively divide the list of symbols into two parts with sums of
 * frequencies as close as possible to each other.</li>
 * <li>Assign a '0' bit to the codes in the first part and a '1' bit to the codes
 * in the second part.</li>
 * <li>Repeat the process for each part until a part contains only one symbol.</li>
 * </ol>
 *
 * <p>Time Complexity: O(n^2) in this implementation due to the partitioning logic,
 * or O(n log n) if a more optimized partitioning strategy is used.
 * Sorting takes O(n log n), where n is the number of unique symbols.
 *
 * <p>References:
 * <ul>
 * <li><a href="https://en.wikipedia.org/wiki/ShannonÃ¢â‚¬"Fano_coding">Wikipedia: ShannonÃ¢â‚¬"Fano coding</a></li>
 * </ul>
 */
public final class ShannonFano {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private ShannonFano() {
    }

    /**
     * A private inner class to represent a symbol and its frequency.
     * Implements Comparable to allow sorting based on frequency.
     */
    private static class Symbol implements Comparable<Symbol> {
        final char character;
        final int frequency;
        String code = "";

        Symbol(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Symbol other) {
            return Integer.compare(other.frequency, this.frequency); // Sort descending
        }
    }

    /**
     * Generates Shannon-Fano codes for the symbols in a given text.
     *
     * @param text The input string for which to generate codes. Must not be null.
     * @return A map where keys are characters and values are their corresponding Shannon-Fano codes.
     */
    public static Map<Character, String> generateCodes(String text) {
        if (text == null || text.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        List<Symbol> symbols = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            symbols.add(new Symbol(entry.getKey(), entry.getValue()));
        }

        Collections.sort(symbols);

        // Special case: only one unique symbol
        if (symbols.size() == 1) {
            symbols.getFirst().code = "0";
        } else {
            buildCodeTree(symbols, 0, symbols.size() - 1, "");
        }

        return symbols.stream().collect(Collectors.toMap(s -> s.character, s -> s.code));
    }

    /**
     * Recursively builds the Shannon-Fano code tree by partitioning the list of symbols.
     * Uses index-based approach to avoid sublist creation issues.
     *
     * @param symbols The sorted list of symbols to be processed.
     * @param start   The start index of the current partition.
     * @param end     The end index of the current partition (inclusive).
     * @param prefix  The current prefix code being built for the symbols in this partition.
     */
    private static void buildCodeTree(List<Symbol> symbols, int start, int end, String prefix) {
        // The initial check in generateCodes ensures start <= end is always true here.
        // The base case is when a partition has only one symbol.
        if (start == end) {
            symbols.get(start).code = prefix;
            return;
        }

        // Find the optimal split point
        int splitIndex = findSplitIndex(symbols, start, end);

        // Recursively process left and right partitions with updated prefixes
        buildCodeTree(symbols, start, splitIndex, prefix + "0");
        buildCodeTree(symbols, splitIndex + 1, end, prefix + "1");
    }

    /**
     * Finds the index that splits the range into two parts with the most balanced frequency sums.
     * This method tries every possible split point and returns the index that minimizes the
     * absolute difference between the two partition sums.
     *
     * @param symbols The sorted list of symbols.
     * @param start   The start index of the range.
     * @param end     The end index of the range (inclusive).
     * @return The index of the last element in the first partition.
     */
    private static int findSplitIndex(List<Symbol> symbols, int start, int end) {
        // Calculate total frequency for the entire range
        long totalFrequency = 0;
        for (int i = start; i <= end; i++) {
            totalFrequency += symbols.get(i).frequency;
        }

        long leftSum = 0;
        long minDifference = Long.MAX_VALUE;
        int splitIndex = start;

        // Try every possible split point and find the one with minimum difference
        for (int i = start; i < end; i++) {
            leftSum += symbols.get(i).frequency;
            long rightSum = totalFrequency - leftSum;
            long difference = Math.abs(leftSum - rightSum);

            if (difference < minDifference) {
                minDifference = difference;
                splitIndex = i;
            }
        }
        return splitIndex;
    }
}
