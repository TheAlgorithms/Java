package com.thealgorithms.strings;

import java.util.HashSet;
import java.util.Set;

/**
 * This class provides methods for generating all permutations of a given string using a backtracking algorithm.
 * <p>
 * The algorithm works as follows:
 * <ol>
 *     <li>Fix a character in the current position and swap it with each of the remaining characters.
 *         For example, for the string "ABC":
 *         <ul>
 *             <li>Fix 'A' at the first position: permutations are "ABC", "BAC", "CBA" (obtained by swapping 'A' with 'B' and 'C' respectively).</li>
 *         </ul>
 *     </li>
 *     <li>Repeat the process for the next character.
 *         For instance, after fixing 'B' in the second position:
 *         <ul>
 *             <li>For "BAC", the permutations include "BAC" and "BCA" (after swapping 'A' and 'C').</li>
 *         </ul>
 *     </li>
 *     <li>After generating permutations for the current position, backtrack by swapping the characters back to their original positions to restore the state.
 *         For example, after generating permutations for "ABC", swap back to restore "BAC" and continue with further permutations.</li>
 *     <li>Repeat the process for all characters to get all possible permutations.</li>
 * </ol>
 * </p>
 */
public final class PermuteString {
    private PermuteString() {
    }

    /**
     * Generates all possible permutations of the given string.
     *
     * <p>This method returns a set containing all unique permutations of the input string. It leverages
     * a recursive helper method to generate these permutations.
     *
     * @param str The input string for which permutations are to be generated.
     *            If the string is null or empty, the result will be an empty set.
     * @return A {@link Set} of strings containing all unique permutations of the input string.
     *         If the input string has duplicate characters, the set will ensure that only unique permutations
     *         are returned.
     */
    public static Set<String> getPermutations(String str) {
        Set<String> permutations = new HashSet<>();
        generatePermutations(str, 0, str.length(), permutations);
        return permutations;
    }

    /**
     * Generates all permutations of the given string and collects them into a set.
     *
     * @param str the string to permute
     * @param start the starting index for the current permutation
     * @param end the end index (length of the string)
     * @param permutations the set to collect all unique permutations
     */
    private static void generatePermutations(String str, int start, int end, Set<String> permutations) {
        if (start == end - 1) {
            permutations.add(str);
        } else {
            for (int currentIndex = start; currentIndex < end; currentIndex++) {
                // Swap the current character with the character at the start index
                str = swapCharacters(str, start, currentIndex);
                // Recursively generate permutations for the remaining characters
                generatePermutations(str, start + 1, end, permutations);
                // Backtrack: swap the characters back to their original positions
                str = swapCharacters(str, start, currentIndex);
            }
        }
    }

    /**
     * Swaps the characters at the specified positions in the given string.
     *
     * @param str the string in which characters will be swapped
     * @param i the position of the first character to swap
     * @param j the position of the second character to swap
     * @return a new string with the characters at positions i and j swapped
     */
    private static String swapCharacters(String str, int i, int j) {
        char[] chars = str.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }
}
