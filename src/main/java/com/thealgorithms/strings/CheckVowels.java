package com.thealgorithms.strings;

import java.util.Set;

/**
 * Vowel Count is a system whereby character strings are placed in order based
 * on the position of the characters in the conventional ordering of an
 * alphabet. Wikipedia: https://en.wikipedia.org/wiki/Alphabetical_order
 */
public final class CheckVowels {
    private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u');

    private CheckVowels() {
    }

    /**
     * Checks if a string contains any vowels.
     *
     * @param input a string to check
     * @return {@code true} if the given string contains at least one vowel, otherwise {@code false}
     */
    public static boolean hasVowels(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        for (char c : input.toLowerCase().toCharArray()) {
            if (VOWELS.contains(c)) {
                return true;
            }
        }
        return false;
    }
}
