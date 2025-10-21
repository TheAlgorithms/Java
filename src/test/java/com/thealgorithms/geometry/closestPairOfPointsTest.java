package com.thealgorithms.geometry;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class ClosestPairOfPointsTest {
    @Test
    void testClosestPair() {
        var pts = Arrays.asList(
            new Point(2, 3), new Point(12, 30),
            new Point(40, 50), new Point(5, 1),
            new Point(12, 10), new Point(3, 4)
        );
        double result = ClosestPairOfPoints.closestPair(pts);
        assertEquals(Math.sqrt(2), result, 1e-6);
    }
}
