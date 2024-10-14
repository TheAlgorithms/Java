package com.thealgorithms.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ConvexHullTest {

    @Test
    void testConvexHullBruteForce() {
        List<ConvexHull.Point> points = Arrays.asList(new ConvexHull.Point(0, 0), new ConvexHull.Point(1, 0), new ConvexHull.Point(10, 1));
        List<ConvexHull.Point> expected = Arrays.asList(new ConvexHull.Point(0, 0), new ConvexHull.Point(1, 0), new ConvexHull.Point(10, 1));
        assertEquals(expected, ConvexHull.convexHullBruteForce(points));

        points = Arrays.asList(new ConvexHull.Point(0, 0), new ConvexHull.Point(1, 0), new ConvexHull.Point(10, 0));
        expected = Arrays.asList(new ConvexHull.Point(0, 0), new ConvexHull.Point(10, 0));
        assertEquals(expected, ConvexHull.convexHullBruteForce(points));

        points = Arrays.asList(
            new ConvexHull.Point(0, 3), new ConvexHull.Point(2, 2), new ConvexHull.Point(1, 1), new ConvexHull.Point(2, 1), new ConvexHull.Point(3, 0), new ConvexHull.Point(0, 0), new ConvexHull.Point(3, 3), new ConvexHull.Point(2, -1), new ConvexHull.Point(2, -4), new ConvexHull.Point(1, -3));
        expected = Arrays.asList(new ConvexHull.Point(2, -4), new ConvexHull.Point(1, -3), new ConvexHull.Point(0, 0), new ConvexHull.Point(3, 0), new ConvexHull.Point(0, 3), new ConvexHull.Point(3, 3));
        assertEquals(expected, ConvexHull.convexHullBruteForce(points));
    }

    @Test
    void testConvexHullRecursive() {
        List<ConvexHull.Point> points = Arrays.asList(new ConvexHull.Point(0, 0), new ConvexHull.Point(1, 0), new ConvexHull.Point(10, 1));
        List<ConvexHull.Point> expected = Arrays.asList(new ConvexHull.Point(0, 0), new ConvexHull.Point(1, 0), new ConvexHull.Point(10, 1));
        assertEquals(expected, ConvexHull.convexHullRecursive(points));

        points = Arrays.asList(new ConvexHull.Point(0, 0), new ConvexHull.Point(1, 0), new ConvexHull.Point(10, 0));
        expected = Arrays.asList(new ConvexHull.Point(0, 0), new ConvexHull.Point(10, 0));
        assertEquals(expected, ConvexHull.convexHullRecursive(points));

        points = Arrays.asList(
            new ConvexHull.Point(0, 3), new ConvexHull.Point(2, 2), new ConvexHull.Point(1, 1), new ConvexHull.Point(2, 1), new ConvexHull.Point(3, 0), new ConvexHull.Point(0, 0), new ConvexHull.Point(3, 3), new ConvexHull.Point(2, -1), new ConvexHull.Point(2, -4), new ConvexHull.Point(1, -3));
        expected = Arrays.asList(new ConvexHull.Point(2, -4), new ConvexHull.Point(1, -3), new ConvexHull.Point(0, 0), new ConvexHull.Point(3, 0), new ConvexHull.Point(0, 3), new ConvexHull.Point(3, 3));
        assertEquals(expected, ConvexHull.convexHullRecursive(points));
    }
}
