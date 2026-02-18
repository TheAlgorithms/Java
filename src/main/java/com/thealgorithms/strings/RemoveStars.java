package com.thealgorithms.strings;

/**
 * Removes characters affected by '*' in a string.
 * Each '*' deletes the closest non-star character to its left.
 *
 * Example:
 * Input: leet**cod*e
 * Output: lecoe
 */

public final class RemoveStars {

    private RemoveStars() {
        // Private constructor to prevent instantiation(object creation)
    }

    public static String removeStars(String s) {
        StringBuilder result = new StringBuilder();
         
        for (char c : s.toCharArray()) {
            if (c == '*') {
                if (result.length() > 0) {
                    result.deleteCharAt(result.length() - 1);
                }
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
    public static void main(String[] args) {
        String example = "leet**cod*e";
        System.out.println(removeStars(example));
    }
}
