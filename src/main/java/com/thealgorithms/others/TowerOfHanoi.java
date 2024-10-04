package com.thealgorithms.others;

import java.util.Scanner;

/**
 * The {@code TowerOfHanoi} class provides functionality to solve the Tower of
 * Hanoi puzzle.
 * It uses recursion to move discs between poles and prints the steps to solve
 * the puzzle.
 * The main function interacts with the user to get the number of discs and
 * calls the recursive {@code shift} function to perform the moves.
 * Wikipedia: https://en.wikipedia.org/wiki/Tower_of_Hanoi
 */
final class TowerOfHanoi {

    private TowerOfHanoi() {
    }

    /**
     * Recursively solve the Tower of Hanoi puzzle by moving discs between poles.
     *
     * @param n                The number of discs to move.
     * @param startPole        The name of the start pole.
     * @param intermediatePole The name of the intermediate pole.
     * @param endPole          The name of the end pole.
     *
     *                         <p>
     *                         This method is called recursively to move n-1 discs
     *                         to the intermediate pole,
     *                         then moves the nth disc to the end pole, and finally
     *                         moves the n-1 discs from the
     *                         intermediate pole to the end pole.
     *                         </p>
     */
    public static void shift(int n, String startPole, String intermediatePole, String endPole) {
        // If n becomes zero, the program returns, ending the recursion.
        if (n != 0) {
            // Recursively move n-1 discs from startPole to intermediatePole
            shift(n - 1, startPole, endPole, intermediatePole);

            // Print the move of the nth disc from startPole to endPole
            System.out.format("Move %d from %s to %s%n", n, startPole, endPole);

            // Recursively move the n-1 discs from intermediatePole to endPole
            shift(n - 1, intermediatePole, startPole, endPole);
        }
    }

    /**
     * The main method that starts the Tower of Hanoi puzzle solution.
     * It prompts the user for the number of discs and invokes the {@code shift}
     * function to begin solving the puzzle.
     *
     * @param args Command-line arguments (not used in this case).
     */
    public static void main(String[] args) {
        System.out.print("Enter number of discs on Pole 1: ");
        Scanner scanner = new Scanner(System.in);
        int numberOfDiscs = scanner.nextInt(); // input of number of discs on pole 1
        shift(numberOfDiscs, "Pole1", "Pole2", "Pole3"); // Shift function called
        scanner.close();
    }
}
