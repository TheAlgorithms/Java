package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Test class for the {@code MidpointCircle} class
 */
class MidpointCircleTest {

    /**
     * Parameterized test to check the generated points for different circles.
     * The points are checked based on the expected center and radius.
     *
     * @param centerX The x-coordinate of the circle's center.
     * @param centerY The y-coordinate of the circle's center.
     * @param radius  The radius of the circle.
     */
    @ParameterizedTest
    @CsvSource({
        "0, 0, 3", // Circle centered at (0, 0) with radius 3
        "10, 10, 2" // Circle centered at (10, 10) with radius 2
    })
    void
    testGenerateCirclePoints(int centerX, int centerY, int radius) {
        List<int[]> points = MidpointCircle.generateCirclePoints(centerX, centerY, radius);

        // Ensure that all points satisfy the circle equation (x - centerX)^2 + (y - centerY)^2 = radius^2
        for (int[] point : points) {
            int x = point[0];
            int y = point[1];

            int dx = x - centerX;
            int dy = y - centerY;
            int distanceSquared = dx * dx + dy * dy;

            assertTrue(Math.abs(distanceSquared - radius * radius) <= 1, "Point (" + x + ", " + y + ") does not satisfy the circle equation.");
        }
    }

    /**
     * Test to ensure the algorithm generates points for a zero-radius circle.
     */
    @Test
    void testZeroRadiusCircle() {
        List<int[]> points = MidpointCircle.generateCirclePoints(0, 0, 0);

        // A zero-radius circle should only have one point: (0, 0)
        assertTrue(points.size() == 1 && points.get(0)[0] == 0 && points.get(0)[1] == 0, "Zero-radius circle did not generate the correct point.");
    }
}
