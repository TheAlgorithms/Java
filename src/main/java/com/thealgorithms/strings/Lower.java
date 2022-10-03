package com.thealgorithms.strings;

public class Lower {

    /**
     * Driver Code
     */
    public static void main(String[] args) {
        String[] strings = { "ABC", "ABC123", "abcABC", "abc123ABC" };
        for (String s : strings) {
            assert toLowerCase(s).equals(s.toLowerCase());
        }
    }

    /**
     * Converts all of the characters in this {@code String} to lower case
     *
     * @param s the string to convert
     * @return the {@code String}, converted to lowercase.
     */
    public static String toLowerCase(String s) {
        char[] values = s.toCharArray();
        for (int i = 0; i < values.length; ++i) {
            if (
                Character.isLetter(values[i]) &&
                Character.isUpperCase(values[i])
            ) {
                values[i] = Character.toLowerCase(values[i]);
            }
        }
        return new String(values);
    }
}
