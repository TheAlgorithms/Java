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
     * Checks if a String is considered a Pangram by checking if each alhpabet is present or not
     *
     * @param s The String to check
     * @return {@code true} if s is a Pangram, otherwise {@code false}
     */
    public static boolean isPangram(String s) {
        if (s.length() < 26) {
            return false;
        }
        s = s.toLowerCase();
        for (char i = 'a'; i <= 'z'; i++) {
            if (s.indexOf(i) == -1) {
                return false;
            }
        }
        return true;
    }
}
