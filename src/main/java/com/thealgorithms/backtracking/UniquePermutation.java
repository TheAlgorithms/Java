package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Generates all UNIQUE permutations of a string, even when duplicate characters exist.
 *
 * Example:
 *   Input: "AAB"
 *   Output: ["AAB", "ABA", "BAA"]
 *
 * Time Complexity: O(n! * n)
 */
public final class UniquePermutation {

    private UniquePermutation() {
        // Prevent instantiation
        throw new UnsupportedOperationException("Utility class");
    }

    public static List<String> generateUniquePermutations(String input) {
        List<String> result = new ArrayList<>();
        if (input == null) {
            return result;
        }

        char[] chars = input.toCharArray();
        Arrays.sort(chars); // important: sort to detect duplicates

        backtrack(chars, new boolean[chars.length], new StringBuilder(), result);
        return result;
    }

    private static void backtrack(char[] chars, boolean[] used, StringBuilder current, List<String> result) {

        if (current.length() == chars.length) {
            result.add(current.toString());
            return;
        }

        for (int i = 0; i < chars.length; i++) {

            // skip duplicates
            if (i > 0 && chars[i] == chars[i - 1] && !used[i - 1]) {
                continue;
            }

            if (!used[i]) {
                used[i] = true;
                current.append(chars[i]);

                backtrack(chars, used, current, result);

                // undo changes
                used[i] = false;
                current.deleteCharAt(current.length() - 1);
            }
        }
    }
}
