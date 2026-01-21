package com.thealgorithms.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * TowerOfHanoi - Solves the classic Tower of Hanoi puzzle
 *
 * This algorithm uses recursion to move a stack of disks from a source rod to a
 * destination rod, following the rules:
 * 1. Only one disk can be moved at a time.
 * 2. Each move consists of taking the upper disk from one of the stacks and
 * placing it on top of another stack.
 * 3. No disk may be placed on top of a smaller disk.
 *
 * Example: If n = 3, Source = 'A', Destination = 'C', Auxiliary = 'B'
 * Resulting moves will guide disks from A to C using B.
 *
 * @author justanothercoder-hub
 * @see <a href="https://en.wikipedia.org/wiki/Tower_of_Hanoi">Tower of Hanoi</a>
 */
public final class TowerOfHanoi {

    private TowerOfHanoi() {
        // Utility class
    }

    /**
     * Solves the Tower of Hanoi puzzle and returns the list of moves
     *
     * @param n number of disks
     * @param source the source rod
     * @param destination the destination rod
     * @param auxiliary the auxiliary rod
     * @return list of moves as strings
     */
    public static List<String> solveTowerOfHanoi(int n, char source, char destination, char auxiliary) {
        if (n < 0) {
            throw new IllegalArgumentException("Number of disks cannot be negative");
        }
        List<String> moves = new ArrayList<>();
        moveDisks(n, source, destination, auxiliary, moves);
        return moves;
    }

    /**
     * Recursive helper method to move disks
     *
     * @param n number of disks
     * @param source the source rod
     * @param destination the destination rod
     * @param auxiliary the auxiliary rod
     * @param moves list to record the moves
     */
    private static void moveDisks(int n, char source, char destination, char auxiliary, List<String> moves) {
        if (n == 1) {
            moves.add("Move disk 1 from rod " + source + " to rod " + destination);
            return;
        }
        moveDisks(n - 1, source, auxiliary, destination, moves);
        moves.add("Move disk " + n + " from rod " + source + " to rod " + destination);
        moveDisks(n - 1, auxiliary, destination, source, moves);
    }
}