package com.thealgorithms.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Tests the basic functionality of {@code ClosestPairSweepLine} class.
 */
public class ClosestPairSweepLineTest {

    @Test
    public void testClosestPair() {
        List<ClosestPairSweepLine.Point> points = new ArrayList<>();
        points.add(new ClosestPairSweepLine.Point(1.0, 1.0));
        points.add(new ClosestPairSweepLine.Point(2.0, 3.0));
        points.add(new ClosestPairSweepLine.Point(3.0, 4.0));
        points.add(new ClosestPairSweepLine.Point(4.0, 2.0));
        points.add(new ClosestPairSweepLine.Point(5.0, 1.0));

        double expected = Math.sqrt(2); // distance between (1.0, 1.0) and (2.0, 2.0)
        double actual = ClosestPairSweepLine.findClosestPair(points);
        System.out.print(actual + "\n" + expected + "\n");
        assertEquals(expected, actual, 0.00001, "The closest distance calculated is incorrect.");
    }

    @Test
    public void testClosestPairNegativeCoordinates() {
        List<ClosestPairSweepLine.Point> points = new ArrayList<>();
        points.add(new ClosestPairSweepLine.Point(-1.0, -1.0));
        points.add(new ClosestPairSweepLine.Point(-2.0, -3.0));
        points.add(new ClosestPairSweepLine.Point(-3.0, -4.0));
        points.add(new ClosestPairSweepLine.Point(-4.0, -2.0));
        points.add(new ClosestPairSweepLine.Point(-1.5, -1.5));

        double expected = Math.sqrt(2) / 2; // distance between (-1.0, -1.0) and (-1.5, -1.5)
        double actual = ClosestPairSweepLine.findClosestPair(points);
        assertEquals(expected, actual, 0.00001, "The closest distance calculated with negative coordinates is incorrect.");
    }

    @Test
    public void testClosestPairLargeDistances() {
        List<ClosestPairSweepLine.Point> points = new ArrayList<>();
        points.add(new ClosestPairSweepLine.Point(1000.0, 1000.0));
        points.add(new ClosestPairSweepLine.Point(2000.0, 3000.0));
        points.add(new ClosestPairSweepLine.Point(3000.0, 4000.0));
        points.add(new ClosestPairSweepLine.Point(4000.0, 2000.0));
        points.add(new ClosestPairSweepLine.Point(5000.0, 1000.0));
        points.add(new ClosestPairSweepLine.Point(1001.0, 1001.0));

        double expected = Math.sqrt(2); // distance between (1000.0, 1000.0) and (1001.0, 1001.0)
        double actual = ClosestPairSweepLine.findClosestPair(points);
        assertEquals(expected, actual, 0.00001, "The closest distance calculated with large distances is incorrect.");
    }

    @Test
    public void testClosestPairLargeNumberOfPoints() {
        List<ClosestPairSweepLine.Point> points = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            points.add(new ClosestPairSweepLine.Point(Math.random() * 10000, Math.random() * 10000));
        }

        double actual = ClosestPairSweepLine.findClosestPair(points);
        assert actual >= 0 : "The closest distance calculated with a large number of points is incorrect.";
    }

    /**
     * Tests {@code ClosestPairSweepLine.findClosestPair} with an edge case scenario
     * where points are equidistant along the edges of a square to verify the algorithm
     * handles such cases correctly.
     */
    @Test
    public void testClosestPairEdgeCase() {
        List<ClosestPairSweepLine.Point> points = new ArrayList<>();
        points.add(new ClosestPairSweepLine.Point(0.0, 0.0));
        points.add(new ClosestPairSweepLine.Point(0.0, 10.0));
        points.add(new ClosestPairSweepLine.Point(10.0, 0.0));
        points.add(new ClosestPairSweepLine.Point(10.0, 10.0));
        points.add(new ClosestPairSweepLine.Point(5.0, 5.0));
        points.add(new ClosestPairSweepLine.Point(5.1, 5.0));

        double expected = 0.1; // distance between (5.0, 5.0) and (5.1, 5.0)
        double actual = ClosestPairSweepLine.findClosestPair(points);
        assertEquals(expected, actual, 0.00001, "The closest distance calculated in edge case is incorrect.");
    }
}
