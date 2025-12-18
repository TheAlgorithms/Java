package com.thealgorithms.recursion;

/**
 * Tower of Hanoi problem solved using recursion.
 *
 * <p>Time Complexity: O(2^n)</p>
 * <p>Space Complexity: O(n)</p>
 */
public final class TowerOfHanoi {

    private TowerOfHanoi() {
        // Utility class
    }

    /**
     * Solves the Tower of Hanoi problem.
     *
     * @param n number of disks
     * @param source source rod
     * @param helper auxiliary rod
     * @param destination destination rod
     */
    public static void towerOfHanoi(int n, char source, char helper, char destination) {
        if (n == 1) {
            System.out.println(
                "Move disk 1 from " + source + " to " + destination
            );
            return;
        }

        towerOfHanoi(n - 1, source, destination, helper);

        System.out.println(
            "Move disk " + n + " from " + source + " to " + destination
        );

        towerOfHanoi(n - 1, helper, source, destination);
    }
}