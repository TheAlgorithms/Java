package com.thealgorithms.geometry;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code MidpointCircle} class implements the Midpoint Circle algorithm,
 * which is an efficient way to determine the points of a circle
 * centered at a given point with a specified radius in a 2D space.
 *
 * <p>This algorithm uses integer arithmetic to calculate the points,
 * making it suitable for rasterization in computer graphics.</p>
 */
public final class MidpointCircle {

    private MidpointCircle() {
        // Private constructor to prevent instantiation.
    }

    /**
     * Finds the list of points that form a circle centered at (xc, yc)
     * with a given radius r.
     *
     * @param xc the x-coordinate of the center point
     * @param yc the y-coordinate of the center point
     * @param r  the radius of the circle
     * @return a {@code List<Point>} containing all points on the circle
     */
    public static List<Point> drawCircle(int xc, int yc, int r) {
        List<Point> circlePoints = new ArrayList<>();

        int x = 0;
        int y = r;
        int p = 1 - r; // Initial decision parameter

        // Function to add points based on symmetry
        addCirclePoints(circlePoints, xc, yc, x, y);

        while (x < y) {
            x++;

            // Update decision parameter
            if (p < 0) {
                p += 2 * x + 1; // Midpoint is inside the circle
            } else {
                y--; // Midpoint is outside the circle
                p += 2 * x - 2 * y + 1;
            }

            // Add points based on symmetry
            addCirclePoints(circlePoints, xc, yc, x, y);
        }

        return circlePoints; // Return the list of points forming the circle
    }

    /**
     * Adds points to the list based on symmetry in all octants.
     *
     * @param points the list of points to add to
     * @param xc    the x-coordinate of the center point
     * @param yc    the y-coordinate of the center point
     * @param x     current x coordinate in circle drawing
     * @param y     current y coordinate in circle drawing
     */
    private static void addCirclePoints(List<Point> points, int xc, int yc, int x, int y) {
        points.add(new Point(xc + x, yc + y));
        points.add(new Point(xc - x, yc + y));
        points.add(new Point(xc + x, yc - y));
        points.add(new Point(xc - x, yc - y));
        points.add(new Point(xc + y, yc + x));
        points.add(new Point(xc - y, yc + x));
        points.add(new Point(xc + y, yc - x));
        points.add(new Point(xc - y, yc - x));
    }
}