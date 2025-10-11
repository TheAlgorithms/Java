package com.thealgorithms.strings;

/**
 * Algorithm to remove stars from a string along with the closest non-star character to the left.
 *
 * You are given a string s, which contains stars *.
 * In one operation, you can:
 * - Choose a star in s.
 * - Remove the closest non-star character to its left, as well as remove the star itself.
 *
 * Example:
 * Input: s = "leet**cod*e"
 * Output: "lecoe"
 * Explanation:
 * - We remove the first star and the 't' to its left, resulting in "lee*cod*e"
 * - We remove the second star and the 'e' to its left, resulting in "lecod*e"
 * - We remove the third star and the 'd' to its left, resulting in "lecoe"
 *
 * @see <a href="https://leetcode.com/problems/removing-stars-from-a-string/">LeetCode Problem</a>
 */
public final class RemovingStarsFromString {
    private RemovingStarsFromString() {
    }

    /**
     * Removes all stars from the string along with their closest non-star character to the left.
     *
     * @param s The input string containing stars
     * @return The resulting string after all stars have been removed
     */
    public static String removeStars(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        StringBuilder result = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == '*') {
                // Remove the last character (closest non-star character to the left)
                if (result.length() > 0) {
                    result.deleteCharAt(result.length() - 1);
                }
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}
