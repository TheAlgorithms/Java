package com.thealgorithms.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the full Damerau–Levenshtein distance algorithm.
 *
 * This algorithm calculates the minimum number of operations required
 * to transform one string into another. Supported operations are:
 * insertion, deletion, substitution, and transposition of adjacent characters.
 *
 * Unlike the restricted version (OSA), this implementation allows multiple
 * edits on the same substring, computing the true edit distance.
 *
 * Time Complexity: O(n * m * max(n, m))
 * Space Complexity: O(n * m)
 */
public final class DamerauLevenshteinDistance {

    private DamerauLevenshteinDistance() {
        // Utility class
    }

    /**
     * Computes the full Damerau–Levenshtein distance between two strings.
     *
     * @param s1 the first string
     * @param s2 the second string
     * @return the minimum edit distance between the two strings
     * @throws IllegalArgumentException if either input string is null
     */
    public static int distance(String s1, String s2) {
        validateInputs(s1, s2);

        int n = s1.length();
        int m = s2.length();

        Map<Character, Integer> charLastPosition = buildCharacterMap(s1, s2);
        int[][] dp = initializeTable(n, m);

        fillTable(s1, s2, dp, charLastPosition);

        return dp[n + 1][m + 1];
    }

    /**
     * Validates that both input strings are not null.
     *
     * @param s1 the first string to validate
     * @param s2 the second string to validate
     * @throws IllegalArgumentException if either string is null
     */
    private static void validateInputs(String s1, String s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Input strings must not be null.");
        }
    }

    /**
     * Builds a character map containing all unique characters from both strings.
     * Each character is initialized with a position value of 0.
     *
     * This map is used to track the last occurrence position of each character
     * during the distance computation, which is essential for handling transpositions.
     *
     * @param s1 the first string
     * @param s2 the second string
     * @return a map containing all unique characters from both strings, initialized to 0
     */
    private static Map<Character, Integer> buildCharacterMap(String s1, String s2) {
        Map<Character, Integer> charMap = new HashMap<>();
        for (char c : s1.toCharArray()) {
            charMap.putIfAbsent(c, 0);
        }
        for (char c : s2.toCharArray()) {
            charMap.putIfAbsent(c, 0);
        }
        return charMap;
    }

    /**
     * Initializes the dynamic programming table for the algorithm.
     *
     * The table has dimensions (n+2) x (m+2) where n and m are the lengths
     * of the input strings. The extra rows and columns are used to handle
     * the transposition operation correctly.
     *
     * The first row and column are initialized with the maximum possible distance,
     * while the second row and column represent the base case of transforming
     * from an empty string.
     *
     * @param n the length of the first string
     * @param m the length of the second string
     * @return an initialized DP table ready for computation
     */
    private static int[][] initializeTable(int n, int m) {
        int maxDist = n + m;
        int[][] dp = new int[n + 2][m + 2];

        dp[0][0] = maxDist;

        for (int i = 0; i <= n; i++) {
            dp[i + 1][0] = maxDist;
            dp[i + 1][1] = i;
        }

        for (int j = 0; j <= m; j++) {
            dp[0][j + 1] = maxDist;
            dp[1][j + 1] = j;
        }

        return dp;
    }

    /**
     * Fills the dynamic programming table by computing the minimum edit distance
     * for each substring pair.
     *
     * This method implements the core algorithm logic, iterating through both strings
     * and computing the minimum cost of transforming substrings. It considers all
     * four operations: insertion, deletion, substitution, and transposition.
     *
     * The character position map is updated as we progress through the first string
     * to enable efficient transposition cost calculation.
     *
     * @param s1 the first string
     * @param s2 the second string
     * @param dp the dynamic programming table to fill
     * @param charLastPosition map tracking the last position of each character in s1
     */
    private static void fillTable(String s1, String s2, int[][] dp, Map<Character, Integer> charLastPosition) {
        int n = s1.length();
        int m = s2.length();

        for (int i = 1; i <= n; i++) {
            int lastMatchCol = 0;

            for (int j = 1; j <= m; j++) {
                char char1 = s1.charAt(i - 1);
                char char2 = s2.charAt(j - 1);

                int lastMatchRow = charLastPosition.get(char2);
                int cost = (char1 == char2) ? 0 : 1;

                if (char1 == char2) {
                    lastMatchCol = j;
                }

                dp[i + 1][j + 1] = computeMinimumCost(dp, i, j, lastMatchRow, lastMatchCol, cost);
            }

            charLastPosition.put(s1.charAt(i - 1), i);
        }
    }

    /**
     * Computes the minimum cost among all possible operations at the current position.
     *
     * This method evaluates four possible operations:
     * 1. Substitution: replace character at position i with character at position j
     * 2. Insertion: insert character from s2 at position j
     * 3. Deletion: delete character from s1 at position i
     * 4. Transposition: swap characters that have been seen before
     *
     * The transposition cost accounts for the gap between the current position
     * and the last position where matching characters were found.
     *
     * @param dp the dynamic programming table
     * @param i the current position in the first string (1-indexed in the DP table)
     * @param j the current position in the second string (1-indexed in the DP table)
     * @param lastMatchRow the row index where the current character of s2 last appeared in s1
     * @param lastMatchCol the column index where the current character of s1 last matched in s2
     * @param cost the substitution cost (0 if characters match, 1 otherwise)
     * @return the minimum cost among all operations
     */
    private static int computeMinimumCost(int[][] dp, int i, int j, int lastMatchRow, int lastMatchCol, int cost) {
        int substitution = dp[i][j] + cost;
        int insertion = dp[i + 1][j] + 1;
        int deletion = dp[i][j + 1] + 1;
        int transposition = dp[lastMatchRow][lastMatchCol] + i - lastMatchRow - 1 + 1 + j - lastMatchCol - 1;

        return Math.min(Math.min(substitution, insertion), Math.min(deletion, transposition));
    }
}
