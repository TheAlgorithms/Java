package com.thealgorithms.strings;

/**
 * Finds the extra character added to string t, which is string s with one
 * additional random character shuffled in.
 *
 * <p>Example: s = "abcd", t = "abcde" -> 'e'
 *
 * <p>Uses XOR to cancel out matching characters in O(n) time and O(1) space.
 *
 * @see <a href="https://leetcode.com/problems/find-the-difference/">LeetCode #389</a>
 */
public final class FindTheDifference {
    private FindTheDifference() {
    }

    public static char findTheDifference(String s, String t) {
        char result = 0;
        for (char c : s.toCharArray()) {
            result ^= c;
        }
        for (char c : t.toCharArray()) {
            result ^= c;
        }
        return result;
    }
}
