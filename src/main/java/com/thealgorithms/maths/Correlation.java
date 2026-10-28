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
     * Correlation between two discrete variables is calculated
     * according to the formula: Cor(x, y)=Cov(x, y)/sqrt(Var(x)*Var(y)).
     * Correlation with a constant variable is taken to be zero.
     *
     * @param x The first discrete variable
     * @param y The second discrete variable
     * @param n The number of values for each variable
     * @return The result of the correlation of variables x,y.
     */
    public static double correlation(double[] x, double[] y, int n) {
        double exy = 0; // E(XY)
        double ex = 0; // E(X)
        double exx = 0; // E(X^2)
        double ey = 0; // E(Y)
        double eyy = 0; // E(Y^2)
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
        double cov = exy - ex * ey; // Cov(X, Y) = E(XY)-E(X)E(Y)
        double varx = Math.sqrt(exx - ex * ex); // Var(X) = sqrt(E(X^2)-E(X)^2)
        double vary = Math.sqrt(eyy - ey * ey); // Var(Y) = sqrt(E(Y^2)-E(Y)^2)
        if (varx * vary < DELTA) { // Var(X) = 0 means X = const, the same about Y
            return 0;
        } else {
            return cov / Math.sqrt(varx * vary);
        }
    }
}
