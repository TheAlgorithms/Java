package com.thealgorithms.maths;

/**
 * A utility to check if a given number is power of two or not. For example 8,16
 * etc.
 */
public class PowerOfTwoOrNot {

    public static void main(String[] args) {
        // Test for 0
        if (!checkIfPowerOfTwoOrNot(0)) {
            System.out.println("ERROR: 0 is not a power of two.");
        } else {
            System.out.println("Test passed: 0 is not a power of two.");
        }

        // Test for 1
        if (!checkIfPowerOfTwoOrNot(1)) {
            System.out.println("ERROR: 1 is not a power of two.");
        } else {
            System.out.println("Test passed: 1 is a power of two.");
        }

        // Test for 8
        if (!checkIfPowerOfTwoOrNot(8)) {
            System.out.println("ERROR: 8 is not a power of two.");
        } else {
            System.out.println("Test passed: 8 is a power of two.");
        }

        // Test for 16
        if (!checkIfPowerOfTwoOrNot(16)) {
            System.out.println("ERROR: 16 is not a power of two.");
        } else {
            System.out.println("Test passed: 16 is a power of two.");
        }

        // Test for 14
        if (!checkIfPowerOfTwoOrNot(14)) {
            System.out.println("ERROR: 14 is not a power of two.");
        } else {
            System.out.println("Test passed: 14 is a power of two.");
        }

        // Test for 21
        if (!checkIfPowerOfTwoOrNot(21)) {
            System.out.println("ERROR: 21 is not a power of two.");
        } else {
            System.out.println("Test passed: 21 is not a power of two.");
        }

        // Test for 1024
        if (!checkIfPowerOfTwoOrNot(1024)) {
            System.out.println("ERROR: 1024 is not a power of two.");
        } else {
            System.out.println("Test passed: 1024 is a power of two.");
        }
    }

    /**
     * Checks whether given number is power of two or not.
     *
     * @param number the number to check
     * @return {@code true} if given number is power of two, otherwise
     * {@code false}
     */
    public static boolean checkIfPowerOfTwoOrNot(int number) {

        boolean isNonZero = number != 0;
        boolean zeroBitSet = ((number & (number - 1)) == 0); // As Binary representation is in power of 2, doing a bit wise with a negated 1 value;  will result into a 0 if it's power of 2
        return isNonZero && zeroBitSet;

        // Samples
        /*  Final result is not 0000 hence not power of 2
             1101   (13 in binary)
           & 1110   (14 in binary)
           -------
             1100   (12 in binary)
         */

        /* final result is 0000 hence power of 2
          16 = 0b10000
          15 = 0b01111
        &-------------
               0b00000
         */
    }
}
