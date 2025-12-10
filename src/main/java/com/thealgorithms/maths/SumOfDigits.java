package com.thealgorithms.maths;

public final class SumOfDigits {
    private SumOfDigits() {
    }

    /**
     * Calculate the sum of digits of a number
     *
     * @param number the number contains digits
     * @return sum of digits of given {@code number}
     */
    public static int sumOfDigits(int number) {
        final int base = 10;
        number = Math.abs(number);
        int sum = 0;
        while (number != 0) {
            sum += number % base;
            number /= base;
        }
        return sum;
    }

    /**
     * Calculate the sum of digits of a number using recursion
     *
     * @param number the number contains digits
     * @return sum of digits of given {@code number}
     */
    public static int sumOfDigitsRecursion(int number) {
        final int base = 10;
        number = Math.abs(number);
        return number < base ? number : number % base + sumOfDigitsRecursion(number / base);
    }

    /**
     * Calculate the sum of digits of a number using char array
     *
     * @param number the number contains digits
     * @return sum of digits of given {@code number}
     */
    public static int sumOfDigitsFast(final int number) {
        return String.valueOf(Math.abs(number)).chars().map(c -> c - '0').reduce(0, Integer::sum);
    }
}
