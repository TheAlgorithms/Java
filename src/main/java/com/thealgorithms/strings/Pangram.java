package com.thealgorithms.strings;

/**
 * Wikipedia: https://en.wikipedia.org/wiki/Pangram
 */
public class Pangram {

    /**
     * Driver Code
     */
    public static void main(String[] args) {
        assert isPangram("The quick brown fox jumps over the lazy dog");
        assert !isPangram("The quick brown fox jumps over the azy dog");
        /* not exists l character */
    }

    /**
     * Check if a string is a pangram string or not
     *
     * @param s string to check
     * @return {@code true} if given string is pangram, otherwise {@code false}
     */
    public static boolean isPangram(String s) {
        boolean[] marked = new boolean[26];
        /* by default all letters don't exists */
        char[] values = s.toCharArray();
        for (char value : values) {
            if (Character.isLetter(value)) {
                int index = Character.isUpperCase(value) ? value - 'A' : value - 'a';
                marked[index] = true;
                /* mark current character exists */
            }
        }

        for (boolean b : marked) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
}
