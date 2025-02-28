package com.thealgorithms.strings;

/**
 * Reverse String using different version
 */
public final class ReverseString {
    private ReverseString() {
    }

    /**
     * easiest way to reverses the string str and returns it
     *
     * @param str string to be reversed
     * @return reversed string
     */
    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * second way to reverses the string str and returns it
     *
     * @param str string to be reversed
     * @return reversed string
     */
    public static String reverse2(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        char[] value = str.toCharArray();
        for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
            char temp = value[i];
            value[i] = value[j];
            value[j] = temp;
        }
        return new String(value);
    }

    /**
     * Reverse version 3 the given string using a StringBuilder.
     * This method converts the string to a character array,
     * iterates through it in reverse order, and appends each character
     * to a StringBuilder.
     *
     * @param string The input string to be reversed.
     * @return The reversed string.
     */
    public static String reverse3(String string) {
        if (string.isEmpty()) {
            return string;
        }
        char[] chars = string.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = string.length() - 1; i >= 0; i--) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }
}
