package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ConvexHullTest {

    @Test
    void testConvexHullBruteForce() {
        List<Point> points = Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(10, 1));
        List<Point> expected = Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(10, 1));
        assertEquals(expected, ConvexHull.convexHullBruteForce(points));

        points = Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(10, 0));
        expected = Arrays.asList(new Point(0, 0), new Point(10, 0));
        assertEquals(expected, ConvexHull.convexHullBruteForce(points));

        points = Arrays.asList(new Point(0, 3), new Point(2, 2), new Point(1, 1), new Point(2, 1), new Point(3, 0), new Point(0, 0), new Point(3, 3), new Point(2, -1), new Point(2, -4), new Point(1, -3));
        expected = Arrays.asList(new Point(2, -4), new Point(1, -3), new Point(0, 0), new Point(3, 0), new Point(0, 3), new Point(3, 3));
        assertEquals(expected, ConvexHull.convexHullBruteForce(points));
    }

    @Test
    void testConvexHullRecursive() {
        List<Point> points = Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(10, 1));
        List<Point> expected = Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(10, 1));
        assertEquals(expected, ConvexHull.convexHullRecursive(points));

        points = Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(10, 0));
        expected = Arrays.asList(new Point(0, 0), new Point(10, 0));
        assertEquals(expected, ConvexHull.convexHullRecursive(points));

        points = Arrays.asList(new Point(0, 3), new Point(2, 2), new Point(1, 1), new Point(2, 1), new Point(3, 0), new Point(0, 0), new Point(3, 3), new Point(2, -1), new Point(2, -4), new Point(1, -3));
        expected = Arrays.asList(new Point(2, -4), new Point(1, -3), new Point(0, 0), new Point(3, 0), new Point(0, 3), new Point(3, 3));
        assertEquals(expected, ConvexHull.convexHullRecursive(points));
    }
}
