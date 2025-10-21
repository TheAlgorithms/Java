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
     * @throws IllegalArgumentException if {@code s} is null
     */
    public static String toUpperCase(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }
        if (s.isEmpty()) {
            return s;
        }

        // Check if any lowercase letter exists before creating a new String
        boolean hasLower = false;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                hasLower = true;
                break;
            }
        }

        // If no lowercase characters, return the same string
        if (!hasLower) {
            return s;
        }

        // Convert lowercase letters to uppercase
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isLowerCase(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
            }
        }

        return new String(chars);
    }
}
