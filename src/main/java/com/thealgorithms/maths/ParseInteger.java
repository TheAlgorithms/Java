package com.thealgorithms.maths;

public class ParseInteger {

    public static void main(String[] args) {
        assert parseInt("123") == Integer.parseInt("123");
        assert parseInt("-123") == Integer.parseInt("-123");
        assert parseInt("0123") == Integer.parseInt("0123");
        assert parseInt("+123") == Integer.parseInt("+123");
    }

    /**
     * Parse a string to integer
     *
     * @param s the string
     * @return the integer value represented by the argument in decimal.
     * @throws NumberFormatException if the {@code string} does not contain a
     * parsable integer.
     */
    public static int parseInt(String s) {
        if (s == null || s.length() == 0) {
            throw new NumberFormatException("null");
        }
        boolean isNegative = s.charAt(0) == '-';
        boolean isPositive = s.charAt(0) == '+';
        int number = 0;
        for (
            int i = isNegative ? 1 : isPositive ? 1 : 0, length = s.length();
            i < length;
            ++i
        ) {
            if (!Character.isDigit(s.charAt(i))) {
                throw new NumberFormatException("s=" + s);
            }
            number = number * 10 + s.charAt(i) - '0';
        }
        return isNegative ? -number : number;
    }
}
