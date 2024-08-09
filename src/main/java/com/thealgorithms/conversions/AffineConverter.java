package com.thealgorithms.conversions;

public final class AffineConverter {
    private final double slope;
    private final double intercept;
    public AffineConverter(final double inSlope, final double inIntercept) {
        slope = inSlope;
        intercept = inIntercept;
    }

    public double convert(final double inValue) {
        return slope * inValue + intercept;
    }

    public AffineConverter invert() {
        assert slope != 0.0;
        return new AffineConverter(1.0 / slope, -intercept / slope);
    }

    public AffineConverter compose(final AffineConverter other) {
        return new AffineConverter(slope * other.slope, slope * other.intercept + intercept);
    }
}
