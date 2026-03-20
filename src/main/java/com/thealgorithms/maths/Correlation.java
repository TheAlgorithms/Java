package com.thealgorithms.maths;

/**
 * Class for correlation of two discrete variables
 */

public final class Correlation {
    private Correlation() {
    }

    public static final double DELTA = 1e-9;

    /**
     * Discrete correlation function.
     * Correlation between two discrete variables is calculated.
     * Correlation with a constant variable is taken to be zero.
     *
     * @param x The first discrete variable
     * @param y The second discrete variable
     * @param n The number of values for each variable
     * @return The result of the correlation of variables x,y.
     */
    public static double correlation(double[] x, double[] y, int n) {
        double exy = 0;
		double ex = 0;
		double exx = 0;
		double ey = 0;
		double eyy = 0;
        for (int i = 0; i < n; i++) {
            exy += x[i] * y[i];
            ex += x[i];
            exx += x[i] * x[i];
            ey += y[i];
            eyy += y[i] * y[i];
        }
        exy /= n;
        ex /= n;
        exx /= n;
        ey /= n;
        eyy /= n;
        double cov = exy - ex * ey;
        double varx = Math.sqrt(exx - ex * ex);
        double vary = Math.sqrt(eyy - ey * ey);
        if (varx * vary < DELTA) {
            return 0;
        } else {
            return cov / Math.sqrt(varx * vary);
        }
    }
}
