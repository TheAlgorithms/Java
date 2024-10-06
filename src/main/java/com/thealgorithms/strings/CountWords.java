package com.thealgorithms.strings;

/**
 * @author Marcus
 */
public final class CountWords {
    private CountWords() {
    }

    /**
     * Counts the number of words in the input string. Words are defined as sequences of
     * characters separated by whitespace.
     *
     * @param s the input string
     * @return the number of words in the input string, or 0 if the string is null or empty
     */
    public static int wordCount(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        return s.trim().split("\\s+").length;
    }

    /**
     * Removes all special characters from the input string, leaving only alphanumeric characters
     * and whitespace.
     *
     * @param s the input string
     * @return a string containing only alphanumeric characters and whitespace
     */
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
     * Counts the number of words in a sentence, ignoring all non-alphanumeric characters that do
     * not contribute to word formation. This method has a time complexity of O(n), where n is the
     * length of the input string.
     *
     * @param s the input string
     * @return the number of words in the input string, with special characters removed, or 0 if the string is null
     */
    public static int secondaryWordCount(String s) {
        if (s == null) {
            return 0;
        }
        return wordCount(removeSpecialCharacters(s));
    }
}
