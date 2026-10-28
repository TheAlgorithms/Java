package com.thealgorithms.physics;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the CoulombsLaw utility class.
 */
final class CoulombsLawTest {

    // A small tolerance (delta) for comparing floating-point numbers
    private static final double DELTA = 1e-9;
    private static final double K = CoulombsLaw.COULOMBS_CONSTANT;

    @Test
    @DisplayName("Test repulsive force between two charges on the x-axis")
    void testSimpleRepulsiveForce() {
        // Two positive 1C charges, 1 meter apart.
        // Force on q2 should be F = K*1*1 / 1^2 = K, directed away from q1 (positive x)
        double[] forceOnB = CoulombsLaw.calculateForceVector(1.0, 0, 0, 1.0, 1, 0);
        assertArrayEquals(new double[] {K, 0.0}, forceOnB, DELTA);

        // Force on q1 should be equal and opposite (negative x)
        double[] forceOnA = CoulombsLaw.calculateForceVector(1.0, 1, 0, 1.0, 0, 0);
        assertArrayEquals(new double[] {-K, 0.0}, forceOnA, DELTA);
    }

    @Test
    @DisplayName("Test attractive force between two charges on the x-axis")
    void testSimpleAttractiveForce() {
        // One positive 1C, one negative -1C, 1 meter apart.
        // Force on q2 should be F = K*1*(-1) / 1^2 = -K, directed toward q1 (negative x)
        double[] forceOnB = CoulombsLaw.calculateForceVector(1.0, 0, 0, -1.0, 1, 0);
        assertArrayEquals(new double[] {-K, 0.0}, forceOnB, DELTA);
    }

    @Test
    @DisplayName("Test electrostatic force in a 2D plane (repulsive)")
    void test2DRepulsiveForce() {
        // q1 at (0,0) with charge +2C
        // q2 at (3,4) with charge +1C
        // Distance is 5 meters.
        double magnitude = K * 2.0 * 1.0 / 25.0; // 2K/25
        // Unit vector from 1 to 2 is (3/5, 4/5)
        double expectedFx = magnitude * (3.0 / 5.0); // 6K / 125
        double expectedFy = magnitude * (4.0 / 5.0); // 8K / 125

        double[] forceOnB = CoulombsLaw.calculateForceVector(2.0, 0, 0, 1.0, 3, 4);
        assertArrayEquals(new double[] {expectedFx, expectedFy}, forceOnB, DELTA);
    }

    @Test
    @DisplayName("Test overlapping charges should result in zero force")
    void testOverlappingCharges() {
        double[] force = CoulombsLaw.calculateForceVector(1.0, 1.5, -2.5, -1.0, 1.5, -2.5);
        assertArrayEquals(new double[] {0.0, 0.0}, force, DELTA);
    }

    @Test
    @DisplayName("Test circular orbit velocity with simple values")
    void testCircularOrbitVelocity() {
        // v = sqrt( (K*1*1 / 1^2) * 1 / 1 ) = sqrt(K)
        double velocity = CoulombsLaw.calculateCircularOrbitVelocity(1.0, 1.0, 1.0, 1.0);
        assertEquals(Math.sqrt(K), velocity, DELTA);
    }

    @Test
    @DisplayName("Test orbital velocity for a Hydrogen atom (Bohr model)")
    void testHydrogenAtomVelocity() {
        // Charge of a proton
        double protonCharge = 1.602176634e-19;
        // Charge of an electron
        double electronCharge = -1.602176634e-19;
        // Mass of an electron
        double electronMass = 9.1093837e-31;
        // Bohr radius (avg distance)
        double bohrRadius = 5.29177e-11;

        double expectedVelocity = 2.1876917e6;

        double velocity = CoulombsLaw.calculateCircularOrbitVelocity(protonCharge, electronCharge, electronMass, bohrRadius);
        // Use a wider delta for this real-world calculation
        assertEquals(expectedVelocity, velocity, 1.0);
    }

    @Test
    @DisplayName("Test invalid inputs for orbital velocity throw exception")
    void testInvalidOrbitalVelocityInputs() {
        // Non-positive mass
        assertThrows(IllegalArgumentException.class, () -> CoulombsLaw.calculateCircularOrbitVelocity(1, 1, 0, 100));
        assertThrows(IllegalArgumentException.class, () -> CoulombsLaw.calculateCircularOrbitVelocity(1, 1, -1, 100));
        // Non-positive radius
        assertThrows(IllegalArgumentException.class, () -> CoulombsLaw.calculateCircularOrbitVelocity(1, 1, 1, 0));
        assertThrows(IllegalArgumentException.class, () -> CoulombsLaw.calculateCircularOrbitVelocity(1, 1, 1, -100));
    }
}
