package com.thealgorithms.geometry;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code DDALine} class implements the Digital Differential Analyzer (DDA)
 * line drawing algorithm. It computes points along a line between two given
 * endpoints using floating-point arithmetic.
 *
 * The algorithm is straightforward but less efficient compared to
 * Bresenham’s line algorithm, since it relies on floating-point operations.
 *
 * For more information, please visit {@link https://en.wikipedia.org/wiki/Digital_differential_analyzer_(graphics_algorithm)}
 */
public final class DDALine {

    private DDALine() {
        // Prevent instantiation
    }

    /**
     * Finds the list of points forming a line between two endpoints using DDA.
     *
     * @param x0 the x-coordinate of the starting point
     * @param y0 the y-coordinate of the starting point
     * @param x1 the x-coordinate of the ending point
     * @param y1 the y-coordinate of the ending point
     * @return an unmodifiable {@code List<Point>} containing all points on the line
     */
    public static List<Point> findLine(int x0, int y0, int x1, int y1) {
        int dx = x1 - x0;
        int dy = y1 - y0;

        int steps = Math.max(Math.abs(dx), Math.abs(dy)); // number of steps

        double xIncrement = dx / (double) steps;
        double yIncrement = dy / (double) steps;

        double x = x0;
        double y = y0;

        List<Point> line = new ArrayList<>(steps + 1);

        for (int i = 0; i <= steps; i++) {
            line.add(new Point((int) Math.round(x), (int) Math.round(y)));
            x += xIncrement;
            y += yIncrement;
        }

        return line;
    }
}
