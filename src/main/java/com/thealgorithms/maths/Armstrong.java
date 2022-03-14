package com.thealgorithms.maths;

/**
 * An Armstrong number is equal to the sum of the cubes of its digits. For
 * example, 370 is an Armstrong number because 3*3*3 + 7*7*7 + 0*0*0 = 370. An
 * Armstrong number is often called Narcissistic number.
 *
 * @author Aitor Fidalgo Sánchez, Vivek (crackCodeLogN)
 */
public class Armstrong {

    /**
     * Checks whether a given number is an armstrong number or not.
     *
     * @param number number to check
     * @return {@code true} if given number is armstrong number, {@code false}
     * otherwise
     */
    public boolean isArmStrongV1(int number) {
        int sum = 0;
        int temp = number;
        int numberOfDigits = 0;
        while (temp != 0) {
            numberOfDigits++;
            temp /= 10;
        }
        temp = number;
        /* copy number again */
        while (number > 0) {
            int remainder = number % 10;
            int power = 1;
            for (int i = 1; i <= numberOfDigits; power *= remainder, ++i)
                ;
            sum = sum + power;
            number /= 10;
        }
        return sum == temp;
    }

    /**
     * Checks whether a number is Armstrong or not.
     *
     * @param number
     * @return {@code true} if given number is armstrong number, {@code false} otherwise
     */
    public boolean isArmStrongV2(long number) {
        long sum = 0, number2 = number;
        while (number2 > 0) {
            long mod = number2 % 10;
            sum += Math.pow(mod, 3);
            number2 /= 10;
        }
        return sum == number;
    }
}