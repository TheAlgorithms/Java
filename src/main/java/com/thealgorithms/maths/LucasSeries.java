package com.thealgorithms.maths;

/**
 * https://en.wikipedia.org/wiki/Lucas_number
 */
public class LucasSeries {

    /**
     * Calculate nth number of Lucas Series(2, 1, 3, 4, 7, 11, 18, 29, 47, 76,
     * 123, ....) using recursion
     *
     * @param n nth
     * @return nth number of Lucas Series
     */
    public static int lucasSeries(int n) {
        return n == 1 ? 2 : n == 2 ? 1 : lucasSeries(n - 1) + lucasSeries(n - 2);
    }

    /**
     * Calculate nth number of Lucas Series(2, 1, 3, 4, 7, 11, 18, 29, 47, 76,
     * 123, ....) using iteration
     *
     * @param n nth
     * @return nth number of lucas series
     */
    public static int lucasSeriesIteration(int n) {
        int previous = 2;
        int current = 1;
        for (int i = 1; i < n; i++) {
            int next = previous + current;
            previous = current;
            current = next;
        }
        return previous;
    }
}
