package com.thealgorithms.strings;

import java.util.Locale;

/**
 * Utility class for checking whether a string's characters are in non-decreasing
 * lexicographical order based on Unicode code points (case-insensitive).
 * <p>
 * This does NOT implement language-aware alphabetical ordering (collation rules).
 * It simply compares lowercase Unicode character values.
 * <p>
 * Non-letter characters are not allowed and will cause the check to fail.
 * <p>
 * Reference:
 * <a href="https://en.wikipedia.org/wiki/Alphabetical_order">Wikipedia: Alphabetical order</a>
 */
public final class Alphabetical {

    private Alphabetical() {
    }

    /**
     * Checks whether the characters in the given string are in non-decreasing
     * lexicographical order (case-insensitive).
     * <p>
     * Rules:
     * <ul>
     *   <li>String must not be null or blank</li>
     *   <li>All characters must be letters</li>
     *   <li>Comparison is based on lowercase Unicode values</li>
     *   <li>Order must be non-decreasing (equal or increasing allowed)</li>
     * </ul>
     *
     * @param s input string
     * @return {@code true} if characters are in non-decreasing order, otherwise {@code false}
     */
    public static boolean isAlphabetical(String s) {
        if (s == null || s.isBlank()) {
            return false;
        }

        String normalized = s.toLowerCase(Locale.ROOT);

        if (!Character.isLetter(normalized.charAt(0))) {
            return false;
        }

        for (int i = 1; i < normalized.length(); i++) {
            char prev = normalized.charAt(i - 1);
            char curr = normalized.charAt(i);

            if (!Character.isLetter(curr) || prev > curr) {
                return false;
            }
        }
        return true;
    }
}
