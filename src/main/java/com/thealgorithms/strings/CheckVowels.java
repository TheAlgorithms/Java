package com.thealgorithms.strings;

import java.util.Set;

/**
  * Utility class to check whether a given string contains any vowels.
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
