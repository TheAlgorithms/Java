package com.thealgorithms.maths;
import java.util.function.Function;

/**
 * @author https://github.com/il798li/
 * For more information on Riemann's approximation methods for integrals, visit {@link https://en.wikipedia.org/wiki/Riemann_sum this website}
 */
public class RiemannIntegration {
    private final double deltaX;

    /**
     * Creating the integration class.
     * @param deltaX This is essentially the change in each rectangle. You ideally want a very small positive values. If you want an extremely high accuracy, use {@code Double.MIN_DOUBLE}, but be warned: this will take an extremely long time.
     * @exception IllegalArgumentException when you pass a negative value.
     */
    public RiemannIntegration (final double deltaX) {
        if (deltaX <= 0) {
            throw new IllegalArgumentException ("Accuracy must be a positive number. " + deltaX + " was passed instead.");
        }
        this.deltaX = deltaX;
    }

    /**
     * Creating the integration class. This will have good accuracy, but will take a few seconds to calculate complicated integrals.
     */
    public RiemannIntegration () {
        this(0.000000001);
    }

    /**
     * Integrates a function.
     * @param function You will need to define this function, using {@code Function<Double, Double> function = x -> {...}}.
     * @param riemannApproximationMethod Each sub-interval can use different shapes to approximate the integral. It is recommended to use Trapezoidal sum.
     * @param lowerBoundary The lower bound of where your integration will start. Conventionally, this is the {@code a} value.
     * @param upperBoundary The upper bound of where your intetgration will end. Conventionally, this is the {@code a} value.
     * @return The area under the curve between the given bounds.
     */
    public double integrate(final Function<Double, Double> function, final RiemannApproximationMethod riemannApproximationMethod, final double lowerBoundary, final double upperBoundary) {
        double value = 0;
        switch (riemannApproximationMethod) {
            case LEFT_RIEMANN_SUM: {
                for (double x = lowerBoundary; x < upperBoundary; x += deltaX) {
                    value += this.deltaX * function.apply (x);
                    x += deltaX;
                }
                break;
            }
            case RIGHT_RIEMANN_SUM: {
                double x = lowerBoundary;
                while (x < upperBoundary) {
                    x += deltaX;
                    value += this.deltaX * function.apply (x);
                }
                break;
            }
            case TRAPEZOIDAL_RIEMANN_SUM: {
                value += function.apply (lowerBoundary) * deltaX;
                for (double x = lowerBoundary + deltaX; x < upperBoundary; x += deltaX) {
                    value += function.apply (x) * deltaX * 2;
                }
                value += function.apply (upperBoundary) * deltaX;
                value /= 2;
                break;
            }
            case MIDPOINT_RIEMANN_SUM: {
                for (double x = lowerBoundary + deltaX / 2; x < upperBoundary; x += deltaX) {
                    value += deltaX * function.apply (x);
                }
                break;
            }
        }
        return value;
    }

    public enum RiemannApproximationMethod {
        LEFT_RIEMANN_SUM,
        RIGHT_RIEMANN_SUM,
        MIDPOINT_RIEMANN_SUM,
        TRAPEZOIDAL_RIEMANN_SUM
    }

    public static void main (String[] args) {
        example ();
    }


    /**
     * Feel free to look at how the implementation of this method to see how it works.
     */
    public static final void example() {
        final Function<Double, Double> xSquaredFunction = x -> Math.pow(x, 2); // Creates the function f(x) = x^2
        final RiemannApproximationMethod riemannApproximationMethod = RiemannApproximationMethod.TRAPEZOIDAL_RIEMANN_SUM; // Chooses the Trapezoidal method for approximating the integral.
        final RiemannIntegration riemannIntegration = new RiemannIntegration ();
        final double result = riemannIntegration.integrate (xSquaredFunction, riemannApproximationMethod, 0, 1); // The integral of x^2 from x = 1 to x = 2 is 1/3.
        System.out.println (result);
    }
}
