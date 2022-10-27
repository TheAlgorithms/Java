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
        assert !isPangram("+-1234 This string is not alphabetical");
        assert !isPangram("\u0000/\\");
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
    
    /**
     * Checks if a String is Pangram or not by checking if each alhpabet is present or not
     *
     * @param s The String to check
     * @return {@code true} if s is a Pangram, otherwise {@code false}
     */
    public static boolean isPangram2(String s) {
        if (s.length() < 26) {
            return false;
        }
        s = s.toLowerCase(); // Converting s to Lower-Case
        for (char i = 'a'; i <= 'z'; i++) {
            if (s.indexOf(i) == -1) {
                return false; // if any alphabet is not present, return false
            }
        }
        return true;
    }
}
