package com.thealgorithms.strings.zigZagPattern;

class zigZagPattern {

    public static String encode(String s, int numRows) {
        if (numRows < 2 || s.length() < numRows) return s;
        int start = 0, index = 0, height = 1, depth = numRows;
        char[] zigZagedArray = new char[s.length()];
        while (depth != 0) {
            int pointer = start, height_space = 2 + ((height - 2) * 2), depth_space = 2 + ((depth - 2) * 2);
            boolean bool = true;
            while (pointer < s.length()) {
                zigZagedArray[index++] = s.charAt(pointer);
                if (height_space == 0)
                    pointer += depth_space;
                else if (depth_space == 0)
                    pointer += height_space;
                else if (bool) {
                    pointer += depth_space;
                    bool = false;
                } else {
                    pointer += height_space;
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
