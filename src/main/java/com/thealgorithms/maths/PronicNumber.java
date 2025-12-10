package com.thealgorithms.maths;

/*
 * Java program for Pronic Number
 * Pronic Number: A number n is a pronic number if
 * it is equal to product of two consecutive numbers m and m+1.
 * Wikipedia: https://en.wikipedia.org/wiki/Pronic_number
 *
 * Author: Akshay Dubey (https://github.com/itsAkshayDubey)
 *
 * */

public final class PronicNumber {
    private PronicNumber() {
    }

    /**
     * This method checks if the given number is pronic number or non-pronic number
     *
     * @param inputNumber Integer value which is to be checked if is a pronic number or not
     * @return true if input number is a pronic number, false otherwise
     */
    static boolean isPronic(int inputNumber) {
        if (inputNumber == 0) {
            return true;
        }
        // Iterating from 0 to input_number
        for (int i = 0; i <= inputNumber; i++) {
            // Checking if product of i and (i+1) is equals input_number
            if (i * (i + 1) == inputNumber && i != inputNumber) {
                // return true if product of i and (i+1) is equals input_number
                return true;
            }
        }

        // return false if product of i and (i+1) for all values from 0 to input_number is not
        // equals input_number
        return false;
    }

    /**
     * This method checks if the given number is pronic number or non-pronic number using square root of number for finding divisors
     *
     * @param number Integer value which is to be checked if is a pronic number or not
     * @return true if input number is a pronic number, false otherwise
     */
    public static boolean isPronicNumber(int number) {
        int squareRoot = (int) Math.sqrt(number); // finding just smaller divisor of the number than its square root.
        return squareRoot * (squareRoot + 1) == number;
    }
}
