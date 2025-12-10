package com.thealgorithms.strings;

import java.util.Arrays;

/**
 * Utility class for string operations.
 * <p>
 * This class provides a method to find the longest common prefix (LCP)
 * among an array of strings.
 * </p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Longest_common_prefix">Longest Common Prefix - Wikipedia</a>
 */
public final class LongestCommonPrefix {

    private LongestCommonPrefix() {
    }

    /**
     * Finds the longest common prefix among a list of strings using lexicographical sorting.
     * The prefix is common to the first and last elements after sorting the array.
     *
     * @param strs array of input strings
     * @return the longest common prefix, or empty string if none exists
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length - 1];

        int index = 0;
        while (index < first.length() && index < last.length() && first.charAt(index) == last.charAt(index)) {
            index++;
        }

        return first.substring(0, index);
    }
}
