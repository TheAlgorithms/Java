package com.thealgorithms.maths;

/**
 * In number theory, an evil number is a non-negative integer that has an even number of 1s in its binary expansion.
 * Non-negative integers that are not evil are called odious numbers.
 * <p>
 * Evil Number Wi<a href="ki:">https://en.wikipedia.org/wiki/Evil_nu</a>mber
 * Odious Number Wi<a href="ki:">https://en.wikipedia.org/wiki/Odious_nu</a>mber
 */
public final class EvilNumber {

    private EvilNumber() {
    }

    // Function to count number of one bits in a number using bitwise operators
    private static int countOneBits(int number) {
        int oneBitCounter = 0;
        while (number > 0) {
            oneBitCounter += number & 1; // increment count if last bit is 1
            number >>= 1; // right shift to next bit
        }
        return oneBitCounter;
    }

    /**
     * Check either {@code number} is an Evil number or Odious number
     *
     * @param number the number
     * @return {@code true} if {@code number} is an Evil number, otherwise false (in case of of Odious number)
     */
    public static boolean isEvilNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Negative numbers are not allowed.");
        }

        int noOfOneBits = countOneBits(number);
        return noOfOneBits % 2 == 0;
    }
}
