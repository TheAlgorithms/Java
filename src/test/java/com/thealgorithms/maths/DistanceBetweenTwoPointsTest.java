package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DistanceBetweenTwoPointsTest {

    @Test
    void testDistanceSimple() {
        assertEquals(5.0, DistanceBetweenTwoPoints.calculate(0, 0, 3, 4), 1e-9);
    }

    @Test
    void testDistanceNegativeCoordinates() {
        assertEquals(5.0, DistanceBetweenTwoPoints.calculate(-1, -1, 2, 3), 1e-9);
    }

    @Test
    void testSamePoint() {
        assertEquals(0.0, DistanceBetweenTwoPoints.calculate(2, 2, 2, 2), 1e-9);
    }
}
