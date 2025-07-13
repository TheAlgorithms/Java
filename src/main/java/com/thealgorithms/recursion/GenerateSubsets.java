package com.thealgorithms.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to generate all subsets (power set) of a given string using recursion.
 *
 * <p>For example, the string "ab" will produce: ["ab", "a", "b", ""]
 */
public final class GenerateSubsets {

    private GenerateSubsets() {
    }

    /**
     * Generates all subsets (power set) of the given string using recursion.
     *
     * @param str the input string to generate subsets for
     * @return a list of all subsets of the input string
     */
    public static List<String> subsetRecursion(String str) {
        return generateSubsets("", str);
    }

    /**
     * Recursive helper method to generate subsets by including or excluding characters.
     *
     * @param current the current prefix being built
     * @param remaining the remaining string to process
     * @return list of subsets formed from current and remaining
     */
    private static List<String> generateSubsets(String current, String remaining) {
        if (remaining.isEmpty()) {
            List<String> result = new ArrayList<>();
            result.add(current);
            return result;
        }

        char ch = remaining.charAt(0);
        String next = remaining.substring(1);

        // Include the character
        List<String> withChar = generateSubsets(current + ch, next);

        // Exclude the character
        List<String> withoutChar = generateSubsets(current, next);

        withChar.addAll(withoutChar);
        return withChar;
    }
}
