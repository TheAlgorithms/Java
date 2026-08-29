package com.thealgorithms.strings;

/**
 * This class provides a method to arrange two strings by alternating their characters.
 * If one string is longer, the remaining characters of the longer string are appended at the end.
 * <p>
 * Example:
 * Input: "abc", "12345"
 * Output: "a1b2c345"
 * <p>
 * Input: "abcd", "12"
 * Output: "a1b2cd"
 *
 * @author Milad Sadeghi
 */
public final class AlternativeStringArrange {

    // Private constructor to prevent instantiation
    private AlternativeStringArrange() {
    }

    /**
     * Arranges two strings by alternating their characters.
     * If one string is longer than the other, the remaining characters of the longer string
     * are appended at the end of the result.
     *
     * @param firstString  the first input string, must not be {@code null}
     * @param secondString the second input string, must not be {@code null}
     * @return a new string with characters from both strings arranged alternately
     * @throws IllegalArgumentException if {@code firstString} or {@code secondString} is {@code null}
     */
    public static String arrange(String firstString, String secondString) {
        if (firstString == null || secondString == null) {
            throw new IllegalArgumentException("Input strings must not be null");
        }

        StringBuilder result = new StringBuilder();
        int length1 = firstString.length();
        int length2 = secondString.length();
        int minLength = Math.min(length1, length2);

        for (int i = 0; i < minLength; i++) {
            result.append(firstString.charAt(i));
            result.append(secondString.charAt(i));
        }

        if (length1 > length2) {
            result.append(firstString.substring(minLength));
        } else if (length2 > length1) {
            result.append(secondString.substring(minLength));
        }

        return result.toString();
    }
}
