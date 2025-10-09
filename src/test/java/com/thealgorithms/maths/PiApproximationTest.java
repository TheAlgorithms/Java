package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class PiApproximationTest {

    private static final double DELTA = 0.5; // Tolerance for Pi approximation
    private static final double TIGHT_DELTA = 0.1; // Tighter tolerance for large samples

    /**
     * Test with known points that are all inside the quarter circle.
     */
    @Test
    public void testAllPointsInside() {
        List<PiApproximation.Point> points = new ArrayList<>();
        points.add(new PiApproximation.Point(0.0, 0.0)); // Origin
        points.add(new PiApproximation.Point(0.5, 0.5)); // Inside
        points.add(new PiApproximation.Point(0.3, 0.3)); // Inside

        double result = PiApproximation.approximatePi(points);
        // All points inside, so result should be 4.0
        assertEquals(4.0, result, 0.001);
    }

    /**
     * Test with known points that are all outside the quarter circle.
     */
    @Test
    public void testAllPointsOutside() {
        List<PiApproximation.Point> points = new ArrayList<>();
        points.add(new PiApproximation.Point(1.0, 1.0)); // Corner - outside
        points.add(new PiApproximation.Point(0.9, 0.9)); // Outside

        double result = PiApproximation.approximatePi(points);
        // No points inside, so result should be 0.0
        assertEquals(0.0, result, 0.001);
    }

    /**
     * Test with mixed points (some inside, some outside).
     */
    @Test
    public void testMixedPoints() {
        List<PiApproximation.Point> points = new ArrayList<>();
        // Inside points
        points.add(new PiApproximation.Point(0.0, 0.0));
        points.add(new PiApproximation.Point(0.5, 0.5));
        // Outside points
        points.add(new PiApproximation.Point(1.0, 1.0));
        points.add(new PiApproximation.Point(0.9, 0.9));

        double result = PiApproximation.approximatePi(points);
        // 2 out of 4 points inside: 4 * 2/4 = 2.0
        assertEquals(2.0, result, 0.001);
    }

    /**
     * Test with boundary point (on the circle).
     */
    @Test
    public void testBoundaryPoint() {
        List<PiApproximation.Point> points = new ArrayList<>();
        points.add(new PiApproximation.Point(1.0, 0.0)); // On circle: x² + y² = 1
        points.add(new PiApproximation.Point(0.0, 1.0)); // On circle

        double result = PiApproximation.approximatePi(points);
        // Boundary points should be counted as inside (≤ 1)
        assertEquals(4.0, result, 0.001);
    }

    /**
     * Test with small random sample (moderate accuracy expected).
     */
    @Test
    public void testSmallRandomSample() {
        List<PiApproximation.Point> points = PiApproximation.generateRandomPoints(1000);
        double result = PiApproximation.approximatePi(points);

        // With 1000 points, result should be reasonably close to π
        assertEquals(Math.PI, result, DELTA);
    }

    /**
     * Test with large random sample (better accuracy expected).
     */
    @Test
    public void testLargeRandomSample() {
        List<PiApproximation.Point> points = PiApproximation.generateRandomPoints(100000);
        double result = PiApproximation.approximatePi(points);

        // With 100000 points, result should be very close to π
        assertEquals(Math.PI, result, TIGHT_DELTA);
    }

    /**
     * Test that result is always positive.
     */
    @Test
    public void testResultIsPositive() {
        List<PiApproximation.Point> points = PiApproximation.generateRandomPoints(1000);
        double result = PiApproximation.approximatePi(points);

        assertTrue(result >= 0, "Pi approximation should be positive");
    }

    /**
     * Test that result is bounded (0 ≤ result ≤ 4).
     */
    @Test
    public void testResultIsBounded() {
        List<PiApproximation.Point> points = PiApproximation.generateRandomPoints(1000);
        double result = PiApproximation.approximatePi(points);

        assertTrue(result >= 0 && result <= 4, "Pi approximation should be between 0 and 4");
    }

    /**
     * Test with single point inside.
     */
    @Test
    public void testSinglePointInside() {
        List<PiApproximation.Point> points = new ArrayList<>();
        points.add(new PiApproximation.Point(0.0, 0.0));

        double result = PiApproximation.approximatePi(points);
        assertEquals(4.0, result, 0.001);
    }

    /**
     * Test with single point outside.
     */
    @Test
    public void testSinglePointOutside() {
        List<PiApproximation.Point> points = new ArrayList<>();
        points.add(new PiApproximation.Point(1.0, 1.0));

        double result = PiApproximation.approximatePi(points);
        assertEquals(0.0, result, 0.001);
    }

    /**
     * Test that generated points are within valid range [0, 1].
     */
    @Test
    public void testGeneratedPointsInRange() {
        List<PiApproximation.Point> points = PiApproximation.generateRandomPoints(100);

        for (PiApproximation.Point p : points) {
            assertTrue(p.x >= 0 && p.x <= 1, "X coordinate should be between 0 and 1");
            assertTrue(p.y >= 0 && p.y <= 1, "Y coordinate should be between 0 and 1");
        }
    }

    /**
     * Test that the correct number of points are generated.
     */
    @Test
    public void testCorrectNumberOfPointsGenerated() {
        int expectedSize = 500;
        List<PiApproximation.Point> points = PiApproximation.generateRandomPoints(expectedSize);

        assertEquals(expectedSize, points.size());
    }
}
