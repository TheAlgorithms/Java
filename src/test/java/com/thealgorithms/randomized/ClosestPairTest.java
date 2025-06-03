package com.thealgorithms.randomized;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClosestPairTest {

    @Test
    void testStandardCaseClosestPair() {
        List<Point> points = Arrays.asList(new Point(1, 4), new Point(2, 8), new Point(0, 1), new Point(4, 5), new Point(9, 4));
        Object[] closestPair = ClosestPair.rabinRandomizedClosestPair(points);
        assertNotEquals(closestPair[0], closestPair[1], "Points are distinct");
        assertTrue((double) closestPair[2] > 0, "Distance must be positive");
    }

    @Test
    void testTwoDistinctPoints() {
        List<Point> points = Arrays.asList(new Point(1, 2), new Point(2, 3));
        Object[] closestPair = ClosestPair.rabinRandomizedClosestPair(points);
        assertTrue((closestPair[0].equals(points.get(0)) && closestPair[1].equals(points.get(1))) || (closestPair[1].equals(points.get(0)) && closestPair[0].equals(points.get(1))));
        assertEquals(closestPair[2], ClosestPair.euclideanDistance(points.get(0), points.get(1)));
    }

    @Test
    void testIdenticalPointsPairWithDistanceZero() {
        List<Point> points = Arrays.asList(new Point(1.0, 2.0), new Point(1.0, 2.0), new Point(1.0, 1.0));
        Object[] closestPair = ClosestPair.rabinRandomizedClosestPair(points);
        assertEquals(0, (double) closestPair[2], "Distance is zero");
    }

    @Test
    void testLargeDatasetRandomPoints() {
        List<Point> points = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            points.add(new Point(random.nextDouble() * 100, random.nextDouble() * 100));
        }
        Object[] closestPair = ClosestPair.rabinRandomizedClosestPair(points);
        assertNotNull(closestPair[0]);
        assertNotNull(closestPair[1]);
        assertTrue((double) closestPair[2] > 0, "Distance must be positive");
    }

    @Test
    void testSinglePointShouldReturnNoPair() {
        List<Point> points = Arrays.asList(new Point(5.0, 5.0));
        Object[] closestPair = ClosestPair.rabinRandomizedClosestPair(points);
        assertNull(closestPair[0]);
        assertNull(closestPair[1]);
    }
}
