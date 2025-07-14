package com.thealgorithms.strings;

/**
 * Utility class for checking if a string's characters are in alphabetical order.
 * <p>
 * Alphabetical order is a system whereby character strings are placed in order
 * based on the position of the characters in the conventional ordering of an
 * alphabet.
 * <p>
 * Reference: <a href="https://en.wikipedia.org/wiki/Alphabetical_order">Wikipedia: Alphabetical Order</a>
 */
public final class Alphabetical {
    private Alphabetical() {
    }

    /**
     * Checks whether the characters in the given string are in alphabetical order.
     * Non-letter characters will cause the check to fail.
     *
     * @param s the input string
     * @return {@code true} if all characters are in alphabetical order (case-insensitive), otherwise {@code false}
     */
    public static boolean isAlphabetical(String s) {
        s = s.toLowerCase();
        for (int i = 0; i < s.length() - 1; ++i) {
            if (!Character.isLetter(s.charAt(i)) || s.charAt(i) > s.charAt(i + 1)) {
                return false;
            }
        }
        return !s.isEmpty() && Character.isLetter(s.charAt(s.length() - 1));
    }
}
