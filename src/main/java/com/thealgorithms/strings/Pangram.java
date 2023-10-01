package com.thealgorithms.strings;

import java.util.Collections;
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
        // an approach using Java Collection Framework
        Set<Character> alpha = new HashSet<Character>();
        s = s.trim().toLowerCase();
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i)!=' ') alpha.add(s.charAt(i));
        if (alpha.size() == 26) return true;
        return false;
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
