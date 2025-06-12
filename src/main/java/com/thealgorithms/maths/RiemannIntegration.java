package com.thealgorithms.maths;

import java.util.function.DoubleFunction;

/**
 * Implementation of Riemann sum algorithms for numerical integration.
 * These methods approximate the definite integral of a function by dividing
 * the integration interval into subintervals and calculating the sum of areas.
 */
public final class RiemannIntegration {

    /**
     * Enum representing different types of Riemann sums
     */
    public enum RiemannType { LEFT, RIGHT, MIDPOINT, TRAPEZOIDAL }

    /**
     * The default tolerance used for numerical computations.
     * This value represents the relative error tolerance that is considered acceptable
     * for integration results.
     */
    public static final double DEFAULT_TOLERANCE = 1e-10;

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    public RiemannIntegration() {
        // Intentionally empty
    }

    /**
     * Computes the definite integral of a function using Riemann sum approximation.
     *
     * @param function      The function to integrate
     * @param lowerBound    The lower bound of integration
     * @param upperBound    The upper bound of integration
     * @param intervals     The number of subintervals to use
     * @param type          The type of Riemann sum to use
     * @return              The approximate value of the definite integral
     * @throws IllegalArgumentException if intervals is not positive or if bounds are invalid
     */
    public static double integrate(DoubleFunction<Double> function, double lowerBound, double upperBound, int intervals, RiemannType type) {
        // Validate inputs
        if (intervals <= 0) {
            throw new IllegalArgumentException("Number of intervals must be positive");
        }

        if (lowerBound >= upperBound) {
            throw new IllegalArgumentException("Lower bound must be less than upper bound");
        }

        // Calculate width of each subinterval
        double width = (upperBound - lowerBound) / intervals;

        // Sum over all intervals based on the specified Riemann sum type
        double sum = 0.0;

        switch (type) {
        case LEFT:
            for (int i = 0; i < intervals; i++) {
                double x = lowerBound + i * width;
                Double y = function.apply(x);
                if (y != null && Double.isFinite(y)) {
                    sum += y;
                }
            }
            break;

        case RIGHT:
            for (int i = 1; i <= intervals; i++) {
                double x = lowerBound + i * width;
                Double y = function.apply(x);
                if (y != null && Double.isFinite(y)) {
                    sum += y;
                }
            }
            break;

        case MIDPOINT:
            for (int i = 0; i < intervals; i++) {
                double x = lowerBound + (i + 0.5) * width;
                Double y = function.apply(x);
                if (y != null && Double.isFinite(y)) {
                    sum += y;
                }
            }
            break;

        case TRAPEZOIDAL:
            // Add the endpoints with weight 1/2
            Double leftY = function.apply(lowerBound);
            Double rightY = function.apply(upperBound);

            if (leftY != null && Double.isFinite(leftY)) {
                sum += leftY / 2;
            }

            if (rightY != null && Double.isFinite(rightY)) {
                sum += rightY / 2;
            }

            // Add the interior points with weight 1
            for (int i = 1; i < intervals; i++) {
                double x = lowerBound + i * width;
                Double y = function.apply(x);
                if (y != null && Double.isFinite(y)) {
                    sum += y;
                }
            }
            break;

        default:
            throw new IllegalArgumentException("Unsupported Riemann sum type");
        }

        return sum * width;
    }

    /**
     * Instance-based method to compute definite integral with default tolerance.
     *
     * @param function      The function to integrate
     * @param lowerBound    The lower bound of integration
     * @param upperBound    The upper bound of integration
     * @param intervals     The number of subintervals to use
     * @param type          The type of Riemann sum to use
     * @return              The approximate value of the definite integral
     */
    public double computeIntegral(DoubleFunction<Double> function, double lowerBound, double upperBound, int intervals, RiemannType type) {
        return integrate(function, lowerBound, upperBound, intervals, type);
    }

    /**
     * Calculates a dynamic tolerance based on the function and integration bounds.
     *
     * @param function      The function to integrate
     * @param lowerBound    The lower bound of integration
     * @param upperBound    The upper bound of integration
     * @param type          The type of Riemann sum to use
     * @return              A dynamically calculated tolerance value
     */
    public double calculateDynamicTolerance(DoubleFunction<Double> function, double lowerBound, double upperBound, RiemannType type) {
        // Sample the function at a few points to determine appropriate scale
        int sampleCount = 10;
        double stepSize = (upperBound - lowerBound) / sampleCount;
        double maxAbsValue = 0.0;

        for (int i = 0; i <= sampleCount; i++) {
            double x = lowerBound + i * stepSize;
            Double y = function.apply(x);
            if (y != null && Double.isFinite(y)) {
                maxAbsValue = Math.max(maxAbsValue, Math.abs(y));
            }
        }

        // Return a tolerance that scales with the function's magnitude
        double scaleFactor = 1e-8;
        return Math.max(DEFAULT_TOLERANCE, maxAbsValue * scaleFactor);
    }

    /**
     * Estimates the number of intervals required to achieve a desired tolerance.
     *
     * @param function          The function to integrate
     * @param lowerBound        The lower bound of integration
     * @param upperBound        The upper bound of integration
     * @param type              The type of Riemann sum to use
     * @param desiredTolerance  The desired tolerance for the resul
     * @return                  An estimate of the required number of intervals
     * @throws IllegalArgumentException if the tolerance is not positive
     */
    public int estimateRequiredIntervals(DoubleFunction<Double> function, double lowerBound, double upperBound, RiemannType type, double desiredTolerance) {
        if (desiredTolerance <= 0) {
            throw new IllegalArgumentException("Tolerance must be positive");
        }

        // Start with a small number of intervals
        int intervals = 10;
        double initialResult = integrate(function, lowerBound, upperBound, intervals, type);

        // Try doubling the intervals until the change is within tolerance
        while (true) {
            int nextIntervals = intervals * 2;
            double nextResult = integrate(function, lowerBound, upperBound, nextIntervals, type);

            double relativeChange = Math.abs((nextResult - initialResult) / (Math.abs(initialResult) + 1e-15));

            if (relativeChange < desiredTolerance) {
                return nextIntervals;
            }

            // Update for next iteration
            intervals = nextIntervals;
            initialResult = nextResult;

            // Bailout to prevent infinite loops
            if (intervals > 1_000_000) {
                return intervals;
            }
        }
    }

    /**
     * Checks if two double values are equal within the default tolerance.
     *
     * @param expected  The expected value
     * @param actual    The actual value
     * @return          True if the values are equal within tolerance
     */
    public boolean areEqual(double expected, double actual) {
        return areEqual(expected, actual, DEFAULT_TOLERANCE);
    }

    /**
     * Checks if two double values are equal within a custom tolerance.
     *
     * @param expected         The expected value
     * @param actual           The actual value
     * @param customTolerance  The tolerance to use
     * @return                 True if the values are equal within tolerance
     */
    public boolean areEqual(double expected, double actual, double customTolerance) {
        if (Double.isNaN(expected) && Double.isNaN(actual)) {
            return true;
        }

        if (Double.isInfinite(expected) || Double.isNaN(expected) || Double.isInfinite(actual) || Double.isNaN(actual)) {
            return expected == actual;
        }

        if (expected == actual) {
            return true;
        }

        // For very small values, use absolute error
        if (Math.abs(expected) < customTolerance) {
            return Math.abs(expected - actual) < customTolerance;
        }

        // Otherwise use relative error
        return Math.abs((expected - actual) / expected) < customTolerance;
    }
}
