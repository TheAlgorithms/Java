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

        char[] result = new char[s.length()];
        int index = 0;

        // Cycle length for zigzag traversal
        int cycleLength = 2 * numRows - 2;

        for (int row = 0; row < numRows; row++) {
            for (int j = row; j < s.length(); j += cycleLength) {
                result[index++] = s.charAt(j);

                int diagonal = j + cycleLength - 2 * row;
                if (row > 0 && row < numRows - 1 && diagonal < s.length()) {
                    result[index++] = s.charAt(diagonal);
                }
            }
        }

        return new String(result);
    }
}
