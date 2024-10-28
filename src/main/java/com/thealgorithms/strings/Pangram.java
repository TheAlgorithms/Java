package com.thealgorithms.strings;

import java.util.HashSet;

/**
 * Checks if a given string is a Pangram (a sentence containing every letter of the alphabet at least once).
 * Wikipedia: https://en.wikipedia.org/wiki/Pangram
 */
public final class Pangram {

    private Pangram() {
    }

    /**
     * Main method to test all pangram-checking methods.
     */
    public static void main(String[] args) {
        assert isPangram("The quick brown fox jumps over the lazy dog");
        assert !isPangram("The quick brown fox jumps over the azy dog"); // L is missing
        assert !isPangram("+-1234 This string is not alphabetical");
        assert !isPangram("\u0000/\\");
        assert isPangramUsingBitwise("The quick brown fox jumps over the lazy dog");
        assert !isPangramUsingBitwise("Hello, World!");
    }

    /**
     * Checks if a String is a Pangram using a HashSet to track unique characters.
     *
     * @param s The String to check
     * @return {@code true} if s is a Pangram, otherwise {@code false}
     */
    public static boolean isPangramUsingSet(String s) {
        HashSet<Character> uniqueChars = new HashSet<>();
        s = s.toLowerCase();
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                uniqueChars.add(c);
            }
        }
        return uniqueChars.size() == 26;
    }

    /**
     * Checks if a String is a Pangram by tracking occurrences of each letter in an array.
     *
     * @param s The String to check
     * @return {@code true} if s is a Pangram, otherwise {@code false}
     */
    public static boolean isPangram(String s) {
        boolean[] lettersExisting = new boolean[26];
        s = s.toLowerCase();
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                lettersExisting[c - 'a'] = true;
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
     * Checks if a String is a Pangram by checking each letter in the alphabet individually.
     *
     * @param s The String to check
     * @return {@code true} if s is a Pangram, otherwise {@code false}
     */
    public static boolean isPangram2(String s) {
        if (s.length() < 26) {
            return false;
        }
        s = s.toLowerCase();
        for (char i = 'a'; i <= 'z'; i++) {
            if (s.indexOf(i) == -1) {
                return false; // if any alphabet is missing, return false
            }
        }
        return true;
    }

    /**
     * Optimized Pangram check using Bitwise operations.
     * Each bit in a 32-bit integer represents a unique letter from 'a' to 'z'.
     *
     * @param s The String to check
     * @return {@code true} if s is a Pangram, otherwise {@code false}
     */
    public static boolean isPangramUsingBitwise(String s) {
        int checker = 0;
        s = s.toLowerCase();
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                int bitIndex = c - 'a';
                checker |= (1 << bitIndex);
            }
        }
        // If all 26 bits are set, checker will equal 0b11111111111111111111111111 (26 ones)
        return checker == (1 << 26) - 1;
    }
}
