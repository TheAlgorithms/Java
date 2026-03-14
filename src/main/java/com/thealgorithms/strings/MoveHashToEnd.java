package com.thealgorithms.strings;

/**
 * Moves all '#' characters to the end of the given string while preserving
 * the order of the other characters.
 *
 * Example:
 * Input  : "h#e#l#llo"
 * Output : "helllo###"
 *
 * The algorithm works by iterating through the string and collecting
 * all non-# characters first, then filling the remaining positions
 * with '#'.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * @see <a href="https://www.geeksforgeeks.org/move-special-char-end-string-maintain-order-alphabets/">Move all special characters to end - GeeksForGeeks</a>
 */
public final class MoveHashToEnd {

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private MoveHashToEnd() {
    }

    /**
     * Moves all '#' characters in the input string to the end.
     *
     * @param str the input string containing characters and '#'
     * @return a new string with all '#' characters moved to the end
     */
    public static String moveHashToEnd(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        char[] arr = str.toCharArray();
        int insertPos = 0;

        // Place all non-# characters at the beginning
        for (char ch : arr) {
            if (ch != '#') {
                arr[insertPos++] = ch;
            }
        }

        // Fill remaining positions with '#'
        while (insertPos < arr.length) {
            arr[insertPos++] = '#';
        }

        return new String(arr);
    }
}
