package com.thealgorithms.geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The MidpointEllipse class implements the Midpoint Ellipse Drawing Algorithm.
 * This algorithm efficiently computes the points on an ellipse by dividing it into two regions
 * and using decision parameters to determine the next point to plot.
 */
public final class MidpointEllipse {

    private MidpointEllipse() {
        // Private constructor to prevent instantiation
    }

    /**
     * Draws an ellipse using the Midpoint Ellipse Algorithm.
     *
     * @param centerX the x-coordinate of the center of the ellipse
     * @param centerY the y-coordinate of the center of the ellipse
     * @param a the length of the semi-major axis (horizontal radius)
     * @param b the length of the semi-minor axis (vertical radius)
     * @return a list of points (represented as int arrays) that form the ellipse
     */
    public static List<int[]> drawEllipse(int centerX, int centerY, int a, int b) {
        List<int[]> points = new ArrayList<>();

        // Handle degenerate cases with early returns
        if (a == 0 && b == 0) {
            points.add(new int[] {centerX, centerY}); // Only the center point
            return points;
        }

        if (a == 0) {
            // Semi-major axis is zero, create a vertical line
            for (int y = centerY - b; y <= centerY + b; y++) {
                points.add(new int[] {centerX, y});
            }
            return points; // Early return
        }

        if (b == 0) {
            // Semi-minor axis is zero, create a horizontal line
            for (int x = centerX - a; x <= centerX + a; x++) {
                points.add(new int[] {x, centerY});
            }
            return points; // Early return
        }

        // Normal case: Non-degenerate ellipse
        computeEllipsePoints(points, centerX, centerY, a, b);

        return points; // Return all calculated points of the ellipse
    }

    /**
     * Computes points of a non-degenerate ellipse using the Midpoint Ellipse Algorithm.
     *
     * @param points    the list to which points will be added
     * @param centerX  the x-coordinate of the center of the ellipse
     * @param centerY  the y-coordinate of the center of the ellipse
     * @param a        the length of the semi-major axis (horizontal radius)
     * @param b        the length of the semi-minor axis (vertical radius)
     */
    private static void computeEllipsePoints(Collection<int[]> points, int centerX, int centerY, int a, int b) {
        int x = 0; // Initial x-coordinate
        int y = b; // Initial y-coordinate

        // Region 1: Initial decision parameter
        double d1 = (b * b) - (a * a * b) + (0.25 * a * a); // Decision variable for region 1
        double dx = 2.0 * b * b * x; // Change in x
        double dy = 2.0 * a * a * y; // Change in y

        // Region 1: When the slope is less than 1
        while (dx < dy) {
            addEllipsePoints(points, centerX, centerY, x, y);

            // Update decision parameter and variables
            if (d1 < 0) {
                x++;
                dx += (2 * b * b); // Update x change
                d1 += dx + (b * b); // Update decision parameter
            } else {
                x++;
                y--;
                dx += (2 * b * b); // Update x change
                dy -= (2 * a * a); // Update y change
                d1 += dx - dy + (b * b); // Update decision parameter
            }
        }

        // Region 2: Initial decision parameter for the second region
        double d2 = b * b * (x + 0.5) * (x + 0.5) + a * a * (y - 1) * (y - 1) - a * a * b * b;

        // Region 2: When the slope is greater than or equal to 1
        while (y >= 0) {
            addEllipsePoints(points, centerX, centerY, x, y);

            // Update decision parameter and variables
            if (d2 > 0) {
                y--;
                dy -= (2 * a * a); // Update y change
                d2 += (a * a) - dy; // Update decision parameter
            } else {
                y--;
                x++;
                dx += (2 * b * b); // Update x change
                dy -= (2 * a * a); // Update y change
                d2 += dx - dy + (a * a); // Update decision parameter
            }
        }
    }

    /**
     * Adds points for all four quadrants of the ellipse based on symmetry.
     *
     * @param points    the list to which points will be added
     * @param centerX  the x-coordinate of the center of the ellipse
     * @param centerY  the y-coordinate of the center of the ellipse
     * @param x        the x-coordinate relative to the center
     * @param y        the y-coordinate relative to the center
     */
    private static void addEllipsePoints(Collection<int[]> points, int centerX, int centerY, int x, int y) {
        points.add(new int[] {centerX + x, centerY + y});
        points.add(new int[] {centerX - x, centerY + y});
        points.add(new int[] {centerX + x, centerY - y});
        points.add(new int[] {centerX - x, centerY - y});
    }
}
