package com.thealgorithms.strings;

public final class CountChar {
    private CountChar() {
    }

    /**
     * Counts the number of non-whitespace characters in the given string.
     *
     * @param str the input string to count the characters in
     * @return the number of non-whitespace characters in the specified string;
     *         returns 0 if the input string is null
     */
    public static int countCharacters(String str) {
        if (str == null) {
            return 0;
        }
        return str.replaceAll("\\s", "").length();
    }
}
