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
    The discrete convolution of two signals A and B is defined as:

          A.length
    C[i] = Σ (A[k]*B[i-k])
          k=0

    It's obvious that:  0 <= k <= A.length , 0 <= i <= A.length + B.length - 2  and  0 <= i-k <=
    B.length - 1 From the last inequality we get that:  i - B.length + 1 <= k <= i and thus we get
    the conditions below.
         */
        for (int i = 0; i < convolved.length; i++) {
            convolved[i] = 0;
            int k = Math.max(i - b.length + 1, 0);

            while (k < i + 1 && k < a.length) {
                convolved[i] += a[k] * b[i - k];
                k++;
            }
        }

        return convolved;
    }
}
