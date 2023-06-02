package com.thealgorithms.others;

import java.util.Scanner;

/**
 * @author Marcus
 */
final public class CountWords {
    private CountWords() {
    }

    /**
     * @brief counts the number of words in the input string
     * @param s the input string
     * @return the number of words in the input string
     */
    public static int wordCount(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        return s.trim().split("[\\s]+").length;
    }

    private static String removeSpecialCharacters(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c) || Character.isWhitespace(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * counts the number of words in a sentence but ignores all potential
     * non-alphanumeric characters that do not represent a word. runs in O(n)
     * where n is the length of s
     *
     * @param s String: sentence with word(s)
     * @return int: number of words
     */
    public static int secondaryWordCount(String s) {
        if (s == null) {
            return 0;
        }
        return wordCount(removeSpecialCharacters(s));
    }
}
