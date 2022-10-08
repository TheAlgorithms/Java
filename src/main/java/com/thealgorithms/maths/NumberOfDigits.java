package com.thealgorithms.maths;

/**
 * Find the number of digits in a number.
 */
public class NumberOfDigits {

    public static void main(String[] args) {
        int[] numbers = {
            0,
            12,
            123,
            1234,
            -12345,
            123456,
            1234567,
            12345678,
            123456789,
        };
        for (int i = 0; i < numbers.length; ++i) {
            assert numberOfDigits(numbers[i]) == i + 1;
            assert numberOfDigitsFast(numbers[i]) == i + 1;
            assert numberOfDigitsFaster(numbers[i]) == i + 1;
            assert numberOfDigitsRecursion(numbers[i]) == i + 1;
        }
    }

    /**
     * Find the number of digits in a number.
     *
     * @param number number to find
     * @return number of digits of given number
     */
    private static int numberOfDigits(int number) {
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
    private static int numberOfDigitsFast(int number) {
        return number == 0
            ? 1
            : (int) Math.floor(Math.log10(Math.abs(number)) + 1);
    }

    /**
     * Find the number of digits in a number faster version.
     *
     * @param number number to find
     * @return number of digits of given number
     */
    private static int numberOfDigitsFaster(int number) {
        return number < 0 ? (-number + "").length() : (number + "").length();
    }

    /**
     * Find the number of digits in a number using recursion.
     *
     * @param number number to find
     * @return number of digits of given number
     */
    private static int numberOfDigitsRecursion(int number) {
        return number / 10 == 0 ? 1 : 1 + numberOfDigitsRecursion(number / 10);
    }
}
