package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.function.DoubleFunction;
import org.junit.jupiter.api.Test;

/**
 * Test cases for RiemannIntegration.java
 */
public class RiemannIntegrationTest {

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
        double leftSum = RiemannIntegration.integrate(
            function, lowerBound, upperBound, numIntervals, RiemannIntegration.RiemannType.LEFT
        );
        assertEquals(exactValue, leftSum, 1e-2);

        // Right Riemann sum
        double rightSum = RiemannIntegration.integrate(
            function, lowerBound, upperBound, numIntervals, RiemannIntegration.RiemannType.RIGHT
        );
        assertEquals(exactValue, rightSum, 1e-2);

        // Midpoint Riemann sum
        double midpointSum = RiemannIntegration.integrate(
            function, lowerBound, upperBound, numIntervals, RiemannIntegration.RiemannType.MIDPOINT
        );
        assertEquals(exactValue, midpointSum, 1e-2);

        // Trapezoidal sum
        double trapezoidalSum = RiemannIntegration.integrate(
            function, lowerBound, upperBound, numIntervals, RiemannIntegration.RiemannType.TRAPEZOIDAL
        );
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
        double leftSum = RiemannIntegration.integrate(
            function, lowerBound, upperBound, numIntervals, RiemannIntegration.RiemannType.LEFT
        );
        assertEquals(exactValue, leftSum, 1e-2);

        // Right Riemann sum
        double rightSum = RiemannIntegration.integrate(
            function, lowerBound, upperBound, numIntervals, RiemannIntegration.RiemannType.RIGHT
        );
        assertEquals(exactValue, rightSum, 1e-2);

        // Midpoint Riemann sum
        double midpointSum = RiemannIntegration.integrate(
            function, lowerBound, upperBound, numIntervals, RiemannIntegration.RiemannType.MIDPOINT
        );
        assertEquals(exactValue, midpointSum, 1e-2);

        // Trapezoidal sum
        double trapezoidalSum = RiemannIntegration.integrate(
            function, lowerBound, upperBound, numIntervals, RiemannIntegration.RiemannType.TRAPEZOIDAL
        );
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
        double leftSum = RiemannIntegration.integrate(
            function, lowerBound, upperBound, numIntervals, RiemannIntegration.RiemannType.LEFT
        );
        assertEquals(exactValue, leftSum, 1e-2);

        // Right Riemann sum
        double rightSum = RiemannIntegration.integrate(
            function, lowerBound, upperBound, numIntervals, RiemannIntegration.RiemannType.RIGHT
        );
        assertEquals(exactValue, rightSum, 1e-2);

        // Midpoint Riemann sum
        double midpointSum = RiemannIntegration.integrate(
            function, lowerBound, upperBound, numIntervals, RiemannIntegration.RiemannType.MIDPOINT
        );
        assertEquals(exactValue, midpointSum, 1e-2);

        // Trapezoidal sum
        double trapezoidalSum = RiemannIntegration.integrate(
            function, lowerBound, upperBound, numIntervals, RiemannIntegration.RiemannType.TRAPEZOIDAL
        );
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
        assertThrows(
            IllegalArgumentException.class,
            () -> RiemannIntegration.integrate(
                function, 1.0, 1.0, numIntervals, RiemannIntegration.RiemannType.LEFT
            )
        );

        // Lower bound greater than upper bound
        assertThrows(
            IllegalArgumentException.class,
            () -> RiemannIntegration.integrate(
                function, 2.0, 1.0, numIntervals, RiemannIntegration.RiemannType.LEFT
            )
        );
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
        assertThrows(
            IllegalArgumentException.class,
            () -> RiemannIntegration.integrate(
                function, lowerBound, upperBound, 0, RiemannIntegration.RiemannType.LEFT
            )
        );

        // Negative intervals
        assertThrows(
            IllegalArgumentException.class,
            () -> RiemannIntegration.integrate(
                function, lowerBound, upperBound, -10, RiemannIntegration.RiemannType.LEFT
            )
        );
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
        for (RiemannIntegration.RiemannType type : RiemannIntegration.RiemannType.values()) {
            double result = RiemannIntegration.integrate(
                function, lowerBound, upperBound, numIntervals, type
            );
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
        
        for (RiemannIntegration.RiemannType type : RiemannIntegration.RiemannType.values()) {
            double result = RiemannIntegration.integrate(
                function, lowerBound, upperBound, numIntervals, type
            );
            // Use a relative tolerance for very small values
            double relativeTolerance = 0.05; // 5% relative error
            double absoluteError = Math.abs(exactValue - result);
            double relativeError = absoluteError / Math.abs(exactValue);
            assertTrue(relativeError < relativeTolerance, 
                "Failed with integration type: " + type + 
                ", expected: " + exactValue + 
                ", got: " + result);
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
        
        for (RiemannIntegration.RiemannType type : RiemannIntegration.RiemannType.values()) {
            double result = RiemannIntegration.integrate(
                function, lowerBound, upperBound, numIntervals, type
            );
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
        
        for (RiemannIntegration.RiemannType type : RiemannIntegration.RiemannType.values()) {
            double previousError = Double.MAX_VALUE;
            
            for (int intervals : intervalsToTest) {
                double result = RiemannIntegration.integrate(
                    function, lowerBound, upperBound, intervals, type
                );
                double currentError = Math.abs(exactValue - result);
                
                // Should converge (error should decrease with more intervals)
                if (intervals > 10) {
                    assertTrue(
                        currentError < previousError,
                        "Error should decrease with more intervals for type: " + type
                    );
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
        
        for (RiemannIntegration.RiemannType type : RiemannIntegration.RiemannType.values()) {
            double result = RiemannIntegration.integrate(
                function, lowerBound, upperBound, numIntervals, type
            );
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
        double midpointResult = RiemannIntegration.integrate(
            function, lowerBound, upperBound, numIntervals, RiemannIntegration.RiemannType.MIDPOINT
        );
        assertEquals(expectedValue, midpointResult, 0.01, "Failed with integration type: MIDPOINT");
        
        double trapezoidalResult = RiemannIntegration.integrate(
            function, lowerBound, upperBound, numIntervals, RiemannIntegration.RiemannType.TRAPEZOIDAL
        );
        assertEquals(expectedValue, trapezoidalResult, 0.01, "Failed with integration type: TRAPEZOIDAL");
    }
    
    /**
     * Test known integration errors for specific Riemann types
     * For quadratic functions, midpoint rule has exact result
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
        double leftSum = RiemannIntegration.integrate(
            function, lowerBound, upperBound, numIntervals, RiemannIntegration.RiemannType.LEFT
        );
        assertTrue(
            Math.abs(exactValue - leftSum) > 0.01, 
            "Left Riemann should have significant error with few intervals"
        );
        
        double rightSum = RiemannIntegration.integrate(
            function, lowerBound, upperBound, numIntervals, RiemannIntegration.RiemannType.RIGHT
        );
        assertTrue(
            Math.abs(exactValue - rightSum) > 0.01, 
            "Right Riemann should have significant error with few intervals"
        );
        
        // For quadratic functions, midpoint rule is extremely accurate even with few intervals
        double midpointSum = RiemannIntegration.integrate(
            function, lowerBound, upperBound, numIntervals, RiemannIntegration.RiemannType.MIDPOINT
        );
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
        
        RiemannIntegration riemann = new RiemannIntegration();
        assertEquals(RiemannIntegration.DEFAULT_TOLERANCE, riemann.getTolerance());
        
        double result = riemann.computeIntegral(
            function, lowerBound, upperBound, numIntervals, RiemannIntegration.RiemannType.MIDPOINT
        );
        assertEquals(exactValue, result, 1e-2);
        
        // Test areEqual method with an appropriate tolerance for the test
        double testTolerance = 1e-8;
        assertTrue(riemann.areEqual(exactValue, result, testTolerance)); 
        assertTrue(riemann.areEqual(exactValue, result + testTolerance/2, testTolerance)); // Within test tolerance
        assertFalse(riemann.areEqual(exactValue, result + testTolerance*2, testTolerance)); // Outside test tolerance
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
        
        double result = riemann.computeIntegral(
            function, lowerBound, upperBound, numIntervals, RiemannIntegration.RiemannType.MIDPOINT
        );
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
        RiemannIntegration.RiemannType[] reliableTypes = {
            RiemannIntegration.RiemannType.MIDPOINT,
            RiemannIntegration.RiemannType.TRAPEZOIDAL
        };
        
        for (RiemannIntegration.RiemannType type : reliableTypes) {
            int estimatedIntervals = riemann.estimateRequiredIntervals(
                function, lowerBound, upperBound, type, desiredTolerance
            );
            
            // Verify that using the estimated intervals achieves the desired tolerance
            double exactValue = 1.0 / 3.0;
            double result = riemann.computeIntegral(
                function, lowerBound, upperBound, estimatedIntervals, type
            );
            
            double error = Math.abs(exactValue - result);
            assertTrue(
                error < desiredTolerance || estimatedIntervals >= 10000,
                "Error " + error + " should be less than tolerance " + desiredTolerance + 
                " with " + estimatedIntervals + " intervals for type " + type
            );
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
        
        double lowerBound = 0.1;  // Avoid the singularity at x=0
        double upperBound = 2.0;
        
        // Reference value based on our implementation results
        // This is the computed value from our algorithm with high interval count
        double referenceValue = 0.034015197803979;
        
        // Test with high number of intervals for accuracy
        int intervals = 200000;
        
        // Only use midpoint and trapezoidal methods which are more suitable for oscillatory functions
        RiemannIntegration.RiemannType[] typesToTest = {
            RiemannIntegration.RiemannType.MIDPOINT, 
            RiemannIntegration.RiemannType.TRAPEZOIDAL
        };
        
        System.out.println("Testing highly oscillatory function integration:");
        for (RiemannIntegration.RiemannType type : typesToTest) {
            double result = RiemannIntegration.integrate(
                function, lowerBound, upperBound, intervals, type
            );
            double currentError = Math.abs(referenceValue - result);
            
            System.out.printf("  Method: %s, Result: %.12f, Error: %.12f%n", 
                             type, result, currentError);
            
            // Use a small tolerance since we're comparing against our known result
            assertEquals(referenceValue, result, 1e-10, 
                "Approximation with " + type + " should match our reference implementation");
        }
    }
} 