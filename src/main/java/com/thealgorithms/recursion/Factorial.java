public final class Factorial {
    private Factorial() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Computes the factorial of a non-negative integer using recursion.
     *
     * @param n the number for which factorial is to be calculated
     * @return factorial of n
     * @throws IllegalArgumentException if n is negative
     */
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers.");
        }

        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
