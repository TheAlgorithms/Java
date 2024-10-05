package com.thealgorithms.others;

import java.util.List;

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
    public static void shift(int n, String startPole, String intermediatePole, String endPole, List<String> result) {
        if (n != 0) {
            // Recursively move n-1 discs from startPole to intermediatePole
            shift(n - 1, startPole, endPole, intermediatePole, result);

            // Add the move of the nth disc from startPole to endPole
            result.add(String.format("Move %d from %s to %s", n, startPole, endPole));

            // Recursively move the n-1 discs from intermediatePole to endPole
            shift(n - 1, intermediatePole, startPole, endPole, result);
        }
    }
}
