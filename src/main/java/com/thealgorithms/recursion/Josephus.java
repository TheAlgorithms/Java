package com.thealgorithms.recursion;

/**
 * Josephus - Solves the Josephus problem using recursion
 *
 * The Josephus problem: n people numbered 1..n stand in a circle. Starting from
 * position 1, every k-th person is eliminated until one remains. This class
 * provides a recursive solution to compute the survivor's position (1-based).
 *
 * Recurrence (0-based): J(1,k) = 0; J(n,k) = (J(n-1,k) + k) % n
 * Convert to 1-based by adding 1.
 *
 * @author ritesh-3822
 * @see <a href="https://en.wikipedia.org/wiki/Josephus_problem">Josephus problem</a>
 */
public final class Josephus {

    private Josephus() {
        // prevent instantiation
    }
    /**
     * Returns the 1-based position of the survivor for given n and k.
     *
     * @param n number of people (must be > 0)
     * @param k step count (must be > 0)
     * @return 1-based index of the survivor
     * @throws IllegalArgumentException if n <= 0 or k <= 0
     */
    public static int getJosephus(int n, int k) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }
        if (k <= 0) {
            throw new IllegalArgumentException("k must be positive");
        }
        // compute zero-based result and convert to 1-based
        return josephusZeroBased(n, k) + 1;
    }

    /**
     * Prints the survivor position (1-based) for given n and k.
     *
     * @param n number of people (must be > 0)
     * @param k step count (must be > 0)
     * @throws IllegalArgumentException if n <= 0 or k <= 0
     */
    public static void printJosephus(int n, int k) {
        int survivor = getJosephus(n, k);
        System.out.println(survivor);
    }

    /**
     * Recursive helper that returns the zero-based survivor index.
     *
     * @param n number of people
     * @param k step
     * @return zero-based survivor index
     */
    private static int josephusZeroBased(int n, int k) {
        // Base case
        if (n == 1) {
            return 0;
        }
        // Recurrence
        return (josephusZeroBased(n - 1, k) + k) % n;
    }

    /**
     * Demo method to show usage
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        int n = 7;
        int k = 3;

        System.out.println("Josephus problem demo:");
        System.out.printf("n = %d, k = %d%n", n, k);
        int survivor = getJosephus(n, k);
        System.out.println("Survivor (1-based position): " + survivor);
    }
}
