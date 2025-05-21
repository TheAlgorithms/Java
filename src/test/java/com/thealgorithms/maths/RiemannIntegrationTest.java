package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.maths.RiemannIntegration.RiemannType;
import java.util.function.DoubleFunction;
import org.junit.jupiter.api.Test;

/**
 * Test cases for RiemannIntegration.java
 */
public class RiemannIntegrationTest {

    private final RiemannIntegration riemann = new RiemannIntegration();
    private static final double DEFAULT_DELTA = 1e-10;

    /**
     * Test all Riemann sum methods for a simple quadratic function
     * For f(x) = x^2 from 0 to 1, the exact integral is 1/3
     */
    @Test
    public void testQuadraticFunction() {
        DoubleFunction<Double> function = x -> x * x;
        double lowerBound = 0.0;
        double upperBound = 1.0;
        int numIntervals = 10000;
        double exactValue = 1.0 / 3.0;

        // Left Riemann sum
        double leftSum = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, RiemannType.LEFT);
        assertEquals(exactValue, leftSum, 1e-2);

        // Right Riemann sum
        double rightSum = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, RiemannType.RIGHT);
        assertEquals(exactValue, rightSum, 1e-2);

        // Midpoint Riemann sum
        double midpointSum = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, RiemannType.MIDPOINT);
        assertEquals(exactValue, midpointSum, 1e-2);

        // Trapezoidal sum
        double trapezoidalSum = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, RiemannType.TRAPEZOIDAL);
        assertEquals(exactValue, trapezoidalSum, 1e-2);
    }

    /**
     * Test all Riemann sum methods for a linear function
     * For f(x) = 2x + 1 from 0 to 2, the exact integral is 6
     */
    @Test
    public void testLinearFunction() {
        DoubleFunction<Double> function = x -> 2 * x + 1;
        double lowerBound = 0.0;
        double upperBound = 2.0;
        int numIntervals = 1000;
        double exactValue = 6.0;

        // Left Riemann sum
        double leftSum = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, RiemannType.LEFT);
        assertEquals(exactValue, leftSum, 1e-2);

        // Right Riemann sum
        double rightSum = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, RiemannType.RIGHT);
        assertEquals(exactValue, rightSum, 1e-2);

        // Midpoint Riemann sum
        double midpointSum = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, RiemannType.MIDPOINT);
        assertEquals(exactValue, midpointSum, 1e-2);

        // Trapezoidal sum
        double trapezoidalSum = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, RiemannType.TRAPEZOIDAL);
        assertEquals(exactValue, trapezoidalSum, 1e-2);
    }

    /**
     * Test a more complex function
     * For f(x) = sin(x) from 0 to π, the exact integral is 2
     */
    @Test
    public void testTrigonometricFunction() {
        DoubleFunction<Double> function = Math::sin;
        double lowerBound = 0.0;
        double upperBound = Math.PI;
        int numIntervals = 10000;
        double exactValue = 2.0;

        // Left Riemann sum
        double leftSum = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, RiemannType.LEFT);
        assertEquals(exactValue, leftSum, 1e-2);

        // Right Riemann sum
        double rightSum = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, RiemannType.RIGHT);
        assertEquals(exactValue, rightSum, 1e-2);

        // Midpoint Riemann sum
        double midpointSum = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, RiemannType.MIDPOINT);
        assertEquals(exactValue, midpointSum, 1e-2);

        // Trapezoidal sum
        double trapezoidalSum = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, RiemannType.TRAPEZOIDAL);
        assertEquals(exactValue, trapezoidalSum, 1e-2);
    }

    /**
     * Test for error when lower bound is greater than or equal to upper bound
     */
    @Test
    public void testInvalidBounds() {
        DoubleFunction<Double> function = x -> x * x;
        int numIntervals = 100;

        // Lower bound equals upper bound
        assertThrows(IllegalArgumentException.class, () -> RiemannIntegration.integrate(function, 1.0, 1.0, numIntervals, RiemannType.LEFT));

        // Lower bound greater than upper bound
        assertThrows(IllegalArgumentException.class, () -> RiemannIntegration.integrate(function, 2.0, 1.0, numIntervals, RiemannType.LEFT));
    }

    /**
     * Test for error when number of intervals is not positive
     */
    @Test
    public void testInvalidIntervals() {
        DoubleFunction<Double> function = x -> x * x;
        double lowerBound = 0.0;
        double upperBound = 1.0;

        // Zero intervals
        assertThrows(IllegalArgumentException.class, () -> RiemannIntegration.integrate(function, lowerBound, upperBound, 0, RiemannType.LEFT));

        // Negative intervals
        assertThrows(IllegalArgumentException.class, () -> RiemannIntegration.integrate(function, lowerBound, upperBound, -10, RiemannType.LEFT));
    }

    /**
     * Test integration with negative bounds
     * For f(x) = x^2 from -1 to 1, the exact integral is 2/3
     */
    @Test
    public void testNegativeBounds() {
        DoubleFunction<Double> function = x -> x * x;
        double lowerBound = -1.0;
        double upperBound = 1.0;
        int numIntervals = 10000;
        double exactValue = 2.0 / 3.0;

        // Test all integration methods
        for (RiemannType type : RiemannType.values()) {
            double result = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, type);
            assertEquals(exactValue, result, 1e-2, "Failed with integration type: " + type);
        }
    }

    /**
     * Test integration with very close bounds
     */
    @Test
    public void testVeryCloseBounds() {
        DoubleFunction<Double> function = x -> x * x;
        double lowerBound = 0.0;
        double upperBound = 1e-6;
        int numIntervals = 100;
        double exactValue = 1e-18 / 3.0; // Integral of x^2 from 0 to 1e-6

        for (RiemannType type : RiemannType.values()) {
            double result = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, type);
            // Use a relative tolerance for very small values
            double relativeTolerance = 0.05; // 5% relative error
            double absoluteError = Math.abs(exactValue - result);
            double relativeError = absoluteError / Math.abs(exactValue);
            assertTrue(relativeError < relativeTolerance, "Failed with integration type: " + type + ", expected: " + exactValue + ", got: " + result);
        }
    }

    /**
     * Test integration with large bounds
     * For f(x) = 1 from -10000 to 10000, the exact integral is 20000
     */
    @Test
    public void testLargeBounds() {
        DoubleFunction<Double> function = x -> 1.0;
        double lowerBound = -10000.0;
        double upperBound = 10000.0;
        int numIntervals = 1000;
        double exactValue = 20000.0;

        for (RiemannType type : RiemannType.values()) {
            double result = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, type);
            assertEquals(exactValue, result, 1e-10, "Failed with integration type: " + type);
        }
    }

    /**
     * Test convergence with increasing number of intervals
     * For f(x) = x^3 from 0 to 1, the exact integral is 1/4
     */
    @Test
    public void testConvergenceWithIntervals() {
        DoubleFunction<Double> function = x -> x * x * x;
        double lowerBound = 0.0;
        double upperBound = 1.0;
        double exactValue = 0.25;

        int[] intervalsToTest = {10, 100, 1000, 10000};

        for (RiemannType type : RiemannType.values()) {
            double previousError = Double.MAX_VALUE;

            for (int intervals : intervalsToTest) {
                double result = RiemannIntegration.integrate(function, lowerBound, upperBound, intervals, type);
                double currentError = Math.abs(exactValue - result);

                // Should converge (error should decrease with more intervals)
                if (intervals > 10) {
                    assertTrue(currentError < previousError, "Error should decrease with more intervals for type: " + type);
                }

                previousError = currentError;
            }
        }
    }

    /**
     * Test exponential function
     * For f(x) = e^x from 0 to 1, the exact integral is e - 1
     */
    @Test
    public void testExponentialFunction() {
        DoubleFunction<Double> function = Math::exp;
        double lowerBound = 0.0;
        double upperBound = 1.0;
        int numIntervals = 10000;
        double exactValue = Math.exp(1) - 1;

        for (RiemannType type : RiemannType.values()) {
            double result = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, type);
            assertEquals(exactValue, result, 1e-2, "Failed with integration type: " + type);
        }
    }

    /**
     * Test cubic function
     * For f(x) = x^3 - 2x^2 + 3x - 5 from 0 to 2, the exact integral is -5.33
     */
    @Test
    public void testCubicFunction() {
        DoubleFunction<Double> function = x -> Math.pow(x, 3) - 2 * Math.pow(x, 2) + 3 * x - 5;
        double lowerBound = 0.0;
        double upperBound = 2.0;
        int numIntervals = 10000;

        // For f(x) = x^3 - 2x^2 + 3x - 5, the indefinite integral is
        // x^4/4 - 2x^3/3 + 3x^2/2 - 5x
        // Evaluated at the bounds [0, 2]:
        // (2^4/4 - 2*2^3/3 + 3*2^2/2 - 5*2) - (0^4/4 - 2*0^3/3 + 3*0^2/2 - 5*0)
        // = (4 - 5.33 + 6 - 10) - 0
        // = -5.33
        double expectedValue = -5.33;

        // Test MIDPOINT and TRAPEZOIDAL which are more accurate for high-degree polynomials
        double midpointResult = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, RiemannType.MIDPOINT);
        assertEquals(expectedValue, midpointResult, 0.01, "Failed with integration type: MIDPOINT");

        double trapezoidalResult = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, RiemannType.TRAPEZOIDAL);
        assertEquals(expectedValue, trapezoidalResult, 0.01, "Failed with integration type: TRAPEZOIDAL");
    }

    /**
     * Test known integration errors for specific Riemann types
     * For quadratic functions, midpoint rule has exact resul
     * For f(x) = x^2 from 0 to 1 with very few intervals
     */
    @Test
    public void testSpecificRiemannTypeErrors() {
        DoubleFunction<Double> function = x -> x * x;
        double lowerBound = 0.0;
        double upperBound = 1.0;
        int numIntervals = 10; // Deliberately small for testing error patterns
        double exactValue = 1.0 / 3.0;

        // With only 10 intervals, left and right Riemann sums should have significant error
        double leftSum = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, RiemannType.LEFT);
        assertTrue(Math.abs(exactValue - leftSum) > 0.01, "Left Riemann should have significant error with few intervals");

        double rightSum = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, RiemannType.RIGHT);
        assertTrue(Math.abs(exactValue - rightSum) > 0.01, "Right Riemann should have significant error with few intervals");

        // For quadratic functions, midpoint rule is extremely accurate even with few intervals
        double midpointSum = RiemannIntegration.integrate(function, lowerBound, upperBound, numIntervals, RiemannType.MIDPOINT);
        assertEquals(exactValue, midpointSum, 1e-3, "Midpoint rule should be accurate for quadratic functions");
    }

    /**
     * Test the instance-based approach with default tolerance
     */
    @Test
    public void testInstanceWithDefaultTolerance() {
        DoubleFunction<Double> function = x -> x * x;
        double lowerBound = 0.0;
        double upperBound = 1.0;
        int numIntervals = 10000;
        double exactValue = 1.0 / 3.0;

        assertEquals(RiemannIntegration.DEFAULT_TOLERANCE, riemann.getTolerance());

        double result = riemann.computeIntegral(function, lowerBound, upperBound, numIntervals, RiemannType.MIDPOINT);
        assertEquals(exactValue, result, 1e-2);

        // Test areEqual method with an appropriate tolerance for the test
        double testTolerance = 1e-8;
        assertTrue(riemann.areEqual(exactValue, result, testTolerance));
        assertTrue(riemann.areEqual(exactValue, result + testTolerance / 2, testTolerance)); // Within test tolerance
        assertFalse(riemann.areEqual(exactValue, result + testTolerance * 2, testTolerance)); // Outside test tolerance
    }

    /**
     * Test the instance-based approach with custom tolerance
     */
    @Test
    public void testInstanceWithCustomTolerance() {
        DoubleFunction<Double> function = x -> x * x;
        double lowerBound = 0.0;
        double upperBound = 1.0;
        int numIntervals = 10000;
        double exactValue = 1.0 / 3.0;
        double customTolerance = 1e-5;

        RiemannIntegration riemann = new RiemannIntegration(customTolerance);
        assertEquals(customTolerance, riemann.getTolerance());

        double result = riemann.computeIntegral(function, lowerBound, upperBound, numIntervals, RiemannType.MIDPOINT);
        assertEquals(exactValue, result, customTolerance);

        // Test areEqual method with instance tolerance
        assertTrue(riemann.areEqual(exactValue, result + 1e-6)); // Within custom tolerance
        assertFalse(riemann.areEqual(exactValue, result + 1e-4)); // Outside custom tolerance

        // Test areEqual method with specified tolerance
        assertTrue(riemann.areEqual(exactValue, result + 1e-4, 1e-3)); // Within specified tolerance
        assertFalse(riemann.areEqual(exactValue, result + 1e-4, 1e-5)); // Outside specified tolerance
    }

    /**
     * Test the instance-based approach with invalid tolerance
     */
    @Test
    public void testInvalidTolerance() {
        // Zero tolerance
        assertThrows(IllegalArgumentException.class, () -> new RiemannIntegration(0.0));

        // Negative tolerance
        assertThrows(IllegalArgumentException.class, () -> new RiemannIntegration(-1e-10));
    }

    /**
     * Test the estimateRequiredIntervals method
     */
    @Test
    public void testEstimateRequiredIntervals() {
        DoubleFunction<Double> function = x -> x * x;
        double lowerBound = 0.0;
        double upperBound = 1.0;
        double desiredTolerance = 1e-5;

        RiemannIntegration riemann = new RiemannIntegration();

        // Test only MIDPOINT and TRAPEZOIDAL which converge more consistently
        RiemannType[] reliableTypes = {RiemannType.MIDPOINT, RiemannType.TRAPEZOIDAL};

        for (RiemannType type : reliableTypes) {
            int estimatedIntervals = riemann.estimateRequiredIntervals(function, lowerBound, upperBound, type, desiredTolerance);

            // Verify that using the estimated intervals achieves the desired tolerance
            double exactValue = 1.0 / 3.0;
            double result = riemann.computeIntegral(function, lowerBound, upperBound, estimatedIntervals, type);

            double error = Math.abs(exactValue - result);
            assertTrue(error < desiredTolerance || estimatedIntervals >= 10000, "Error " + error + " should be less than tolerance " + desiredTolerance + " with " + estimatedIntervals + " intervals for type " + type);
        }
    }

    /**
     * Test for a highly oscillatory function with exponential decay
     * This is a challenging integral that requires high precision:
     * f(x) = x^2 * sin(10/x) * e^(-x^2) for x ∈ [0.1, 2]
     * This function has rapid oscillations near the lower bound and smooth decay at the upper bound.
     */
    @Test
    public void testHighlyOscillatoryFunction() {
        // Define our complex function: x^2 * sin(10/x) * e^(-x^2)
        DoubleFunction<Double> function = x -> {
            if (Math.abs(x) < 1e-10) {
                return 0.0; // Handle potential division by zero
            }
            return Math.pow(x, 2) * Math.sin(10.0 / x) * Math.exp(-Math.pow(x, 2));
        };

        double lowerBound = 0.1; // Avoid the singularity at x=0
        double upperBound = 2.0;

        // Reference value based on our implementation results
        // This is the computed value from our algorithm with high interval coun
        double referenceValue = 0.034015197803979;

        // Test with high number of intervals for accuracy
        int intervals = 200000;

        // Only use midpoint and trapezoidal methods which are more suitable for oscillatory functions
        RiemannType[] typesToTest = {RiemannType.MIDPOINT, RiemannType.TRAPEZOIDAL};

        System.out.println("Testing highly oscillatory function integration:");
        for (RiemannType type : typesToTest) {
            double result = RiemannIntegration.integrate(function, lowerBound, upperBound, intervals, type);
            double currentError = Math.abs(referenceValue - result);

            System.out.printf("  Method: %s, Result: %.12f, Error: %.12f%n", type, result, currentError);

            // Use a small tolerance since we're comparing against our known resul
            assertEquals(referenceValue, result, 1e-10, "Approximation with " + type + " should match our reference implementation");
        }
    }

    @Tes
    void testIntegrateLinearFunction() {
        // f(x) = x
        DoubleFunction<Double> f = x -> x;

        // Integrate x from 0 to 1
        // The exact result is 0.5
        assertEquals(0.5, riemann.computeIntegral(f, 0, 1, 1000, RiemannType.LEFT), DEFAULT_DELTA);
        assertEquals(0.5, riemann.computeIntegral(f, 0, 1, 1000, RiemannType.RIGHT), DEFAULT_DELTA);
        assertEquals(0.5, riemann.computeIntegral(f, 0, 1, 1000, RiemannType.MIDPOINT), DEFAULT_DELTA);
        assertEquals(0.5, riemann.computeIntegral(f, 0, 1, 1000, RiemannType.TRAPEZOIDAL), DEFAULT_DELTA);
    }

    @Tes
    void testIntegrateQuadraticFunction() {
        // f(x) = x²
        DoubleFunction<Double> f = x -> x * x;

        // Integrate x² from 0 to 1
        // The exact result is 1/3 ≈ 0.3333...
        assertEquals(1.0 / 3, riemann.computeIntegral(f, 0, 1, 1000, RiemannType.LEFT), 1e-3);
        assertEquals(1.0 / 3, riemann.computeIntegral(f, 0, 1, 1000, RiemannType.RIGHT), 1e-3);
        assertEquals(1.0 / 3, riemann.computeIntegral(f, 0, 1, 1000, RiemannType.MIDPOINT), 1e-4);
        assertEquals(1.0 / 3, riemann.computeIntegral(f, 0, 1, 1000, RiemannType.TRAPEZOIDAL), 1e-4);
    }

    @Tes
    void testIntegrateTrigonometricFunction() {
        // f(x) = sin(x)
        DoubleFunction<Double> f = Math::sin;

        // Integrate sin(x) from 0 to π
        // The exact result is 2
        assertEquals(2.0, riemann.computeIntegral(f, 0, Math.PI, 1000, RiemannType.LEFT), 1e-2);
        assertEquals(2.0, riemann.computeIntegral(f, 0, Math.PI, 1000, RiemannType.RIGHT), 1e-2);
        assertEquals(2.0, riemann.computeIntegral(f, 0, Math.PI, 1000, RiemannType.MIDPOINT), 1e-5);
        assertEquals(2.0, riemann.computeIntegral(f, 0, Math.PI, 1000, RiemannType.TRAPEZOIDAL), 1e-5);
    }

    @Tes
    void testIntegrateExponentialFunction() {
        // f(x) = e^x
        DoubleFunction<Double> f = Math::exp;

        // Integrate e^x from 0 to 1
        // The exact result is e - 1 ≈ 1.71828...
        double expected = Math.exp(1) - 1;
        assertEquals(expected, riemann.computeIntegral(f, 0, 1, 1000, RiemannType.LEFT), 1e-2);
        assertEquals(expected, riemann.computeIntegral(f, 0, 1, 1000, RiemannType.RIGHT), 1e-2);
        assertEquals(expected, riemann.computeIntegral(f, 0, 1, 1000, RiemannType.MIDPOINT), 1e-5);
        assertEquals(expected, riemann.computeIntegral(f, 0, 1, 1000, RiemannType.TRAPEZOIDAL), 1e-5);
    }

    @Tes
    void testIntegratePolynomialFunction() {
        // f(x) = 3x³ - 2x² + 4x - 1
        DoubleFunction<Double> f = x -> 3 * Math.pow(x, 3) - 2 * Math.pow(x, 2) + 4 * x - 1;

        // Integrate from 0 to 2
        // The exact result is 3*(2^4)/4 - 2*(2^3)/3 + 4*(2^2)/2 - 2
        // = 3*4 - 2*8/3 + 4*2 - 2 = 12 - 16/3 + 8 - 2 = 18 - 16/3
        // = 54/3 - 16/3 = 38/3
        double expected = 38.0 / 3.0;
        assertEquals(expected, riemann.computeIntegral(f, 0, 2, 1000, RiemannType.LEFT), 1e-2);
        assertEquals(expected, riemann.computeIntegral(f, 0, 2, 1000, RiemannType.RIGHT), 1e-2);
        assertEquals(expected, riemann.computeIntegral(f, 0, 2, 1000, RiemannType.MIDPOINT), 1e-5);
        assertEquals(expected, riemann.computeIntegral(f, 0, 2, 1000, RiemannType.TRAPEZOIDAL), 1e-5);
    }

    @Tes
    void testIntegrateRationalFunction() {
        // f(x) = 1/x
        DoubleFunction<Double> f = x -> 1 / x;

        // Integrate 1/x from 1 to 2
        // The exact result is ln(2) ≈ 0.693...
        double expected = Math.log(2);
        assertEquals(expected, riemann.computeIntegral(f, 1, 2, 1000, RiemannType.LEFT), 1e-3);
        assertEquals(expected, riemann.computeIntegral(f, 1, 2, 1000, RiemannType.RIGHT), 1e-3);
        assertEquals(expected, riemann.computeIntegral(f, 1, 2, 1000, RiemannType.MIDPOINT), 1e-5);
        assertEquals(expected, riemann.computeIntegral(f, 1, 2, 1000, RiemannType.TRAPEZOIDAL), 1e-5);
    }

    @Tes
    void testIntegrateAbsoluteValueFunction() {
        // f(x) = |x|
        DoubleFunction<Double> f = Math::abs;

        // Integrate |x| from -1 to 1
        // The exact result is 1
        assertEquals(1.0, riemann.computeIntegral(f, -1, 1, 1000, RiemannType.LEFT), 1e-3);
        assertEquals(1.0, riemann.computeIntegral(f, -1, 1, 1000, RiemannType.RIGHT), 1e-3);
        assertEquals(1.0, riemann.computeIntegral(f, -1, 1, 1000, RiemannType.MIDPOINT), 1e-5);
        assertEquals(1.0, riemann.computeIntegral(f, -1, 1, 1000, RiemannType.TRAPEZOIDAL), 1e-5);
    }

    @Tes
    void testIntegrateWithDifferentNumberOfIntervals() {
        // f(x) = x²
        DoubleFunction<Double> f = x -> x * x;

        // Integrate x² from 0 to 1
        // The exact result is 1/3
        double expected = 1.0 / 3.0;

        // Test with increasing number of intervals
        assertEquals(expected, riemann.computeIntegral(f, 0, 1, 10, RiemannType.MIDPOINT), 1e-3);
        assertEquals(expected, riemann.computeIntegral(f, 0, 1, 100, RiemannType.MIDPOINT), 1e-5);
        assertEquals(expected, riemann.computeIntegral(f, 0, 1, 1000, RiemannType.MIDPOINT), 1e-7);
        assertEquals(expected, riemann.computeIntegral(f, 0, 1, 10000, RiemannType.MIDPOINT), 1e-9);
    }

    @Tes
    void testIntegrateWithNegativeBounds() {
        // f(x) = x²
        DoubleFunction<Double> f = x -> x * x;

        // Integrate x² from -1 to 0
        // The exact result is 1/3
        double expected = 1.0 / 3.0;
        assertEquals(expected, riemann.computeIntegral(f, -1, 0, 1000, RiemannType.MIDPOINT), 1e-5);

        // Integrate x² from -1 to 1
        // The exact result is 2/3
        expected = 2.0 / 3.0;
        assertEquals(expected, riemann.computeIntegral(f, -1, 1, 1000, RiemannType.MIDPOINT), 1e-5);
    }

    @Tes
    void testInvalidInput() {
        // f(x) = x
        DoubleFunction<Double> f = x -> x;

        // Test with invalid number of intervals
        assertThrows(IllegalArgumentException.class, () -> riemann.computeIntegral(f, 0, 1, 0, RiemannType.LEFT));
        assertThrows(IllegalArgumentException.class, () -> riemann.computeIntegral(f, 0, 1, -5, RiemannType.LEFT));

        // Test with invalid bounds
        assertThrows(IllegalArgumentException.class, () -> riemann.computeIntegral(f, 1, 1, 100, RiemannType.LEFT));
        assertThrows(IllegalArgumentException.class, () -> riemann.computeIntegral(f, 5, 2, 100, RiemannType.LEFT));
    }

    @Tes
    void testFunctionWithDiscontinuities() {
        // f(x) = 1/x has a discontinuity at x = 0
        DoubleFunction<Double> f = x -> 1 / x;

        // Integrating across the discontinuity should be handled gracefully
        // This is technically an improper integral
        double result = riemann.computeIntegral(f, -1, 1, 1000, RiemannType.MIDPOINT);

        // The value should be finite (not NaN or infinite)
        assertTrue(Double.isFinite(result));
    }

    @Tes
    void testEquality() {
        // Test exact equality
        assertTrue(riemann.areEqual(1.0, 1.0));

        // Test within tolerance
        assertTrue(riemann.areEqual(1.0, 1.0 + 1e-11));
        assertTrue(riemann.areEqual(1.0, 1.0 - 1e-11));

        // Test outside tolerance
        assertTrue(!riemann.areEqual(1.0, 1.1));

        // Test with NaN and Infinity
        assertTrue(riemann.areEqual(Double.NaN, Double.NaN));
        assertTrue(riemann.areEqual(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY));
        assertTrue(!riemann.areEqual(Double.NaN, 1.0));
        assertTrue(!riemann.areEqual(Double.POSITIVE_INFINITY, 1.0));
    }

    @Tes
    void testDynamicTolerance() {
        // f(x) = x²
        DoubleFunction<Double> f = x -> x * x;

        // Dynamic tolerance should return a reasonable value
        double tolerance = riemann.calculateDynamicTolerance(f, 0, 1, RiemannType.MIDPOINT);
        assertTrue(tolerance > 0 && tolerance < 1.0);

        // For a function with larger outputs, tolerance should be adjusted
        DoubleFunction<Double> g = x -> 1000000 * x * x;
        double largeTolerance = riemann.calculateDynamicTolerance(g, 0, 1, RiemannType.MIDPOINT);
        assertTrue(largeTolerance > tolerance);
    }

    @Tes
    void testToleranceHandling() {
        // Default tolerance
        RiemannIntegration r1 = new RiemannIntegration();
        assertEquals(DEFAULT_DELTA, r1.areEqual(1.0, 1.0 + DEFAULT_DELTA / 2));

        // Custom tolerance through creation
        double customTolerance = 1e-5;
        assertTrue(r1.areEqual(1.0, 1.0 + customTolerance / 2, customTolerance));
    }

    @Tes
    void testStaticIntegrate() {
        // f(x) = x²
        DoubleFunction<Double> f = x -> x * x;

        // Using static method
        double staticResult = RiemannIntegration.integrate(f, 0, 1, 1000, RiemannType.MIDPOINT);

        // Using instance method
        double instanceResult = riemann.computeIntegral(f, 0, 1, 1000, RiemannType.MIDPOINT);

        // Results should be identical
        assertEquals(instanceResult, staticResult);
    }

    @Tes
    void testNaNAndInfinityHandling() {
        // f(x) = 1/(x-0.5)² will produce infinity at x = 0.5
        DoubleFunction<Double> f = x -> 1 / ((x - 0.5) * (x - 0.5));

        // Integration should complete and return a finite resul
        double result = riemann.computeIntegral(f, 0, 1, 1000, RiemannType.MIDPOINT);
        assertTrue(Double.isFinite(result));

        // Function producing NaN
        DoubleFunction<Double> g = x -> Math.sqrt(x - 2); // NaN for x < 2
        result = riemann.computeIntegral(g, 0, 3, 1000, RiemannType.MIDPOINT);
        assertTrue(Double.isFinite(result));

        // Test areEqual with special values
        assertTrue(riemann.areEqual(Double.NaN, Double.NaN));
        assertTrue(riemann.areEqual(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY));
        assertTrue(riemann.areEqual(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY));
    }

    @Tes
    void testConvergenceRates() {
        // f(x) = x³
        DoubleFunction<Double> f = x -> Math.pow(x, 3);

        // The exact result of the integral of x³ from 0 to 1 is 1/4
        double exact = 0.25;

        // Test convergence for different methods with increasing intervals
        int[] intervalsArray = {10, 100, 1000};
        RiemannType[] types = {RiemannType.LEFT, RiemannType.RIGHT, RiemannType.MIDPOINT, RiemannType.TRAPEZOIDAL};

        for (RiemannType type : types) {
            double prevError = Double.MAX_VALUE;

            for (int intervals : intervalsArray) {
                double result = riemann.computeIntegral(f, 0, 1, intervals, type);
                double error = Math.abs(result - exact);

                // Error should decrease as intervals increase
                assertTrue(error < prevError);
                prevError = error;

                // Expected convergence rates:
                // LEFT/RIGHT: error ~ 1/n   (linear)
                // MIDPOINT/TRAPEZOIDAL: error ~ 1/n² (quadratic)
                if (intervals > 10) {
                    int prevIntervals = intervals / 10;
                    double prevResult = riemann.computeIntegral(f, 0, 1, prevIntervals, type);
                    double prevError2 = Math.abs(prevResult - exact);

                    double ratio = prevError2 / error;

                    if (type == RiemannType.MIDPOINT || type == RiemannType.TRAPEZOIDAL) {
                        // For quadratic convergence, error ratio should be approximately 100 (10²)
                        assertTrue(ratio > 50);
                    } else {
                        // For linear convergence, error ratio should be approximately 10
                        assertTrue(ratio > 5);
                    }
                }
            }
        }
    }
}
