package com.thealgorithms.dynamicprogramming;

/**
 * The NewManShanksPrime class provides a method to determine whether the nth
 * New Man Shanks prime matches an expected answer.
 *
 * <p>This is based on the New Man Shanks prime sequence defined by the recurrence
 * relation:</p>
 *
 * <pre>
 * a(n) = 2 * a(n-1) + a(n-2) for n >= 2
 * a(0) = 1
 * a(1) = 1
 * </pre>
 *
 * <p>For more information on New Man Shanks primes, please refer to the
 * <a href="https://en.wikipedia.org/wiki/Newman%E2%80%93Shanks%E2%80%93Williams_prime">
 * Wikipedia article</a>.</p>
 *
 * <p>Note: The class is designed to be non-instantiable.</p>
 *
 * @author <a href="https://github.com/siddhant2002">Siddhant Swarup Mallick</a>
 */
public final class NewManShanksPrime {
    private NewManShanksPrime() {
    }

    /**
     * Calculates the nth New Man Shanks prime and checks if it equals the
     * expected answer.
     *
     * @param n the index of the New Man Shanks prime to calculate (0-based).
     * @param expectedAnswer the expected value of the nth New Man Shanks prime.
     * @return true if the calculated nth New Man Shanks prime matches the
     *         expected answer; false otherwise.
     */
    public static boolean nthManShanksPrime(int n, int expectedAnswer) {
        int[] a = new int[n + 1];
        a[0] = 1;
        a[1] = 1;

        for (int i = 2; i <= n; i++) {
            a[i] = 2 * a[i - 1] + a[i - 2];
        }

        return a[n] == expectedAnswer;
    }
}
