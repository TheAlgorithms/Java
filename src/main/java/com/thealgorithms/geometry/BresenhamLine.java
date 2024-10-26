package com.thealgorithms.geometry;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code BresenhamLine} class implements the Bresenham's line algorithm,
 * which is an efficient way to determine the points of a straight line
 * between two given points in a 2D space.
 *
 * <p>This algorithm uses integer arithmetic to calculate the points,
 * making it suitable for rasterization in computer graphics.</p>
 *
 * For more information, please visit {@link https://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm}
 */
public final class BresenhamLine {

    private BresenhamLine() {
        // Private constructor to prevent instantiation.
    }

    /**
     * Finds the list of points that form a straight line between two endpoints.
     *
     * @param x0 the x-coordinate of the starting point
     * @param y0 the y-coordinate of the starting point
     * @param x1 the x-coordinate of the ending point
     * @param y1 the y-coordinate of the ending point
     * @return a {@code List<Point>} containing all points on the line
     */
    public static List<Point> findLine(int x0, int y0, int x1, int y1) {
        List<Point> line = new ArrayList<>();

        // Calculate differences and steps for each axis
        int dx = Math.abs(x1 - x0); // Change in x
        int dy = Math.abs(y1 - y0); // Change in y
        int sx = (x0 < x1) ? 1 : -1; // Step in x direction
        int sy = (y0 < y1) ? 1 : -1; // Step in y direction
        int err = dx - dy; // Initial error term

        // Loop until we reach the endpoint
        while (true) {
            line.add(new Point(x0, y0)); // Add current point to the line

            // Check if we've reached the endpoint
            if (x0 == x1 && y0 == y1) {
                break; // Exit loop if endpoint is reached
            }

            // Calculate error term doubled for decision making
            final int e2 = err * 2;

            // Adjust x coordinate if necessary
            if (e2 > -dy) {
                err -= dy; // Update error term
                x0 += sx; // Move to next point in x direction
            }

            // Adjust y coordinate if necessary
            if (e2 < dx) {
                err += dx; // Update error term
                y0 += sy; // Move to next point in y direction
            }
        }

        return line; // Return the list of points forming the line
    }
}
