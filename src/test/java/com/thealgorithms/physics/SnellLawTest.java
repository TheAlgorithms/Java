package com.thealgorithms.physics;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SnellLawTest {

    @Test
    public void testRefractedAngle() {
        double n1 = 1.0; // air
        double n2 = 1.5; // glass
        double theta1 = Math.toRadians(30);

        double theta2 = SnellLaw.refractedAngle(n1, n2, theta1);

        double expected = Math.asin(n1 / n2 * Math.sin(theta1));

        assertEquals(expected, theta2, 1e-12);
    }

    @Test
    public void testTotalInternalReflection() {
        double n1 = 1.5;
        double n2 = 1.0;
        double theta1 = Math.toRadians(60); // large angle

        assertThrows(IllegalArgumentException.class, () -> SnellLaw.refractedAngle(n1, n2, theta1));
    }

    @Test
    public void testNoTotalInternalReflectionAtLowAngles() {
        double n1 = 1.5;
        double n2 = 1.0;
        double theta1 = Math.toRadians(10);

        assertDoesNotThrow(() -> SnellLaw.refractedAngle(n1, n2, theta1));
    }
}
