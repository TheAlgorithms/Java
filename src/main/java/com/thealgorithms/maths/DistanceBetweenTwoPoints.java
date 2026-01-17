package com.thealgorithms.maths;

/**
 * Distance Between Two Points in 2D Space.
 *
 * <p>This class provides a method to calculate the Euclidean distance between two points in a
 * two-dimensional plane.
 *
 * <p>Formula: d = sqrt((x2 - x1)^2 + (y2 - y1)^2)
 *
 * <p>Reference: https://en.wikipedia.org/wiki/Euclidean_distance
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
            final double x1, final double y1, final double x2, final double y2) {
        final double deltaX = x2 - x1;
        final double deltaY = y2 - y1;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
