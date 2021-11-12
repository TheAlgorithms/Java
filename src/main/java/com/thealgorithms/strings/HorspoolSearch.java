package com.thealgorithms.strings;

import java.util.HashMap;

/**
 * This class is not thread safe<br>
 * <br>
 * (From wikipedia) In computer science, the Boyer–Moore–Horspool algorithm or
 * Horspool's algorithm is an algorithm for finding substrings in strings. It
 * was published by Nigel Horspool in 1980.
 * <br>
 * <a href=https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore%E2%80%93Horspool_algorithm>Wikipedia
 * page</a><br>
 * <br>
 *
 * <p>
 * An explanation:<br>
 *
 * <p>
 * The Horspool algorithm is a simplification of the Boyer-Moore algorithm in
 * that it uses only one of the two heuristic methods for increasing the number
 * of characters shifted when finding a bad match in the text. This method is
 * usually called the "bad symbol" or "bad character" shift. The bad symbol
 * shift method is classified as an input enhancement method in the theory of
 * algorithms. Input enhancement is (from wikipedia) the principle that
 * processing a given input to a problem and altering it in a specific way will
 * increase runtime efficiency or space efficiency, or both. Both algorithms try
 * to match the pattern and text comparing the pattern symbols to the text's
 * from right to left.<br>
 * <br>
 *
 * <p>
 * In the bad symbol shift method, a table is created prior to the search,
 * called the "bad symbol table". The bad symbol table contains the shift values
 * for any symbol in the text and pattern. For these symbols, the value is the
 * length of the pattern, if the symbol is not in the first (length - 1) of the
 * pattern. Else it is the distance from its rightmost occurrence in the pattern
 * to the last symbol of the pattern. In practice, we only calculate the values
 * for the ones that exist in the first (length - 1) of the pattern.<br>
 * <br>
 *
 * <p>
 * For more details on the algorithm and the more advanced Boyer-Moore I
 * recommend checking out the wikipedia page and professor Anany Levitin's book:
 * Introduction To The Design And Analysis Of Algorithms.
 */
public class HorspoolSearch {

    private static HashMap<Character, Integer> shiftValues; // bad symbol table
    private static Integer patternLength;
    private static int comparisons = 0; // total comparisons in the current/last search

    /**
     * Case sensitive version version of the algorithm
     *
     * @param pattern the pattern to be searched for (needle)
     * @param text the text being searched in (haystack)
     * @return -1 if not found or first index of the pattern in the text
     */
    public static int findFirst(String pattern, String text) {
        return firstOccurrence(pattern, text, true);
    }

    /**
     * Case insensitive version version of the algorithm
     *
     * @param pattern the pattern to be searched for (needle)
     * @param text the text being searched in (haystack)
     * @return -1 if not found or first index of the pattern in the text
     */
    public static int findFirstInsensitive(String pattern, String text) {
        return firstOccurrence(pattern, text, false);
    }

    /**
     * Utility method that returns comparisons made by last run (mainly for
     * tests)
     *
     * @return number of character comparisons of the last search
     */
    public static Integer getLastComparisons() {
        return HorspoolSearch.comparisons;
    }

    /**
     * Fairly standard implementation of the Horspool algorithm. Only the index
     * of the last character of the pattern on the text is saved and shifted by
     * the appropriate amount when a mismatch is found. The algorithm stops at
     * the first match or when the entire text has been exhausted.
     *
     * @param pattern String to be matched in the text
     * @param text text String
     * @return index of first occurrence of the pattern in the text
     */
    private static int firstOccurrence(String pattern, String text, boolean caseSensitive) {
        shiftValues = calcShiftValues(pattern); // build the bad symbol table
        comparisons = 0; // reset comparisons

        int textIndex
                = pattern.length() - 1; // align pattern with text start and get index of the last character

        // while pattern is not out of text bounds
        while (textIndex < text.length()) {

            // try to match pattern with current part of the text starting from last character
            int i = pattern.length() - 1;
            while (i >= 0) {
                comparisons++;
                char patternChar = pattern.charAt(i);
                char textChar = text.charAt((textIndex + i) - (pattern.length() - 1));
                if (!charEquals(patternChar, textChar, caseSensitive)) { // bad character, shift pattern
                    textIndex += getShiftValue(text.charAt(textIndex));
                    break;
                }
                i--;
            }

            // check for full match
            if (i == -1) {
                return textIndex - pattern.length() + 1;
            }
        }

        // text exhausted, return failure
        return -1;
    }

    /**
     * Compares the argument characters
     *
     * @param c1 first character
     * @param c2 second character
     * @param caseSensitive boolean determining case sensitivity of comparison
     * @return truth value of the equality comparison
     */
    private static boolean charEquals(char c1, char c2, boolean caseSensitive) {
        if (caseSensitive) {
            return c1 == c2;
        }
        return Character.toLowerCase(c1) == Character.toLowerCase(c2);
    }

    /**
     * Builds the bad symbol table required to run the algorithm. The method
     * starts from the second to last character of the pattern and moves to the
     * left. When it meets a new character, it is by definition its rightmost
     * occurrence and therefore puts the distance from the current index to the
     * index of the last character into the table. If the character is already
     * in the table, then it is not a rightmost occurrence, so it continues.
     *
     * @param pattern basis for the bad symbol table
     * @return the bad symbol table
     */
    private static HashMap<Character, Integer> calcShiftValues(String pattern) {
        patternLength = pattern.length();
        HashMap<Character, Integer> table = new HashMap<>();

        for (int i = pattern.length() - 2;
                i >= 0;
                i--) { // length - 2 is the index of the second to last character
            char c = pattern.charAt(i);
            int finalI = i;
            table.computeIfAbsent(c, k -> pattern.length() - 1 - finalI);
        }

        return table;
    }

    /**
     * Helper function that uses the bad symbol shift table to return the
     * appropriate shift value for a given character
     *
     * @param c character
     * @return shift value that corresponds to the character argument
     */
    private static Integer getShiftValue(char c) {
        if (shiftValues.get(c) != null) {
            return shiftValues.get(c);
        } else {
            return patternLength;
        }
    }
}
