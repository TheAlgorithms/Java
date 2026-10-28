package com.thealgorithms.physics;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Gravitation utility class.
 */
final class GravitationTest {

    // A small tolerance (delta) for comparing floating-point numbers
    private static final double DELTA = 1e-9;
    private static final double G = Gravitation.GRAVITATIONAL_CONSTANT;

    @Test
    @DisplayName("Test gravitational force between two bodies on the x-axis")
    void testSimpleForceCalculation() {
        // Force on body 2 should be F = G*1*1 / 1^2 = G, directed towards body 1 (negative x)
        double[] forceOnB = Gravitation.calculateGravitationalForce(1.0, 0, 0, 1.0, 1, 0);
        assertArrayEquals(new double[] {-G, 0.0}, forceOnB, DELTA);

        // Force on body 1 should be equal and opposite (positive x)
        double[] forceOnA = Gravitation.calculateGravitationalForce(1.0, 1, 0, 1.0, 0, 0);
        assertArrayEquals(new double[] {G, 0.0}, forceOnA, DELTA);
    }

    @Test
    @DisplayName("Test gravitational force in a 2D plane")
    void test2DForceCalculation() {
        // Body 1 at (0,0) with mass 2kg
        // Body 2 at (3,4) with mass 1kg
        // Distance is sqrt(3^2 + 4^2) = 5 meters
        double magnitude = 2.0 * G / 25.0; // G * 2 * 1 / 5^2
        // Unit vector from 2 to 1 is (-3/5, -4/5)
        double expectedFx = magnitude * -3.0 / 5.0; // -6G / 125
        double expectedFy = magnitude * -4.0 / 5.0; // -8G / 125

        double[] forceOnB = Gravitation.calculateGravitationalForce(2.0, 0, 0, 1.0, 3, 4);
        assertArrayEquals(new double[] {expectedFx, expectedFy}, forceOnB, DELTA);
    }

    @Test
    @DisplayName("Test overlapping bodies should result in zero force")
    void testOverlappingBodies() {
        double[] force = Gravitation.calculateGravitationalForce(1000.0, 1.5, -2.5, 500.0, 1.5, -2.5);
        assertArrayEquals(new double[] {0.0, 0.0}, force, DELTA);
    }

    @Test
    @DisplayName("Test circular orbit velocity with simple values")
    void testCircularOrbitVelocity() {
        // v = sqrt(G*1/1) = sqrt(G)
        double velocity = Gravitation.calculateCircularOrbitVelocity(1.0, 1.0);
        assertEquals(Math.sqrt(G), velocity, DELTA);
    }

    @Test
    @DisplayName("Test orbital velocity with real-world-ish values (LEO)")
    void testEarthOrbitVelocity() {
        // Mass of Earth ~5.972e24 kg
        // Radius of LEO ~6,771,000 m (Earth radius + 400km)
        double earthMass = 5.972e24;
        double leoRadius = 6.771e6;
        // FIX: Updated expected value to match the high-precision calculation
        double expectedVelocity = 7672.4904;

        double velocity = Gravitation.calculateCircularOrbitVelocity(earthMass, leoRadius);
        assertEquals(expectedVelocity, velocity, 0.0001); // Use a larger delta for big numbers
    }

    @Test
    @DisplayName("Test invalid inputs for orbital velocity throw exception")
    void testInvalidOrbitalVelocityInputs() {
        assertThrows(IllegalArgumentException.class, () -> Gravitation.calculateCircularOrbitVelocity(0, 100));
        assertThrows(IllegalArgumentException.class, () -> Gravitation.calculateCircularOrbitVelocity(-1000, 100));
        assertThrows(IllegalArgumentException.class, () -> Gravitation.calculateCircularOrbitVelocity(1000, 0));
        assertThrows(IllegalArgumentException.class, () -> Gravitation.calculateCircularOrbitVelocity(1000, -100));
    }
}
