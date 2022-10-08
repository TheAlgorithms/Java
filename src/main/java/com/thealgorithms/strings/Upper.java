package com.thealgorithms.strings;

public class Upper {

    /**
     * Driver Code
     */
    public static void main(String[] args) {
        String[] strings = { "ABC", "ABC123", "abcABC", "abc123ABC" };
        for (String s : strings) {
            assert toUpperCase(s).equals(s.toUpperCase());
        }
    }

    /**
     * Converts all of the characters in this {@code String} to upper case
     *
     * @param s the string to convert
     * @return the {@code String}, converted to uppercase.
     */
    public static String toUpperCase(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        char[] values = s.toCharArray();
        for (int i = 0; i < values.length; ++i) {
            if (
               values[i]>='a' &&
                values[i]<='z'
            ) {
                //using ascci values a-97 and A-65    //  97-65=32
                values[i] = (char)(values[i]-32);
            }
        }
        return new String(values);
    }
}
