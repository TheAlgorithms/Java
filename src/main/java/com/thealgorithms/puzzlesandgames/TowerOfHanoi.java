package com.thealgorithms.puzzlesandgames;

import java.util.List;

/**
 * Recursive solution to the Tower of Hanoi puzzle.
 *
 * <p>
 * The puzzle rules are:
 * 1. Only one disc can be moved at a time.
 * 2. A disc can only be placed on top of a larger disc.
 * 3. All discs must start on one pole and end on another.
 * </p>
 *
 * <p>
 * The recursion follows three steps:
 * 1. Move {@code n-1} discs from start to intermediate.
 * 2. Move the largest disc from start to end.
 * 3. Move {@code n-1} discs from intermediate to end.
 * </p>
 *
 * <p>
 * Time Complexity: O(2^n) - exponential due to recursive expansion.
 * Space Complexity: O(n) - recursion stack depth.
 * </p>
 *
 * <p>
 * See <a href="https://en.wikipedia.org/wiki/Tower_of_Hanoi">Tower of Hanoi on Wikipedia</a>.
 * </p>
 */
public final class TowerOfHanoi {

    private TowerOfHanoi() {
    }

    /**
     * Recursively solve the Tower of Hanoi puzzle by moving discs between poles.
     *
     * @param n                The number of discs to move.
     * @param startPole        The name of the start pole from which discs are moved.
     * @param intermediatePole The name of the intermediate pole used as a temporary holding area.
     * @param endPole          The name of the end pole to which discs are moved.
     * @param result           A list to store the steps required to solve the puzzle.
     * @throws IllegalArgumentException if {@code n} is negative.
     *
     *                         <p>
     *                         This method is called recursively to move n-1 discs
     *                         to the intermediate pole,
     *                         then moves the nth disc to the end pole, and finally
     *                         moves the n-1 discs from the
     *                         intermediate pole to the end pole.
     *                         </p>
     *
     *                         <p>
     *                         Time Complexity: O(2^n) - Exponential time complexity due to the recursive nature of the problem.
     *                         Space Complexity: O(n) - Linear space complexity due to the recursion stack.
     *                         </p>
     */
    public static void shift(int n, String startPole, String intermediatePole, String endPole, List<String> result) {
        if (n < 0) {
            throw new IllegalArgumentException("Number of discs must be non-negative");
        }
        if (n == 0) {
            return;
        }

        // Move n-1 discs from startPole to intermediatePole
        shift(n - 1, startPole, endPole, intermediatePole, result);

        // Add the move of the nth disc from startPole to endPole
        result.add(String.format("Move %d from %s to %s", n, startPole, endPole));

        // Move the n-1 discs from intermediatePole to endPole
        shift(n - 1, intermediatePole, startPole, endPole, result);
    }
}
