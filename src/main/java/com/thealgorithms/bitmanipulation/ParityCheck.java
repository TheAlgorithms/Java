package com.thealgorithms.bitmanipulation;

/**
 * The ParityCheck class provides a method to check the parity of a given number.
 * <p>
 * Parity is a mathematical term that describes the property of an integer's binary representation.
 * The parity of a binary number is the number of 1s in its binary representation.
 * If the number of 1s is even, the parity is even; otherwise, it is odd.
 * <p>
 * For example, the binary representation of 5 is 101, which has two 1s, so the parity of 5 is even.
 * The binary representation of 6 is 110, which has two 1s, so the parity of 6 is even.
 * The binary representation of 7 is 111, which has three 1s, so the parity of 7 is odd.
 *
 * @author Hardvan
 */
public final class ParityCheck {
    private ParityCheck() {
    }

    /**
     * This method checks the parity of the given number.
     *
     * @param n the number to check the parity of
     * @return true if the number has even parity, false otherwise
     */
    public static boolean checkParity(int n) {
        int count = 0;
        while (n > 0) {
            count += n & 1;
            n >>= 1;
        }
        return count % 2 == 0;
    }
}
