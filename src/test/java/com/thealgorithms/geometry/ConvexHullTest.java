package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ConvexHullTest {

    @Test
    void testConvexHullBruteForce() {
        // Test 1: Triangle with intermediate point
        List<Point> points = Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(10, 1));
        List<Point> expected = Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(10, 1));
        assertEquals(expected, ConvexHull.convexHullBruteForce(points));

        // Test 2: Collinear points
        points = Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(10, 0));
        expected = Arrays.asList(new Point(0, 0), new Point(10, 0));
        assertEquals(expected, ConvexHull.convexHullBruteForce(points));

        // Test 3: Complex polygon
        points = Arrays.asList(new Point(0, 3), new Point(2, 2), new Point(1, 1), new Point(2, 1), new Point(3, 0), new Point(0, 0), new Point(3, 3), new Point(2, -1), new Point(2, -4), new Point(1, -3));
        expected = Arrays.asList(new Point(2, -4), new Point(1, -3), new Point(0, 0), new Point(3, 0), new Point(0, 3), new Point(3, 3));
        assertEquals(expected, ConvexHull.convexHullBruteForce(points));
    }

    @Test
    void testConvexHullRecursive() {
        // Test 1: Triangle - CCW order starting from bottom-left
        // The algorithm includes (1,0) as it's detected as an extreme point
        List<Point> points = Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(10, 1));
        List<Point> result = ConvexHull.convexHullRecursive(points);
        List<Point> expected = Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(10, 1));
        assertEquals(expected, result);
        assertTrue(isCounterClockwise(result), "Points should be in counter-clockwise order");

        // Test 2: Collinear points
        points = Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(10, 0));
        result = ConvexHull.convexHullRecursive(points);
        expected = Arrays.asList(new Point(0, 0), new Point(10, 0));
        assertEquals(expected, result);

        // Test 3: Complex polygon
        // Convex hull vertices in CCW order from bottom-most point (2,-4):
        // (2,-4) -> (3,0) -> (3,3) -> (0,3) -> (0,0) -> (1,-3) -> back to (2,-4)
        points = Arrays.asList(
                new Point(0, 3), new Point(2, 2), new Point(1, 1),
                new Point(2, 1), new Point(3, 0), new Point(0, 0),
                new Point(3, 3), new Point(2, -1), new Point(2, -4),
                new Point(1, -3)
        );
        result = ConvexHull.convexHullRecursive(points);
        expected = Arrays.asList(
                new Point(2, -4),  // Bottom-most, left-most (starting point)
                new Point(3, 0),   // Right side going up
                new Point(3, 3),   // Top right corner
                new Point(0, 3),   // Top left corner
                new Point(0, 0),   // Left side coming down
                new Point(1, -3)   // Bottom section, back towards start
        );
        assertEquals(expected, result);
        assertTrue(isCounterClockwise(result), "Points should be in counter-clockwise order");
    }

    @Test
    void testConvexHullRecursiveAdditionalCases() {
        // Test 4: Square (all corners on hull)
        List<Point> points = Arrays.asList(
                new Point(0, 0), new Point(2, 0),
                new Point(2, 2), new Point(0, 2)
        );
        List<Point> result = ConvexHull.convexHullRecursive(points);
        List<Point> expected = Arrays.asList(
                new Point(0, 0), new Point(2, 0),
                new Point(2, 2), new Point(0, 2)
        );
        assertEquals(expected, result);
        assertTrue(isCounterClockwise(result), "Square points should be in CCW order");

        // Test 5: Pentagon with interior point
        points = Arrays.asList(
                new Point(0, 0), new Point(4, 0), new Point(5, 3),
                new Point(2, 5), new Point(-1, 3), new Point(2, 2) // (2,2) is interior
        );
        result = ConvexHull.convexHullRecursive(points);
        // CCW from (0,0): (0,0) -> (4,0) -> (5,3) -> (2,5) -> (-1,3)
        expected = Arrays.asList(
                new Point(0, 0), new Point(4, 0), new Point(5, 3),
                new Point(2, 5), new Point(-1, 3)
        );
        assertEquals(expected, result);
        assertTrue(isCounterClockwise(result), "Pentagon points should be in CCW order");

        // Test 6: Simple triangle (clearly convex)
        points = Arrays.asList(
                new Point(0, 0), new Point(4, 0), new Point(2, 3)
        );
        result = ConvexHull.convexHullRecursive(points);
        expected = Arrays.asList(
                new Point(0, 0), new Point(4, 0), new Point(2, 3)
        );
        assertEquals(expected, result);
        assertTrue(isCounterClockwise(result), "Triangle points should be in CCW order");
    }

    /**
     * Helper method to verify if points are in counter-clockwise order.
     * Uses the signed area method: positive area means CCW.
     */
    private boolean isCounterClockwise(List<Point> points) {
        if (points.size() < 3) {
            return true; // Less than 3 points, trivially true
        }

        long signedArea = 0;
        for (int i = 0; i < points.size(); i++) {
            Point p1 = points.get(i);
            Point p2 = points.get((i + 1) % points.size());
            signedArea += (long) p1.x() * p2.y() - (long) p2.x() * p1.y();
        }

        return signedArea > 0; // Positive signed area means counter-clockwise
    }
}
