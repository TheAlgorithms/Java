package com.thealgorithms.maths;

/**
 * Pronic Number: A number n is a pronic number (or oblong number) if
 * it is equal to the product of two consecutive integers m and m + 1 (for m >= 0).
 * Wikipedia: https://en.wikipedia.org/wiki/Pronic_number
 *
 * @author Akshay Dubey (https://github.com/itsAkshayDubey)
 */
public final class PronicNumber {
    private PronicNumber() {
    }

    /**
     * Checks if the given number is a pronic number iteratively.
     *
     * @param inputNumber integer value to be checked
     * @return true if inputNumber is a pronic number, false otherwise
     */
    public static boolean isPronic(int inputNumber) {
        if (inputNumber < 0) {
            return false;
        }
        for (int i = 0; (long) i * (i + 1) <= inputNumber; i++) {
            if ((long) i * (i + 1) == inputNumber) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given number is a pronic number in O(1) time using square root.
     *
     * @param number integer value to be checked
     * @return true if number is a pronic number, false otherwise
     */
    public static boolean isPronicNumber(int number) {
        if (number < 0) {
            return false;
        }
        int squareRoot = (int) Math.sqrt(number);
        return (long) squareRoot * (squareRoot + 1) == number;
    }
}
