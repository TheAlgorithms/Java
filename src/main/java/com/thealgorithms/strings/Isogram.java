package com.thealgorithms.strings;

import java.util.HashSet;
import java.util.Set;

/**
 * An isogram (also called heterogram or nonpattern word) is a word in which no
 * letter of the word occurs more than once. Each character appears exactly
 * once.
 *
 * For example, the word "uncopyrightable" is the longest common English isogram
 * with 15 unique letters. Other examples include "dermatoglyphics" (15
 * letters),
 * "background" (10 letters), "python" (6 letters), and "keyboard" (8 letters).
 * But words like "hello" and "programming" are not isograms because some
 * letters
 * appear multiple times ('l' appears twice in "hello", while 'r', 'm', 'g'
 * repeat
 * in "programming").
 *
 * Isograms are particularly valuable in creating substitution ciphers and are
 * studied in recreational linguistics. A perfect pangram, which uses all 26
 * letters
 * of the alphabet exactly once, is a special type of isogram.
 *
 * Reference from https://en.wikipedia.org/wiki/Heterogram_(literature)#Isograms
 */
public final class Isogram {
    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private Isogram() {
    }

    /**
     * Checks if a string is an isogram using boolean array approach.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param str the input string
     * @return true if the string is an isogram, false otherwise
     * @throws IllegalArgumentException if the string contains non-alphabetic
     *                                  characters
     */
    public static boolean isAlphabeticIsogram(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }

        str = str.toLowerCase();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch < 'a' || ch > 'z') {
                throw new IllegalArgumentException("Input contains non-alphabetic character: '" + ch + "'");
            }
        }

        boolean[] seenChars = new boolean[26];
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int index = ch - 'a';
            if (seenChars[index]) {
                return false;
            }
            seenChars[index] = true;
        }
        return true;
    }

    /**
     * Checks if a string is an isogram using length comparison approach.
     * Time Complexity: O(n)
     * Space Complexity: O(k) where k is the number of unique characters
     *
     * @param str the input string
     * @return true if the string is an isogram, false otherwise
     */
    public static boolean isFullIsogram(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        str = str.toLowerCase();

        Set<Character> uniqueChars = new HashSet<>();
        for (char ch : str.toCharArray()) {
            uniqueChars.add(ch);
        }
        return uniqueChars.size() == str.length();
    }
}
