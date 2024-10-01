package com.thealgorithms.strings;

import java.util.Set;

public final class CheckVowels {

    private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

    private CheckVowels() {
        // Private constructor to prevent instantiation
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

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (VOWELS.contains(c)) {
                return true;
            }
        }
        return false;
    }
}
