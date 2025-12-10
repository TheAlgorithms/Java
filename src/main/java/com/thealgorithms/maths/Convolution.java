package com.thealgorithms.maths;

/**
 * Class for linear convolution of two discrete signals
 *
 * @author Ioannis Karavitsis
 * @version 1.0
 */
public final class Convolution {
    private Convolution() {
    }

    /**
     * Discrete linear convolution function. Both input signals and the output
     * signal must start from 0. If you have a signal that has values before 0
     * then shift it to start from 0.
     *
     * @param a The first discrete signal
     * @param b The second discrete signal
     * @return The convolved signal
     */
    public static double[] convolution(double[] a, double[] b) {
        double[] convolved = new double[a.length + b.length - 1];

        /*
         * Discrete convolution formula:
         * C[i] = Σ A[k] * B[i - k]
         * where k ranges over valid indices so that both A[k] and B[i-k] are in bounds.
         */

        for (int i = 0; i < convolved.length; i++) {
            double sum = 0;
            int kStart = Math.max(0, i - b.length + 1);
            int kEnd = Math.min(i, a.length - 1);

            for (int k = kStart; k <= kEnd; k++) {
                sum += a[k] * b[i - k];
            }

            convolved[i] = sum;
        }

        return convolved;
    }
}
