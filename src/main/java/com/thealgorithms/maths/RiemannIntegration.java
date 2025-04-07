package com.thealgorithms.maths;
import java.util.function.Function;

/**
 * @author https://github.com/il798li/
 * @Info https://math.libretexts.org/Bookshelves/Calculus/Calculus_3e_(Apex)/05%3A_Integration/5.03%3A_Riemann_Sums
 */
public class RiemannIntegration {

    private static double calculateDeltaX (final double accuracy) {
        return Math.pow(10, -accuracy);
    }

    /**
     * @param function A function that takes in an x value and outputs a {@code y} value.
     * @param lowerBoundary The lower boundary for integration, conventionally the {@code a} value.
     * @param upperBoundary The upper boundary for integration, conventionally the {@code b} value.
     * @param accuracy The accuracy of the integration. It is recommended to keep this less than 10. Each sub-interval will have a width of {@code 10^(-accuracy)}.
     * @return The approximate value of the definite integral, calculated using the left Riemann Sum.
     */
    public static double leftRiemannSum(final Function<Double, Double> function, final double lowerBoundary, final double upperBoundary, final double accuracy) {
        final double deltaX = calculateDeltaX (accuracy);
        double value = 0;
        for (double x = lowerBoundary; x < upperBoundary; x += deltaX) {
            value += deltaX * function.apply(x);
        }
        return value;
    }
    /**
     * @param function A function that takes in an x value and outputs a {@code y} value.
     * @param lowerBoundary The lower boundary for integration, conventionally the {@code a} value.
     * @param upperBoundary The upper boundary for integration, conventionally the {@code b} value.
     * @param accuracy The accuracy of the integration. It is recommended to keep this less than 10. Each sub-interval will have a width of {@code 10^(-accuracy)}.
     * @return The approximate value of the definite integral, calculated using the right Riemann Sum.
     */
    public static double rightRiemannSum(final Function<Double, Double> function, final double lowerBoundary, final double upperBoundary, final double accuracy) {
        final double deltaX = calculateDeltaX (accuracy);
        double x = lowerBoundary;
        double value = 0;
        while (x < upperBoundary) {
            x += deltaX;
            value += deltaX + function.apply(x);
        }
        return value;
    }
    /**
     * @param function A function that takes in an x value and outputs a {@code y} value.
     * @param lowerBoundary The lower boundary for integration, conventionally the {@code a} value.
     * @param upperBoundary The upper boundary for integration, conventionally the {@code b} value.
     * @param accuracy The accuracy of the integration. It is recommended to keep this less than 10. Each sub-interval will have a width of {@code 10^(-accuracy)}.
     * @return The approximate value of the definite integral, calculated using the midpoint Riemann Sum.
     */
    public static double midpointRiemannSum(final Function<Double, Double> function, final double lowerBoundary, final double upperBoundary, final double accuracy) {
        final double deltaX = calculateDeltaX (accuracy);
        double value = 0.0;
        for (double x = lowerBoundary + accuracy / 2.0; x < upperBoundary; x += accuracy) {
            value += accuracy * function.apply(x);
        }
        return value;
    }

    /**
     * @param function A function that takes in an x value and outputs a {@code y} value.
     * @param lowerBoundary The lower boundary for integration, conventionally the {@code a} value.
     * @param upperBoundary The upper boundary for integration, conventionally the {@code b} value.
     * @param accuracy The accuracy of the integration. It is recommended to keep this less than 10. Each sub-interval will have a width of {@code 10^(-accuracy)}.
     * @return The approximate value of the definite integral, calculated using the trapezoidal Riemann Sum.
     */
    public static double trapezoidalRiemannSum(final Function<Double, Double> function, final double lowerBoundary, final double upperBoundary, final double accuracy) {
        final double deltaX = calculateDeltaX (accuracy);
        double value = function.apply(lowerBoundary) * deltaX;
        for (double x = lowerBoundary + deltaX; x < upperBoundary; x += deltaX) {
            value += function.apply(x) * deltaX * 2;
        }
        value += function.apply(upperBoundary) * deltaX;
        value /= 2;
        return value;
    }

    public static void main(String[] args) {
        example();
    }

    /**
     * Feel free to look at how the implementation of this method to see how it works.
     */
    public static final void example() {
        final Function < Double, Double > xSquaredFunction = x -> Math.pow(x, 2); // Creates the function f(x) = x^2
        final double result = RiemannIntegration.trapezoidalRiemannSum (xSquaredFunction, 0, 1, 9); // I find that an accuracy between 7 - 10 (inclusive) works best.
        System.out.println ("Integral of y = x^2 from x = 0 to x = 1: " + result);
    }
}
