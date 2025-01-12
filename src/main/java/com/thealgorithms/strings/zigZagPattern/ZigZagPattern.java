package com.thealgorithms.strings.zigZagPattern;

final class ZigZagPattern {

    private ZigZagPattern() {
    }

    /**
     * Encodes a given string into a zig-zag pattern.
     *
     * @param s       the input string to be encoded
     * @param numRows the number of rows in the zig-zag pattern
     * @return the encoded string in zig-zag pattern format
     */
    public static String encode(String s, int numRows) {
        if (numRows < 2 || s.length() < numRows) {
            return s;
        }

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int index = 0;
        while (index < s.length()) {
            for (int i = 0; i < numRows && index < s.length(); i++) {
                rows[i].append(s.charAt(index));
                index++;
            }
            for (int i = numRows - 2; i >= 1 && index < s.length(); i--) {
                rows[i].append(s.charAt(index));
                index++;
            }
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }
}
