package com.thealgorithms.strings;

/**
 * Wikipedia: https://en.wikipedia.org/wiki/Pangram
 */
public class Pangram {

    /**
     * Test code
     */
    public static void main(String[] args) {
        assert isPangram("The quick brown fox jumps over the lazy dog");
        assert !isPangram("The quick brown fox jumps over the azy dog"); // L is missing
    }

    /**
     * Checks if a String is considered a Pangram
     *
     * @param s The String to check
     * @return {@code true} if s is a Pangram, otherwise {@code false}
     */
    public static boolean isPangram(String s) {
        boolean[] lettersExisting = new boolean[26];
        for (char c : s.toCharArray()) {
            int letterIndex = c - (Character.isUpperCase(c) ? 'A' : 'a');
            if (letterIndex >= 0 && letterIndex < lettersExisting.length) {
                lettersExisting[letterIndex] = true;
            }
        }
        for (boolean letterFlag : lettersExisting) {
            if (!letterFlag) {
                return false;
            }
        }
        return true;
    }
}
