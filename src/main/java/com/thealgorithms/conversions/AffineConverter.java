package com.thealgorithms.conversions;

/**
 * A utility class to perform affine transformations of the form:
 * y = slope * x + intercept.
 *
 * This class supports inversion and composition of affine transformations.
 * It is immutable, meaning each instance represents a fixed transformation.
 */
public final class AffineConverter {
    private final double slope;
    private final double intercept;

    /**
     * Constructs an AffineConverter with the given slope and intercept.
     *
     * @param inSlope The slope of the affine transformation.
     * @param inIntercept The intercept (constant term) of the affine transformation.
     * @throws IllegalArgumentException if either parameter is NaN.
     */
    public AffineConverter(final double inSlope, final double inIntercept) {
        if (Double.isNaN(inSlope) || Double.isNaN(inIntercept)) {
            throw new IllegalArgumentException("Slope and intercept must be valid numbers.");
        }
        slope = inSlope;
        intercept = inIntercept;
    }

    /**
     * Converts the given input value using the affine transformation:
     * result = slope * inValue + intercept.
     *
     * @param inValue The input value to convert.
     * @return The transformed value.
     */
    public double convert(final double inValue) {
        return slope * inValue + intercept;
    }

    /**
     * Returns a new AffineConverter representing the inverse of the current transformation.
     * The inverse of y = slope * x + intercept is x = (y - intercept) / slope.
     *
     * @return A new AffineConverter representing the inverse transformation.
     * @throws AssertionError if the slope is zero, as the inverse would be undefined.
     */
    public AffineConverter invert() {
        assert slope != 0.0 : "Slope cannot be zero for inversion.";
        return new AffineConverter(1.0 / slope, -intercept / slope);
    }

    /**
     * Composes this affine transformation with another, returning a new AffineConverter.
     * If this transformation is f(x) and the other is g(x), the result is f(g(x)).
     *
     * @param other Another AffineConverter to compose with.
     * @return A new AffineConverter representing the composition of the two transformations.
     */
    public AffineConverter compose(final AffineConverter other) {
        double newSlope = slope * other.slope;
        double newIntercept = slope * other.intercept + intercept;
        return new AffineConverter(newSlope, newIntercept);
    }
}
