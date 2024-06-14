package com.thealgorithms.strings.zigZagPattern;

final class ZigZagPattern {
    private ZigZagPattern() {
    }

    public static String encode(String s, int numRows) {
        if (numRows < 2 || s.length() < numRows) {
            return s;
        }
        int start = 0;
        int index = 0;
        int height = 1;
        int depth = numRows;
        char[] zigZagedArray = new char[s.length()];
        while (depth != 0) {
            int pointer = start;
            int heightSpace = 2 + ((height - 2) * 2);
            int depthSpace = 2 + ((depth - 2) * 2);
            boolean bool = true;
            while (pointer < s.length()) {
                zigZagedArray[index++] = s.charAt(pointer);
                if (heightSpace == 0) {
                    pointer += depthSpace;
                } else if (depthSpace == 0) {
                    pointer += heightSpace;
                } else if (bool) {
                    pointer += depthSpace;
                    bool = false;
                } else {
                    pointer += heightSpace;
                    bool = true;
                }
            }
            height++;
            depth--;
            start++;
        }
        return new String(zigZagedArray);
    }
}
