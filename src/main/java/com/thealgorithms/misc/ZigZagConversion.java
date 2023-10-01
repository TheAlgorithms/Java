package com.thealgorithms.misc;

/**
 * How the Zigzag Conversion Works:
 * --------------------------------
 * How It Works:
 * - If numRows is 1 or greater than or equal to the string's length, the original string is returned.
 * - The input string is processed character by character and arranged in a zigzag pattern.
 * - The result is obtained by appending the rows and returning the converted string.
 *
 * This code is used to convert strings into a zigzag pattern, often used in the context of encoding text for
 * display in graphical user interfaces or for solving certain coding interview problems.
 *
 * For more information - https://codewithgeeks.com/zigzag-conversion-leetcode-solution/
 */

public class ZigZagConversion {
    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;
        boolean goingDown = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            rows[currentRow].append(c);
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }
            currentRow += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row.toString());
        }

        return result.toString();
    }
}
