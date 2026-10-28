package com.thealgorithms.strings;

public final class Upper {
    private Upper() {
    }

    /**
     * Driver Code
     */
    public static void main(String[] args) {
        String[] strings = {"ABC", "ABC123", "abcABC", "abc123ABC"};
        for (String s : strings) {
            assert toUpperCase(s).equals(s.toUpperCase());
        }
    }

    /**
     * Converts all the characters in this {@code String} to upper case.
     *
     * @param s the string to convert
     * @return the {@code String}, converted to uppercase.
     */
    public static String toUpperCase(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }
        if (s.isEmpty()) {
            return s;
        }

        StringBuilder result = new StringBuilder(s.length());

        for (int i = 0; i < s.length(); ++i) {
            char currentChar = s.charAt(i);
            if (Character.isLowerCase(currentChar)) {
                result.append(Character.toUpperCase(currentChar));
            } else {
                result.append(currentChar);
            }
        }
        return result.toString();
    }
}
