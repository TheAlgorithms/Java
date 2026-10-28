package com.thealgorithms.conversions;

/**
 * A utility class to convert between Cartesian and Polar coordinate systems.
 *
 * <p>This class provides methods to perform the following conversions:
 * <ul>
 *   <li>Cartesian to Polar coordinates</li>
 *   <li>Polar to Cartesian coordinates</li>
 * </ul>
 *
 * <p>The class is final and cannot be instantiated.
 */
public final class CoordinateConverter {

    private CoordinateConverter() {
        // Prevent instantiation
    }

    /**
     * Converts Cartesian coordinates to Polar coordinates.
     *
     * @param x the x-coordinate in the Cartesian system; must be a finite number
     * @param y the y-coordinate in the Cartesian system; must be a finite number
     * @return an array where the first element is the radius (r) and the second element is the angle (theta) in degrees
     * @throws IllegalArgumentException if x or y is not a finite number
     */
    public static double[] cartesianToPolar(double x, double y) {
        if (!Double.isFinite(x) || !Double.isFinite(y)) {
            throw new IllegalArgumentException("x and y must be finite numbers.");
        }
        double r = Math.sqrt(x * x + y * y);
        double theta = Math.toDegrees(Math.atan2(y, x));
        return new double[] {r, theta};
    }

    /**
     * Converts Polar coordinates to Cartesian coordinates.
     *
     * @param r the radius in the Polar system; must be non-negative
     * @param thetaDegrees the angle (theta) in degrees in the Polar system; must be a finite number
     * @return an array where the first element is the x-coordinate and the second element is the y-coordinate in the Cartesian system
     * @throws IllegalArgumentException if r is negative or thetaDegrees is not a finite number
     */
    public static double[] polarToCartesian(double r, double thetaDegrees) {
        if (r < 0) {
            throw new IllegalArgumentException("Radius (r) must be non-negative.");
        }
        if (!Double.isFinite(thetaDegrees)) {
            throw new IllegalArgumentException("Theta (angle) must be a finite number.");
        }
        double theta = Math.toRadians(thetaDegrees);
        double x = r * Math.cos(theta);
        double y = r * Math.sin(theta);
        return new double[] {x, y};
    }
}
