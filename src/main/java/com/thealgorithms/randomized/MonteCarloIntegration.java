package com.thealgorithms.randomized;

import java.util.Random;
import java.util.function.Function;

/**
 * A demonstration of the Monte Carlo integration algorithm in Java.
 *
 * <p>This class estimates the value of definite integrals using randomized sampling,
 * also known as the Monte Carlo method. It is particularly effective for:
 * <ul>
 *   <li>Functions that are difficult or impossible to integrate analytically</li>
 *   <li>High-dimensional integrals where traditional methods are inefficient</li>
 *   <li>Simulation and probabilistic analysis tasks</li>
 * </ul>
 *
 * <p>The core idea is to sample random points uniformly from the integration domain,
 * evaluate the function at those points, and compute the scaled average to estimate the integral.
 *
 * <p>For a one-dimensional integral over [a, b], the approximation is the function range (b-a),
 * multiplied by the function average result for a random sample.
 * See more: <a href="https://en.wikipedia.org/wiki/Monte_Carlo_integration">Monte Carlo Integration</a>
 *
 * @author: MuhammadEzzatHBK
 */

public final class MonteCarloIntegration {

    private MonteCarloIntegration() {
    }

    /**
     * Approximates the definite integral of a given function over a specified
     * interval using the Monte Carlo method with a fixed random seed for
     * reproducibility.
     *
     * @param fx    the function to integrate
     * @param a     the lower bound of the interval
     * @param b     the upper bound of the interval
     * @param n     the number of random samples to use
     * @param seed  the seed for the random number generator
     * @return      the approximate value of the integral
     */
    public static double approximate(Function<Double, Double> fx, double a, double b, int n, long seed) {
        return doApproximate(fx, a, b, n, new Random(seed));
    }

    /**
     * Approximates the definite integral of a given function over a specified
     * interval using the Monte Carlo method with a random seed based on the
     * current system time for more randomness.
     *
     * @param fx    the function to integrate
     * @param a     the lower bound of the interval
     * @param b     the upper bound of the interval
     * @param n     the number of random samples to use
     * @return      the approximate value of the integral
     */
    public static double approximate(Function<Double, Double> fx, double a, double b, int n) {
        return doApproximate(fx, a, b, n, new Random(System.currentTimeMillis()));
    }

    private static double doApproximate(Function<Double, Double> fx, double a, double b, int n, Random generator) {
        if (!validate(fx, a, b, n)) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
        double totalArea = 0.0;
        double interval = b - a;
        for (int i = 0; i < n; i++) {
            double x = a + generator.nextDouble() * interval;
            totalArea += fx.apply(x);
        }
        return interval * totalArea / n;
    }

    private static boolean validate(Function<Double, Double> fx, double a, double b, int n) {
        boolean isFunctionValid = fx != null;
        boolean isIntervalValid = a < b;
        boolean isSampleSizeValid = n > 0;
        return isFunctionValid && isIntervalValid && isSampleSizeValid;
    }
}
