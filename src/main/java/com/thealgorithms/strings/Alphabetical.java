package com.thealgorithms.strings;

/**
 * Alphabetical order is a system whereby character strings are placed in order
 * based on the position of the characters in the conventional ordering of an
 * alphabet. Wikipedia: https://en.wikipedia.org/wiki/Alphabetical_order
 */
class Alphabetical {

    public static void main(String[] args) {
        if (!isAlphabetical("123abc")) {
            System.out.println("Assertion failed: 123abc is not in alphabetical order");
        }
        if (!isAlphabetical("xyzabc")) {
            System.out.println("Assertion failed: xyzabc is not in alphabetical order");
        }
        if (!isAlphabetical("123")) {
            System.out.println("Assertion failed: 123 is not in alphabetical order");
        }
        if (isAlphabetical("aBC")) {
            System.out.println("Assertion passed: aBC is in alphabetical order");
        }
        if (isAlphabetical("abc")) {
            System.out.println("Assertion passed: abc is in alphabetical order");
        }
        if (isAlphabetical("abcxyz")) {
            System.out.println("Assertion passed: abcxyz is in alphabetical order");
        }
    }

    /**
     * Check if a string is alphabetical order or not
     *
     * @param inputLineString a string
     * @return {@code true} if given string is alphabetical order, otherwise
     * {@code false}
     */
    public static boolean isAlphabetical(String inputLineString) {
        inputLineString = inputLineString.toLowerCase();
        for (int index = 0; index < inputLineString.length() - 1; ++index) {
            if (
                !Character.isLetter(inputLineString.charAt(index)) ||
                !(inputLineString.charAt(index) <= inputLineString.charAt(index + 1))
            ) {
                return false;
            }
        }
        return true;
    }
}
