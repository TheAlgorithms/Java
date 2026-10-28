package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
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
        points = Arrays.asList(new Point(0, 3), new Point(2, 2), new Point(1, 1), new Point(2, 1), new Point(3, 0), new Point(0, 0), new Point(3, 3), new Point(2, -1), new Point(2, -4), new Point(1, -3));
        result = ConvexHull.convexHullRecursive(points);
        expected = Arrays.asList(new Point(2, -4), new Point(3, 0), new Point(3, 3), new Point(0, 3), new Point(0, 0), new Point(1, -3));
        assertEquals(expected, result);
        assertTrue(isCounterClockwise(result), "Points should be in counter-clockwise order");
    }

    @Test
    void testConvexHullRecursiveAdditionalCases() {
        // Test 4: Square (all corners on hull)
        List<Point> points = Arrays.asList(new Point(0, 0), new Point(2, 0), new Point(2, 2), new Point(0, 2));
        List<Point> result = ConvexHull.convexHullRecursive(points);
        List<Point> expected = Arrays.asList(new Point(0, 0), new Point(2, 0), new Point(2, 2), new Point(0, 2));
        assertEquals(expected, result);
        assertTrue(isCounterClockwise(result), "Square points should be in CCW order");

        // Test 5: Pentagon with interior point
        points = Arrays.asList(new Point(0, 0), new Point(4, 0), new Point(5, 3), new Point(2, 5), new Point(-1, 3), new Point(2, 2) // (2,2) is interior
        );
        result = ConvexHull.convexHullRecursive(points);
        // CCW from (0,0): (0,0) -> (4,0) -> (5,3) -> (2,5) -> (-1,3)
        expected = Arrays.asList(new Point(0, 0), new Point(4, 0), new Point(5, 3), new Point(2, 5), new Point(-1, 3));
        assertEquals(expected, result);
        assertTrue(isCounterClockwise(result), "Pentagon points should be in CCW order");

        // Test 6: Simple triangle (clearly convex)
        points = Arrays.asList(new Point(0, 0), new Point(4, 0), new Point(2, 3));
        result = ConvexHull.convexHullRecursive(points);
        expected = Arrays.asList(new Point(0, 0), new Point(4, 0), new Point(2, 3));
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

    @Test
    void testRecursiveHullForCoverage() {
        // 1. Test the base cases of the convexHullRecursive method (covering scenarios with < 3 input points).

        // Test Case: 0 points
        List<Point> pointsEmpty = new ArrayList<>();
        List<Point> resultEmpty = ConvexHull.convexHullRecursive(pointsEmpty);
        assertTrue(resultEmpty.isEmpty(), "Should return an empty list for an empty input list");

        // Test Case: 1 point
        List<Point> pointsOne = List.of(new Point(5, 5));
        // Pass a new ArrayList because the original method modifies the input list.
        List<Point> resultOne = ConvexHull.convexHullRecursive(new ArrayList<>(pointsOne));
        List<Point> expectedOne = List.of(new Point(5, 5));
        assertEquals(expectedOne, resultOne, "Should return the single point for a single-point input");

        // Test Case: 2 points
        List<Point> pointsTwo = Arrays.asList(new Point(10, 1), new Point(0, 0));
        List<Point> resultTwo = ConvexHull.convexHullRecursive(new ArrayList<>(pointsTwo));
        List<Point> expectedTwo = Arrays.asList(new Point(0, 0), new Point(10, 1)); // Should return the two points, sorted.
        assertEquals(expectedTwo, resultTwo, "Should return the two sorted points for a two-point input");

        // 2. Test the logic for handling collinear points in the sortCounterClockwise method.

        // Construct a scenario where multiple collinear points lie on an edge of the convex hull.
        // The expected convex hull vertices are (0,0), (10,0), and (5,5).
        // When (0,0) is used as the pivot for polar angle sorting, (5,0) and (10,0) are collinear.
        // This will trigger the crossProduct == 0 branch in the sortCounterClockwise method.
        List<Point> pointsWithCollinearOnHull = Arrays.asList(new Point(0, 0), new Point(5, 0), new Point(10, 0), new Point(5, 5), new Point(2, 2));

        List<Point> resultCollinear = ConvexHull.convexHullRecursive(new ArrayList<>(pointsWithCollinearOnHull));
        List<Point> expectedCollinear = Arrays.asList(new Point(0, 0), new Point(10, 0), new Point(5, 5));

        assertEquals(expectedCollinear, resultCollinear, "Should correctly handle collinear points on the hull edge");
        assertTrue(isCounterClockwise(resultCollinear), "The result of the collinear test should be in counter-clockwise order");
    }
}
