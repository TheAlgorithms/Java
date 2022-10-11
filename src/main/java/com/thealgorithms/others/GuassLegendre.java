package com.thealgorithms.others;

/**
 * Guass Legendre Algorithm ref
 * https://en.wikipedia.org/wiki/Gauss–Legendre_algorithm
 *
 * @author AKS1996
 */
public class GuassLegendre {

    public static void main(String[] args) {
        for (int i = 1; i <= 3; ++i) {
            System.out.println(pi(i));
        }
    }

    static double pi(int l) {
        /*
         * l: No of loops to run
         */

        double a = 1, b = Math.pow(2, -0.5), t = 0.25, p = 1;
        for (int i = 0; i < l; ++i) {
            double temp[] = update(a, b, t, p);
            a = temp[0];
            b = temp[1];
            t = temp[2];
            p = temp[3];
        }

        return Math.pow(a + b, 2) / (4 * t);
    }

    static double[] update(double a, double b, double t, double p) {
        double values[] = new double[4];
        values[0] = (a + b) / 2;
        values[1] = Math.sqrt(a * b);
        values[2] = t - p * Math.pow(a - values[0], 2);
        values[3] = 2 * p;

        return values;
    }
}
