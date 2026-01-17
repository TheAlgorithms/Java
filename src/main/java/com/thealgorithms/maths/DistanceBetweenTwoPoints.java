package com.thealgorithms.maths;

/**
 * Distance Between Two Points in 2D Space.
 *
 * <p>This class provides a method to calculate the Euclidean distance
 * between two points in a two-dimensional plane.</p>
 *
 * <p>Formula:
 * d = √((x2 − x1)² + (y2 − y1)²)</p>
 *
 * <p>Reference:
 * https://en.wikipedia.org/wiki/Euclidean_distance</p>
 */
public final class DistanceBetweenTwoPoints {

    private DistanceBetweenTwoPoints() {
        // Utility class; prevent instantiation
    }

    /**
     * Calculate the Euclidean distance between two points.
     *
     * @param x1 x-coordinate of the first point
     * @param y1 y-coordinate of the first point
     * @param x2 x-coordinate of the second point
     * @param y2 y-coordinate of the second point
     * @return Euclidean distance between the two points
     */
    public static double calculate(
            final double x1,
            final double y1,
            final double x2,
            final double y2) {

        return Math.sqrt(
                Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)
        );
    }
}
