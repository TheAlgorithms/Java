package com.thealgorithms.recursion;

/**
 * Tower of Hanoi problem using recursion.
 *
 * <p>Time Complexity: O(2^n)</p>
 * <p>Space Complexity: O(n) (recursion stack)</p>
 */
public final class TowerOfHanoi {

    private TowerOfHanoi() {
        // Utility class
    }

    public static void towerOfHanoi(int n, char src, char helper, char dest) {
        if (n == 1) {
            System.out.println("Move disk " + n + " from " + src + " to " + dest);
            return;
        }

        towerOfHanoi(n - 1, src, dest, helper);
        System.out.println("Move disk " + n + " from " + src + " to " + dest);
        towerOfHanoi(n - 1, helper, src, dest);
    }
}

