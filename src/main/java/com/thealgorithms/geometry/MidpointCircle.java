package com.thealgorithms.geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class to represent the Midpoint Circle Algorithm.
 * This algorithm calculates points on the circumference of a circle
 * using integer arithmetic for efficient computation.
 */
public final class MidpointCircle {

    private MidpointCircle() {
        // Private Constructor to prevent instantiation.
    }

    /**
     * Generates points on the circumference of a circle using the midpoint circle algorithm.
     *
     * @param centerX The x-coordinate of the circle's center.
     * @param centerY The y-coordinate of the circle's center.
     * @param radius  The radius of the circle.
     * @return A list of points on the circle, each represented as an int[] with 2 elements [x, y].
     */
    public static List<int[]> generateCirclePoints(int centerX, int centerY, int radius) {
        List<int[]> points = new ArrayList<>();

        // Special case for radius 0, only the center point should be added.
        if (radius == 0) {
            points.add(new int[] {centerX, centerY});
            return points;
        }

        // Start at (radius, 0)
        int x = radius;
        int y = 0;

        // Decision parameter
        int p = 1 - radius;

        // Add the initial points in all octants
        addSymmetricPoints(points, centerX, centerY, x, y);

        // Iterate while x > y
        while (x > y) {
            y++;

            if (p <= 0) {
                // Midpoint is inside or on the circle
                p = p + 2 * y + 1;
            } else {
                // Midpoint is outside the circle
                x--;
                p = p + 2 * y - 2 * x + 1;
            }

            // Add points for this (x, y)
            addSymmetricPoints(points, centerX, centerY, x, y);
        }

        return points;
    }

    /**
     * Adds the symmetric points in all octants of the circle based on the current x and y values.
     *
     * @param points  The list to which symmetric points will be added.
     * @param centerX The x-coordinate of the circle's center.
     * @param centerY The y-coordinate of the circle's center.
     * @param x       The current x-coordinate on the circumference.
     * @param y       The current y-coordinate on the circumference.
     */
    private static void addSymmetricPoints(Collection<int[]> points, int centerX, int centerY, int x, int y) {
        // Octant symmetry points
        points.add(new int[] {centerX + x, centerY + y});
        points.add(new int[] {centerX - x, centerY + y});
        points.add(new int[] {centerX + x, centerY - y});
        points.add(new int[] {centerX - x, centerY - y});
        points.add(new int[] {centerX + y, centerY + x});
        points.add(new int[] {centerX - y, centerY + x});
        points.add(new int[] {centerX + y, centerY - x});
        points.add(new int[] {centerX - y, centerY - x});
    }
}
