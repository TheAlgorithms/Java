package com.thealgorithms.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class to solve the Tower of Hanoi problem using recursion.
 *
 * The Tower of Hanoi is a classic recursive algorithm where the objective is to move all disks
 * from the source peg to the destination peg following these rules:
 * 1. Only one disk can be moved at a time.
 * 2. A disk can only be moved if it is the topmost disk on a peg.
 * 3. No larger disk may be placed on top of a smaller disk.
 *
 * The recursive solution divides the problem into three steps:
 * Step 1: Move (n-1) disks from source to auxiliary peg.
 * Step 2: Move the largest disk from source to destination peg.
 * Step 3: Move (n-1) disks from auxiliary to destination peg.
 *
 * Time Complexity: O(2^n - 1)
 * Space Complexity: O(n) - recursion call stack depth
 */
public final class TowerOfHanoi {

    private TowerOfHanoi() {
    }

    /**
     * Solves the Tower of Hanoi problem for n disks.
     *
     * @param n           number of disks (must be at least 1)
     * @param source      source peg (typically 'A')
     * @param destination destination peg (typically 'C')
     * @param auxiliary   auxiliary peg (typically 'B')
     * @return a list of moves required to solve the problem
     * @throws IllegalArgumentException if n is less than 1
     */
    public static List<String> solve(int n, char source, char destination, char auxiliary) {
        if (n < 1) {
            throw new IllegalArgumentException("Number of disks must be at least 1");
        }

        List<String> moves = new ArrayList<>();
        moveDisks(n, source, destination, auxiliary, moves);
        return moves;
    }

    /**
     * Recursive helper to move disks from source to destination using auxiliary peg.
     * Implements the three-step Tower of Hanoi algorithm.
     *
     * @param n           number of disks to move
     * @param source      source peg
     * @param destination destination peg
     * @param auxiliary   auxiliary peg
     * @param moves       list to accumulate moves
     */
    private static void moveDisks(int n, char source, char destination, char auxiliary, List<String> moves) {
        if (n == 1) {
            moves.add("Move disk 1 from " + source + " to " + destination);
            return;
        }

        moveDisks(n - 1, source, auxiliary, destination, moves);
        moves.add("Move disk " + n + " from " + source + " to " + destination);
        moveDisks(n - 1, auxiliary, destination, source, moves);
    }

    /**
     * Calculates the number of moves required to solve Tower of Hanoi for n disks.
     * Formula: 2^n - 1
     *
     * @param n number of disks (must be at least 1)
     * @return the number of moves required
     * @throws IllegalArgumentException if n is less than 1
     */
    public static long getMoveCount(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Number of disks must be at least 1");
        }
        return (1L << n) - 1;
    }
}
