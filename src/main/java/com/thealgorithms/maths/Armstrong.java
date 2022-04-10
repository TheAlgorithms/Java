package com.thealgorithms.maths;

/**
 * An Armstrong number is equal to the sum of the cubes of its digits. For
 * example, 370 is an Armstrong number because 3*3*3 + 7*7*7 + 0*0*0 = 370. An
 * Armstrong number is often called Narcissistic number.
 *
 * @author Vivek
 */
public class Armstrong {

    /**
     * Checks whether a given number is an armstrong number or not.
     *
     * @param number number to check
     * @return {@code true} if given number is armstrong number, {@code false}
     * otherwise
     */
    public boolean isArmstrong(int number) {
        long sum = 0;
        long number2 = number;
        while (number2 > 0) {
            long mod = number2 % 10;
            sum += Math.pow(mod, 3);
            number2 /= 10;
        }
        return sum == number;
    }
}