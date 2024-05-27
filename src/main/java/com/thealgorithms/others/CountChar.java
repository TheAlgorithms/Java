package com.thealgorithms.others;

public final class CountChar {
    private CountChar() {
    }

    /**
     * Count non space character in string
     *
     * @param str String to count the characters
     * @return number of character in the specified string
     */

    public static int countCharacters(String str) {
        return str.replaceAll("\\s", "").length();
    }
}
