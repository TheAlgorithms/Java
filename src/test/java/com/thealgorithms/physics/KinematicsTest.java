package com.thealgorithms.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Kinematics utility class.
 */

public final class KinematicsTest {
    // A small tolerance for comparing floating-point numbers
    private static final double DELTA = 1e-9;
    @Test
    @DisplayName("Test final velocity: v = u + at")
    void testCalculateFinalVelocity() {
        assertEquals(20.0, Kinematics.calculateFinalVelocity(10.0, 2.0, 5.0), DELTA);
    }

    @Test
    @DisplayName("Test displacement: s = ut + 0.5at^2")
    void testCalculateDisplacement() {
        assertEquals(75.0, Kinematics.calculateDisplacement(10.0, 2.0, 5.0), DELTA);
    }

    @Test
    @DisplayName("Test final velocity squared: v^2 = u^2 + 2as")
    void testCalculateFinalVelocitySquared() {
        assertEquals(400.0, Kinematics.calculateFinalVelocitySquared(10.0, 2.0, 75.0), DELTA);
    }

    @Test
    @DisplayName("Test displacement from average velocity: s = (u+v)/2 * t")
    void testCalculateDisplacementFromVelocities() {
        assertEquals(75.0, Kinematics.calculateDisplacementFromVelocities(10.0, 20.0, 5.0), DELTA);
    }

    @Test
    @DisplayName("Test with negative acceleration (deceleration)")
    void testDeceleration() {
        assertEquals(10.0, Kinematics.calculateFinalVelocity(30.0, -4.0, 5.0), DELTA);
        assertEquals(100.0, Kinematics.calculateDisplacement(30.0, -4.0, 5.0), DELTA);

        assertEquals(100.0, Kinematics.calculateFinalVelocitySquared(30.0, -4.0, 100.0), DELTA);
        assertEquals(100.0, Kinematics.calculateDisplacementFromVelocities(30.0, 10.0, 5.0), DELTA);
    }
}
