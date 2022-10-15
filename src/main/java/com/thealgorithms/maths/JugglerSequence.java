package com.thealgorithms.maths;

import java.util.ArrayList;
import java.util.List;

/*
 * Java program for printing juggler sequence
 * Wikipedia: https://en.wikipedia.org/wiki/Juggler_sequence
 *
 * Author: Akshay Dubey (https://github.com/itsAkshayDubey)
 *
 * */

public class JugglerSequence {

    /**
     * This method prints juggler sequence starting with the number in the parameter
     *
     * @param inputNumber Number from which juggler sequence is to be started
     */
    public static void jugglerSequence(int inputNumber) {
        // Copy method argument to a local variable
        int n = inputNumber;
        List<String> seq = new ArrayList<>();
        seq.add(n + "");
        // Looping till n reaches 1
        while (n != 1) {
            int temp;
            // if previous term is even then
            // next term in the sequence is square root of previous term
            // if previous term is odd then
            // next term is floor value of 3 time the square root of previous term

            // Check if previous term is even or odd
            if (n % 2 == 0) {
                temp = (int) Math.floor(Math.sqrt(n));
            } else {
                temp =
                    (int) Math.floor(
                        Math.sqrt(n) * Math.sqrt(n) * Math.sqrt(n)
                    );
            }
            n = temp;
            seq.add(n + "");
        }
        String res = String.join(",", seq);
        System.out.println(res);
    }

    // Driver code
    public static void main(String[] args) {
        jugglerSequence(3);
        // Output: 3,5,11,36,6,2,1
    }
}
