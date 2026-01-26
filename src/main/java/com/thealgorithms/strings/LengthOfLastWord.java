package com.thealgorithms.strings;

/**
 * The {@code LengthOfLastWord} class provides a utility method to determine
 * the length of the last word in a given string.
 *
 * <p>A "word" is defined as a maximal substring consisting of non-space
 * characters only. Trailing spaces at the end of the string are ignored.
 *
 * <p><strong>Example:</strong>
 * <pre>{@code
 * LengthOfLastWord obj = new LengthOfLastWord();
 * System.out.println(obj.lengthOfLastWord("Hello World"));  // Output: 5
 * System.out.println(obj.lengthOfLastWord("  fly me   to   the moon  "));  // Output: 4
 * System.out.println(obj.lengthOfLastWord("luffy is still joyboy"));  // Output: 6
 * }</pre>
 *
 * <p>This implementation runs in O(n) time complexity, where n is the length
 * of the input string, and uses O(1) additional space.
 */
public class LengthOfLastWord {

    /**
     * Returns the length of the last word in the specified string.
     *
     * <p>The method iterates from the end of the string, skipping trailing
     * spaces first, and then counts the number of consecutive non-space characters
     * characters until another space (or the beginning of the string) is reached.
     *
     * @param s the input string to analyze
     * @return the length of the last word in {@code s}; returns 0 if there is no word
     * @throws NullPointerException if {@code s} is {@code null}
     */
    public int lengthOfLastWord(String s) {
        int sizeOfString = s.length() - 1;
        int lastWordLength = 0;

        // Skip trailing spaces from the end of the string
        while (sizeOfString >= 0 && s.charAt(sizeOfString) == ' ') {
            sizeOfString--;
        }

        // Count the characters of the last word
        while (sizeOfString >= 0 && s.charAt(sizeOfString) != ' ') {
            lastWordLength++;
            sizeOfString--;
        }

        return lastWordLength;
    }
}
