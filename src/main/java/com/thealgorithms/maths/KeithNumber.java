package com.thealgorithms.maths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A Keith number is an n-digit positive integer where the sequence formed by
 * starting with its digits and repeatedly adding the previous n terms,
 * eventually reaches the number itself.
 *
 * <p>
 * For example:
 * <ul>
 * <li>14 is a Keith number: 1, 4, 5, 9, 14</li>
 * <li>19 is a Keith number: 1, 9, 10, 19</li>
 * <li>28 is a Keith number: 2, 8, 10, 18, 28</li>
 * <li>197 is a Keith number: 1, 9, 7, 17, 33, 57, 107, 197</li>
 * </ul>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Keith_number">Keith Number -
 *      Wikipedia</a>
 * @see <a href="https://mathworld.wolfram.com/KeithNumber.html">Keith Number -
 *      MathWorld</a>
 */
public final class KeithNumber {
    private KeithNumber() {
    }

    /**
     * Checks if a given number is a Keith number.
     *
     * <p>
     * The algorithm works as follows:
     * <ol>
     * <li>Extract all digits of the number and store them in a list</li>
     * <li>Generate subsequent terms by summing the last n digits</li>
     * <li>Continue until a term equals or exceeds the original number</li>
     * <li>If a term equals the number, it is a Keith number</li>
     * </ol>
     *
     * @param number the number to check (must be positive)
     * @return {@code true} if the number is a Keith number, {@code false} otherwise
     * @throws IllegalArgumentException if the number is not positive
     */
    public static boolean isKeith(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be positive");
        }

        // Extract digits and store them in the list
        ArrayList<Integer> terms = new ArrayList<>();
        int temp = number;
        int digitCount = 0;

        while (temp > 0) {
            terms.add(temp % 10);
            temp = temp / 10;
            digitCount++;
        }

        // Reverse the list to get digits in correct order
        Collections.reverse(terms);

        // Generate subsequent terms in the sequence
        int nextTerm = 0;
        int currentIndex = digitCount;

        while (nextTerm < number) {
            nextTerm = 0;
            // Sum the last 'digitCount' terms
            for (int j = 1; j <= digitCount; j++) {
                nextTerm = nextTerm + terms.get(currentIndex - j);
            }
            terms.add(nextTerm);
            currentIndex++;
        }

        // Check if the generated term equals the original number
        return (nextTerm == number);
    }

    /**
     * Main method for demonstrating Keith number detection.
     * Reads a number from standard input and checks if it is a Keith number.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a positive integer: ");
        int number = scanner.nextInt();

        if (isKeith(number)) {
            System.out.println("Yes, " + number + " is a Keith number.");
        } else {
            System.out.println("No, " + number + " is not a Keith number.");
        }
        scanner.close();
    }
}
