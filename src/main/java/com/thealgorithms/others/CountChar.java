package com.thealgorithms.others;

public class CountChar {

    /**
     * Count non space character in string
     *
     * @param str String to count the characters
     * @return number of character in the specified string
     */

    public static int CountCharacters(String str) {
        return str.replaceAll("\\s", "").length();
    }
}
