package com.thealgorithms.randomized;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.randomized.RandomizedClosestPair.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;

public class RandomizedClosestPairTest {

    @Test
    public void testFindClosestPairDistance() {
        Point[] points = {new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(8, 8), new Point(13, 13)};
        double result = RandomizedClosestPair.findClosestPairDistance(points);
        assertEquals(Math.sqrt(2), result, 0.00001);
    }

    @Test
    public void testWithIdenticalPoints() {
        Point[] points = {new Point(0, 0), new Point(0, 0), new Point(1, 1)};
        double result = RandomizedClosestPair.findClosestPairDistance(points);
        assertEquals(0.0, result, 0.00001);
    }

    @Test
    public void testWithDistantPoints() {
        Point[] points = {new Point(0, 0), new Point(5, 0), new Point(10, 0)};
        double result = RandomizedClosestPair.findClosestPairDistance(points);
        assertEquals(5.0, result, 0.00001);
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor<RandomizedClosestPair> constructor = RandomizedClosestPair.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

    @Test
    public void testStripConditionCoverage() {
        Point[] points = {new Point(0, 0), new Point(0.001, 0.001), new Point(0.002, 0.002)};
        double result = RandomizedClosestPair.findClosestPairDistance(points);
        assertTrue(result < 0.01); // distance should be covered by strip logic
    }
}
