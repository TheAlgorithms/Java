package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * This class generates all valid combinations of parentheses for a given number of pairs using backtracking.
 */
public final class ParenthesesGenerator {
    private ParenthesesGenerator() {
    }

    /**
     * Generates all valid combinations of parentheses for a given number of pairs.
     *
     * @param n The number of pairs of parentheses.
     * @return A list of strings representing valid combinations of parentheses.
     * @throws IllegalArgumentException if n is less than 0.
     */
    public static List<String> generateParentheses(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The number of pairs of parentheses cannot be nagative");
        }
        List<String> result = new ArrayList<>();
        generateParenthesesHelper(result, "", 0, 0, n);
        return result;
    }

    /**
     * Helper function for generating all valid combinations of parentheses recursively.
     *
     * @param result  The list to store valid combinations.
     * @param current The current combination being formed.
     * @param open    The number of open parentheses.
     * @param close   The number of closed parentheses.
     * @param n       The total number of pairs of parentheses.
     */
    private static void generateParenthesesHelper(List<String> result, final String current, final int open, final int close, final int n) {
        if (current.length() == n * 2) {
            result.add(current);
            return;
        }
        if (open < n) {
            generateParenthesesHelper(result, current + "(", open + 1, close, n);
        }
        if (close < open) {
            generateParenthesesHelper(result, current + ")", open, close + 1, n);
        }
    }
}
