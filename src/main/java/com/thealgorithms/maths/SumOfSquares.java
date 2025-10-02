package com.thealgorithms.maths;

/**
 * Implementation of Lagrange's Four Square Theorem
 * Find minimum number of perfect squares that sum to given number
 *
 * @see <a href="https://en.wikipedia.org/wiki/Lagrange%27s_four-square_theorem">Lagrange's Four Square Theorem</a>
 * @author BEASTSHRIRAM
 */
public final class SumOfSquares {

    private SumOfSquares() {
        // Utility class
    }

    /**
     * Find minimum number of perfect squares that sum to n
     *
     * @param n the target number
     * @return minimum number of squares needed
     */
    public static int minSquares(int n) {
        if (isPerfectSquare(n)) {
            return 1;
        }

        for (int i = 1; i * i <= n; i++) {
            int remaining = n - i * i;
            if (isPerfectSquare(remaining)) {
                return 2;
            }
        }

        // Legendre's three-square theorem
        int temp = n;
        while (temp % 4 == 0) {
            temp /= 4;
        }
        if (temp % 8 == 7) {
            return 4;
        }

        return 3;
    }

    private static boolean isPerfectSquare(int n) {
        if (n < 0) {
            return false;
        }
        int root = (int) Math.sqrt(n);
        return root * root == n;
    }
}
