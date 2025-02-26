package com.thealgorithms.strings;

/**
 * Reverse String using Recursion
 */
public final class ReverseStringRecursive {
    private ReverseStringRecursive() {
    }

    /**
     * @param str string to be reversed
     * @return reversed string
     */
    public static String reverse(String str) {
        if (str.isEmpty()) {
            return str;
        } else {
            return reverse(str.substring(1)) + str.charAt(0);
        }
    }

    /**
     * Reverses the given string using a StringBuilder.
     * This method converts the string to a character array,
     * iterates through it in reverse order, and appends each character
     * to a StringBuilder.
     *
     * @param string The input string to be reversed.
     * @return The reversed string.
     */
    public static String reverseUsingStringBuilder(String string){
        if (string.isEmpty()){
            return string;
        }
        char[] chars = string.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = string.length() -1; i >= 0; i--){
            sb.append(chars[i]);
        }
        return sb.toString();
    }
}
