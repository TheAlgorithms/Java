package com.thealgorithms.maths;

/**
 * Find the number of digits in a number.
 */
public final class NumberOfDigits {
    private NumberOfDigits() {
    }
    /**
     * Find the number of digits in a number.
     *
     * @param number number to find
     * @return number of digits of given number
     */
    public static int numberOfDigits(int number) {
        int digits = 0;
        do {
            digits++;
            number /= 10;
        } while (number != 0);
        return digits;
    }

    /**
     * Find the number of digits in a number fast version.
     *
     * @param number number to find
     * @return number of digits of given number
     */
    public static int numberOfDigitsFast(int number) {
        return number == 0 ? 1 : (int) Math.floor(Math.log10(Math.abs(number)) + 1);
    }

    /**
     * Find the number of digits in a number faster version.
     *
     * @param number number to find
     * @return number of digits of given number
     */
    public static int numberOfDigitsFaster(int number) {
        return number < 0 ? (-number + "").length() : (number + "").length();
    }

    /**
     * Find the number of digits in a number using recursion.
     *
     * @param number number to find
     * @return number of digits of given number
     */
    public static int numberOfDigitsRecursion(int number) {
        return number / 10 == 0 ? 1 : 1 + numberOfDigitsRecursion(number / 10);
    }
}
