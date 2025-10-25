package com.thealgorithms.compression;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the Burrows-Wheeler Transform (BWT) and its inverse.
 * <p>
 * BWT is a reversible data transformation algorithm that rearranges a string into runs of
 * similar characters. While not a compression algorithm itself, it significantly improves
 * the compressibility of data for subsequent algorithms like Move-to-Front encoding and
 * Run-Length Encoding.
 * </p>
 *
 * <p>The transform works by:
 * <ol>
 *   <li>Generating all rotations of the input string</li>
 *   <li>Sorting these rotations lexicographically</li>
 *   <li>Taking the last column of the sorted matrix as output</li>
 *   <li>Recording the index of the original string in the sorted matrix</li>
 * </ol>
 * </p>
 *
 * <p><b>Important:</b> The input string should end with a unique end-of-string marker
 * (typically '$') that:
 * <ul>
 *   <li>Does not appear anywhere else in the text</li>
 *   <li>Is lexicographically smaller than all other characters</li>
 *   <li>Ensures unique rotations and enables correct inverse transformation</li>
 * </ul>
 * Without this marker, the inverse transform may not correctly reconstruct the original string.
 * </p>
 *
 * <p><b>Time Complexity:</b>
 * <ul>
 *   <li>Forward transform: O(n² log n) where n is the string length</li>
 *   <li>Inverse transform: O(n) using the LF-mapping technique</li>
 * </ul>
 * </p>
 *
 * <p><b>Example:</b></p>
 * <pre>
 * Input:  "banana$"
 * Output: BWTResult("annb$aa", 4)
 *         - "annb$aa" is the transformed string (groups similar characters)
 *         - 4 is the index of the original string in the sorted rotations
 * </pre>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Burrows%E2%80%93Wheeler_transform">Burrows–Wheeler transform (Wikipedia)</a>
 */
public final class BurrowsWheelerTransform {

    private BurrowsWheelerTransform() {
    }

    /**
     * A container for the result of the forward BWT.
     * <p>
     * Contains the transformed string and the index of the original string
     * in the sorted rotations matrix, both of which are required for the
     * inverse transformation.
     * </p>
     */
    public static class BWTResult {
        /** The transformed string (last column of the sorted rotation matrix) */
        public final String transformed;

        /** The index of the original string in the sorted rotations matrix */
        public final int originalIndex;

        /**
         * Constructs a BWTResult with the transformed string and original index.
         *
         * @param transformed the transformed string (L-column)
         * @param originalIndex the index of the original string in sorted rotations
         */
        public BWTResult(String transformed, int originalIndex) {
            this.transformed = transformed;
            this.originalIndex = originalIndex;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            BWTResult bwtResult = (BWTResult) obj;
            return originalIndex == bwtResult.originalIndex && transformed.equals(bwtResult.transformed);
        }

        @Override
        public int hashCode() {
            return 31 * transformed.hashCode() + originalIndex;
        }

        @Override
        public String toString() {
            return "BWTResult[transformed=" + transformed + ", originalIndex=" + originalIndex + "]";
        }
    }

    /**
     * Performs the forward Burrows-Wheeler Transform on the input string.
     * <p>
     * The algorithm generates all cyclic rotations of the input, sorts them
     * lexicographically, and returns the last column of this sorted matrix
     * along with the position of the original string.
     * </p>
     *
     * <p><b>Note:</b> It is strongly recommended that the input string ends with
     * a unique end-of-string marker (e.g., '$') that is lexicographically smaller
     * than any other character in the string. This ensures correct inversion.</p>
     *
     * @param text the input string to transform; must not be {@code null}
     * @return a {@link BWTResult} object containing the transformed string (L-column)
     *         and the index of the original string in the sorted rotations matrix;
     *         returns {@code BWTResult("", -1)} for empty input
     * @throws NullPointerException if {@code text} is {@code null}
     */
    public static BWTResult transform(String text) {
        if (text == null || text.isEmpty()) {
            return new BWTResult("", -1);
        }

        int n = text.length();

        // Generate all rotations of the input string
        String[] rotations = new String[n];
        for (int i = 0; i < n; i++) {
            rotations[i] = text.substring(i) + text.substring(0, i);
        }

        // Sort rotations lexicographically
        Arrays.sort(rotations);
        int originalIndex = Arrays.binarySearch(rotations, text);
        StringBuilder lastColumn = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            lastColumn.append(rotations[i].charAt(n - 1));
        }

        return new BWTResult(lastColumn.toString(), originalIndex);
    }

    /**
     * Performs the inverse Burrows-Wheeler Transform using the LF-mapping technique.
     * <p>
     * The LF-mapping (Last-First mapping) is an efficient method to reconstruct
     * the original string from the BWT output without explicitly reconstructing
     * the entire sorted rotations matrix.
     * </p>
     *
     * <p>The algorithm works by:
     * <ol>
     *   <li>Creating the first column by sorting the BWT string</li>
     *   <li>Building a mapping from first column indices to last column indices</li>
     *   <li>Following this mapping starting from the original index to reconstruct the string</li>
     * </ol>
     * </p>
     *
     * @param bwtString the transformed string (L-column) from the forward transform; must not be {@code null}
     * @param originalIndex the index of the original string row from the forward transform;
     *                      use -1 for empty strings
     * @return the original, untransformed string; returns empty string if input is empty or {@code originalIndex} is -1
     * @throws NullPointerException if {@code bwtString} is {@code null}
     * @throws IllegalArgumentException if {@code originalIndex} is out of valid range (except -1)
     */
    public static String inverseTransform(String bwtString, int originalIndex) {
        if (bwtString == null || bwtString.isEmpty() || originalIndex == -1) {
            return "";
        }

        int n = bwtString.length();
        if (originalIndex < 0 || originalIndex >= n) {
            throw new IllegalArgumentException("Original index must be between 0 and " + (n - 1) + ", got: " + originalIndex);
        }

        char[] lastColumn = bwtString.toCharArray();
        char[] firstColumn = bwtString.toCharArray();
        Arrays.sort(firstColumn);

        // Create the "next" array for LF-mapping.
        // next[i] stores the row index in the last column that corresponds to firstColumn[i]
        int[] next = new int[n];

        // Track the count of each character seen so far in the last column
        Map<Character, Integer> countMap = new HashMap<>();

        // Store the first occurrence index of each character in the first column
        Map<Character, Integer> firstOccurrence = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (!firstOccurrence.containsKey(firstColumn[i])) {
                firstOccurrence.put(firstColumn[i], i);
            }
        }

        // Build the LF-mapping
        for (int i = 0; i < n; i++) {
            char c = lastColumn[i];
            int count = countMap.getOrDefault(c, 0);
            int firstIndex = firstOccurrence.get(c);
            next[firstIndex + count] = i;
            countMap.put(c, count + 1);
        }

        // Reconstruct the original string by following the LF-mapping
        StringBuilder originalString = new StringBuilder(n);
        int currentRow = originalIndex;
        for (int i = 0; i < n; i++) {
            originalString.append(firstColumn[currentRow]);
            currentRow = next[currentRow];
        }

        return originalString.toString();
    }
}
