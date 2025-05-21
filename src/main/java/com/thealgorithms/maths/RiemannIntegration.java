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
    public enum RiemannType {
        LEFT,
        RIGHT,
        MIDPOINT,
        TRAPEZOIDAL
    }
    
    /**
     * The default tolerance used for numerical computations.
     * This value represents the relative error tolerance that is considered acceptable
     * for integration results.
     */
    public static final double DEFAULT_TOLERANCE = 1e-10;
    
    /**
     * The tolerance used for numerical computations.
     * When comparing expected and actual values, differences smaller than this value are ignored.
     */
    private final double tolerance;
    
    /**
     * Creates a new RiemannIntegration instance with the default tolerance (1e-10).
     */
    public RiemannIntegration() {
        this.tolerance = DEFAULT_TOLERANCE;
    }
    
    /**
     * Creates a new RiemannIntegration instance with a custom tolerance.
     *
     * @param tolerance The tolerance to use for numerical computations.
     *                  Must be positive. Smaller values mean higher precision requirements.
     * @throws IllegalArgumentException if tolerance is not positive
     */
    public RiemannIntegration(double tolerance) {
        if (tolerance <= 0) {
            throw new IllegalArgumentException("Tolerance must be positive");
        }
        this.tolerance = tolerance;
    }
    
    /**
     * Returns the tolerance being used for numerical computations.
     *
     * @return The current tolerance value
     */
    public double getTolerance() {
        return tolerance;
    }

    /**
     * Approximates the definite integral of a function using Riemann sum method.
     *
     * @param function The function to integrate
     * @param lowerBound Lower bound of integration
     * @param upperBound Upper bound of integration
     * @param numIntervals Number of intervals to divide [lowerBound, upperBound]
     * @param type Type of Riemann sum (LEFT, RIGHT, MIDPOINT, TRAPEZOIDAL)
     * @return Approximation of the definite integral
     * @throws IllegalArgumentException if numIntervals is not positive or if lowerBound >= upperBound
     */
    public double computeIntegral(
        DoubleFunction<Double> function,
        double lowerBound,
        double upperBound,
        int numIntervals,
        RiemannType type
    ) {
        if (numIntervals <= 0) {
            throw new IllegalArgumentException("Number of intervals must be positive");
        }

        if (lowerBound >= upperBound) {
            throw new IllegalArgumentException("Upper bound must be greater than lower bound");
        }

        double deltaX = (upperBound - lowerBound) / numIntervals;
        double sum = 0.0;

        switch (type) {
            case LEFT:
                sum = leftRiemannSum(function, lowerBound, deltaX, numIntervals);
                break;
            case RIGHT:
                sum = rightRiemannSum(function, lowerBound, deltaX, numIntervals);
                break;
            case MIDPOINT:
                sum = midpointRiemannSum(function, lowerBound, deltaX, numIntervals);
                break;
            case TRAPEZOIDAL:
                sum = trapezoidalSum(function, lowerBound, deltaX, numIntervals);
                break;
            default:
                throw new IllegalArgumentException("Invalid Riemann sum type");
        }

        return sum;
    }
    
    /**
     * Static utility method for backward compatibility. Uses default tolerance.
     *
     * @param function The function to integrate
     * @param lowerBound Lower bound of integration
     * @param upperBound Upper bound of integration
     * @param numIntervals Number of intervals to divide [lowerBound, upperBound]
     * @param type Type of Riemann sum (LEFT, RIGHT, MIDPOINT, TRAPEZOIDAL)
     * @return Approximation of the definite integral
     * @throws IllegalArgumentException if numIntervals is not positive or if lowerBound >= upperBound
     */
    public static double integrate(
        DoubleFunction<Double> function,
        double lowerBound,
        double upperBound,
        int numIntervals,
        RiemannType type
    ) {
        return new RiemannIntegration().computeIntegral(function, lowerBound, upperBound, numIntervals, type);
    }

    /**
     * Left Riemann sum: uses the left endpoint of each subinterval
     */
    private static double leftRiemannSum(
        DoubleFunction<Double> function,
        double lowerBound,
        double deltaX,
        int numIntervals
    ) {
        double sum = 0.0;
        for (int i = 0; i < numIntervals; i++) {
            double x = lowerBound + i * deltaX;
            try {
                double value = function.apply(x);
                if (!Double.isNaN(value) && !Double.isInfinite(value)) {
                    sum += value;
                }
            } catch (ArithmeticException e) {
                // Skip points where function evaluation fails
            }
        }
        return sum * deltaX;
    }

    /**
     * Right Riemann sum: uses the right endpoint of each subinterval
     */
    private static double rightRiemannSum(
        DoubleFunction<Double> function,
        double lowerBound,
        double deltaX,
        int numIntervals
    ) {
        double sum = 0.0;
        for (int i = 1; i <= numIntervals; i++) {
            double x = lowerBound + i * deltaX;
            try {
                double value = function.apply(x);
                if (!Double.isNaN(value) && !Double.isInfinite(value)) {
                    sum += value;
                }
            } catch (ArithmeticException e) {
                // Skip points where function evaluation fails
            }
        }
        return sum * deltaX;
    }

    /**
     * Midpoint Riemann sum: uses the midpoint of each subinterval
     */
    private static double midpointRiemannSum(
        DoubleFunction<Double> function,
        double lowerBound,
        double deltaX,
        int numIntervals
    ) {
        double sum = 0.0;
        for (int i = 0; i < numIntervals; i++) {
            double x = lowerBound + (i + 0.5) * deltaX;
            try {
                double value = function.apply(x);
                if (!Double.isNaN(value) && !Double.isInfinite(value)) {
                    sum += value;
                }
            } catch (ArithmeticException e) {
                // Skip points where function evaluation fails
            }
        }
        return sum * deltaX;
    }

    /**
     * Trapezoidal sum: averages the function values at left and right endpoints
     */
    private static double trapezoidalSum(
        DoubleFunction<Double> function,
        double lowerBound,
        double deltaX,
        int numIntervals
    ) {
        double sum = 0.0;
        
        // First point
        try {
            double firstValue = function.apply(lowerBound);
            if (!Double.isNaN(firstValue) && !Double.isInfinite(firstValue)) {
                sum += firstValue / 2.0;
            }
        } catch (ArithmeticException e) {
            // Skip point if function evaluation fails
        }
        
        // Middle points
        for (int i = 1; i < numIntervals; i++) {
            double x = lowerBound + i * deltaX;
            try {
                double value = function.apply(x);
                if (!Double.isNaN(value) && !Double.isInfinite(value)) {
                    sum += value;
                }
            } catch (ArithmeticException e) {
                // Skip points where function evaluation fails
            }
        }
        
        // Last point
        try {
            double lastValue = function.apply(lowerBound + numIntervals * deltaX);
            if (!Double.isNaN(lastValue) && !Double.isInfinite(lastValue)) {
                sum += lastValue / 2.0;
            }
        } catch (ArithmeticException e) {
            // Skip point if function evaluation fails
        }
        
        return sum * deltaX;
    }
    
    /**
     * Determines if two double values are equal within the tolerance.
     * Uses both absolute and relative difference to compare values.
     * 
     * @param expected The expected value
     * @param actual The actual value
     * @return true if the values are considered equal within tolerance
     */
    public boolean areEqual(double expected, double actual) {
        if (expected == actual) {
            return true; // Handle exact match and special cases like infinity
        }
        
        double absoluteDifference = Math.abs(expected - actual);
        
        // For very small values, use absolute difference
        if (Math.abs(expected) < tolerance || Math.abs(actual) < tolerance) {
            return absoluteDifference < tolerance;
        } 
        
        // For larger values, use relative difference
        double relativeDifference = absoluteDifference / Math.max(Math.abs(expected), Math.abs(actual));
        return absoluteDifference < tolerance || relativeDifference < tolerance;
    }
    
    /**
     * Determines if two double values are equal within a specified tolerance.
     * Uses both absolute and relative difference to compare values.
     * 
     * @param expected The expected value
     * @param actual The actual value
     * @param customTolerance The tolerance to use for this specific comparison
     * @return true if the values are considered equal within the custom tolerance
     */
    public boolean areEqual(double expected, double actual, double customTolerance) {
        if (expected == actual) {
            return true; // Handle exact match and special cases like infinity
        }
        
        double absoluteDifference = Math.abs(expected - actual);
        
        // For very small values, use absolute difference
        if (Math.abs(expected) < customTolerance || Math.abs(actual) < customTolerance) {
            return absoluteDifference < customTolerance;
        }
        
        // For larger values, use relative difference
        double relativeDifference = absoluteDifference / Math.max(Math.abs(expected), Math.abs(actual));
        return absoluteDifference < customTolerance || relativeDifference < customTolerance;
    }
    
    /**
     * Calculates the approximate number of intervals needed to achieve a specified tolerance.
     * This is a heuristic estimate that assumes the error decreases quadratically with the number of intervals.
     * 
     * @param function The function to integrate
     * @param lowerBound Lower bound of integration
     * @param upperBound Upper bound of integration
     * @param type Type of Riemann sum (LEFT, RIGHT, MIDPOINT, TRAPEZOIDAL)
     * @param desiredTolerance The desired tolerance for the result
     * @return Estimated number of intervals needed
     */
    public int estimateRequiredIntervals(
        DoubleFunction<Double> function,
        double lowerBound,
        double upperBound,
        RiemannType type,
        double desiredTolerance
    ) {
        // Start with a baseline of intervals
        int baselineIntervals = 100;  // Increased for better baseline accuracy
        double baselineResult = computeIntegral(function, lowerBound, upperBound, baselineIntervals, type);
        
        // Calculate with double the intervals
        int doubledIntervals = baselineIntervals * 2;
        double refinedResult = computeIntegral(function, lowerBound, upperBound, doubledIntervals, type);
        
        // Estimate error and calculate required intervals
        double estimatedError = Math.abs(refinedResult - baselineResult);
        
        // If error is already below tolerance, return the baseline
        if (estimatedError < desiredTolerance) {
            return baselineIntervals;
        }
        
        // Different Riemann types have different convergence rates
        double convergenceFactor;
        switch (type) {
            case MIDPOINT:
            case TRAPEZOIDAL:
                // These methods have second-order convergence (error proportional to 1/n²)
                convergenceFactor = 2.0;
                break;
            case LEFT:
            case RIGHT:
                // These methods have first-order convergence (error proportional to 1/n)
                convergenceFactor = 1.0;
                break;
            default:
                convergenceFactor = 1.0;
        }
        
        // Calculate intervals needed based on error reduction with proper convergence factor
        double factorNeeded;
        if (convergenceFactor == 2.0) {
            // For second-order methods (error ~ 1/n²)
            factorNeeded = Math.ceil(Math.sqrt(estimatedError / desiredTolerance));
        } else {
            // For first-order methods (error ~ 1/n)
            factorNeeded = Math.ceil(estimatedError / desiredTolerance);
        }
        
        // Apply safety factor to ensure we meet tolerance
        return (int) Math.max(baselineIntervals * factorNeeded, 10000);
    }

    /**
     * Calculate dynamic tolerance based on estimated function degree and integration bounds
     * @param function The function to integrate
     * @param lowerBound The lower integration bound
     * @param upperBound The upper integration bound
     * @param type The Riemann integration type
     * @return A dynamically calculated tolerance
     */
    public double calculateDynamicTolerance(
        DoubleFunction<Double> function,
        double lowerBound,
        double upperBound,
        RiemannType type
    ) {
        // Estimate polynomial degree using finite differences
        int estimatedDegree = estimateFunctionDegree(function, lowerBound, upperBound);
        
        // Base tolerance that gets adjusted
        double baseTolerance = 1e-6;
        
        // Scale based on integration range
        double rangeScale = Math.log10(Math.abs(upperBound - lowerBound) + 1) + 1;
        
        // Scale based on degree (higher degree functions need more generous tolerance)
        double degreeScale = Math.pow(10, Math.min(estimatedDegree - 1, 10) / 3.0);
        
        // Scale based on method (MIDPOINT and TRAPEZOIDAL are more accurate)
        double methodScale = 1.0;
        if (type == RiemannType.LEFT || type == RiemannType.RIGHT) {
            methodScale = 5.0; // Less accurate methods need more generous tolerance
        }
        
        return baseTolerance * rangeScale * degreeScale * methodScale;
    }

    /**
     * Estimate the polynomial degree of a function using finite differences
     */
    private int estimateFunctionDegree(
        DoubleFunction<Double> function,
        double lowerBound,
        double upperBound
    ) {
        // Sample points
        final int samples = 11;
        double[] xValues = new double[samples];
        double[] yValues = new double[samples];
        
        // Generate evenly spaced sample points
        double step = (upperBound - lowerBound) / (samples - 1);
        for (int i = 0; i < samples; i++) {
            xValues[i] = lowerBound + i * step;
            try {
                yValues[i] = function.apply(xValues[i]);
            } catch (ArithmeticException e) {
                // If function evaluation fails, try a slightly different point
                xValues[i] = xValues[i] + step * 0.01;
                yValues[i] = function.apply(xValues[i]);
            }
            
            // Handle NaN or infinite values
            if (Double.isNaN(yValues[i]) || Double.isInfinite(yValues[i])) {
                // Use a fallback value
                yValues[i] = 0.0;
            }
        }
        
        // Compute finite differences until they become approximately constant
        return analyzeFiniteDifferences(yValues);
    }

    /**
     * Analyzes finite differences to estimate the polynomial degree
     */
    private int analyzeFiniteDifferences(double[] yValues) {
        int degree = 0;
        double tolerance = 1e-6;
        
        // Create a working copy of the values
        double[] diffs = yValues.clone();
        int n = diffs.length;
        
        // Compute successive differences until they become approximately constant or zero
        boolean isConstant = false;
        while (!isConstant && degree < n - 1) {
            // Compute the next level of differences
            for (int i = 0; i < n - degree - 1; i++) {
                diffs[i] = diffs[i + 1] - diffs[i];
            }
            
            // Check if differences are approximately constant or zero
            isConstant = true;
            double sum = 0.0;
            double sumSquares = 0.0;
            
            for (int i = 0; i < n - degree - 1; i++) {
                sum += diffs[i];
                sumSquares += diffs[i] * diffs[i];
            }
            
            int count = n - degree - 1;
            if (count > 1) {
                double mean = sum / count;
                double variance = (sumSquares - (sum * sum) / count) / (count - 1);
                
                // If the variance is very small relative to the mean, consider it constant
                if (Math.sqrt(variance) > tolerance * (Math.abs(mean) + tolerance)) {
                    isConstant = false;
                }
            }
            
            degree++;
        }
        
        return degree;
    }

}
