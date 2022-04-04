package com.thealgorithms.strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Vowel Count is a system whereby character strings are placed in order based
 * on the position of the characters in the conventional ordering of an
 * alphabet. Wikipedia: https://en.wikipedia.org/wiki/Alphabetical_order
 */
public class CheckVowels {
    private static final Set<Character> VOWELS = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    /**
     * Check if a string is has vowels or not
     *
     * @param input a string
     * @return {@code true} if given string has vowels, otherwise {@code false}
     */
    public static boolean hasVowels(String input) {
        return countVowels(input) > 0;
    }

    /**
     * count the number of vowels
     *
     * @param input a string prints the count of vowels
     */
    public static int countVowels(String input) {
        if (input == null) {
            return 0;
        }
        int cnt = 0;
        for (char c : input.toLowerCase().toCharArray()) {
            if (VOWELS.contains(c)) {
                ++cnt;
            }
        }
        return cnt;
    }
}
