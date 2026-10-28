package com.thealgorithms.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Relativity utility class.
 */
final class RelativityTest {

    // A small tolerance (delta) for comparing floating-point numbers
    private static final double DELTA = 1e-6;
    private static final double C = Relativity.SPEED_OF_LIGHT;

    @Test
    @DisplayName("Test the gamma parameter")
    void testGamma() {
        double myGamma = Relativity.gamma(0.6 * C);
        assertEquals(1.25, myGamma, DELTA);
    }

    @Test
    @DisplayName("Test the length contraction")
    void testLengthContraction() {
        double myLength = Relativity.lengthContraction(5.0, 0.8 * C);
        assertEquals(3.0, myLength, DELTA);
    }

    @Test
    @DisplayName("Test the time dilation")
    void testTimeDilation() {
        double myTime = Relativity.timeDilation(4.0, 0.6 * C);
        assertEquals(5.0, myTime, DELTA);
    }

    @Test
    @DisplayName("Test the velocity addition in the same direction")
    void testVelocityAdditionSameDirection() {
        double myVelocity = Relativity.velocityAddition(0.8 * C, 0.75 * C);
        assertEquals(0.125 * C, myVelocity, DELTA);
    }

    @Test
    @DisplayName("Test the velocity addition in different directions")
    void testVelocityAdditionDifferentDirections() {
        double myVelocity = Relativity.velocityAddition(0.8 * C, -0.75 * C);
        assertEquals(0.96875 * C, myVelocity, DELTA);
    }

    @Test
    @DisplayName("Test the velocity addition with the speed of light")
    void testVelocityAdditionWithSpeedOfLight() {
        double myVelocity = Relativity.velocityAddition(C, 0.7 * C);
        assertEquals(C, myVelocity, DELTA);
    }

    @Test
    @DisplayName("Test invalid inputs throw exception")
    void testInvalidOrbitalVelocityInputs() {
        assertThrows(IllegalArgumentException.class, () -> Relativity.gamma(1.2 * C));
        assertThrows(IllegalArgumentException.class, () -> Relativity.gamma(-C));
        assertThrows(IllegalArgumentException.class, () -> Relativity.lengthContraction(-1.0, 0.6 * C));
        assertThrows(IllegalArgumentException.class, () -> Relativity.lengthContraction(1.0, 1.5 * C));
        assertThrows(IllegalArgumentException.class, () -> Relativity.timeDilation(-5.0, -0.8 * C));
        assertThrows(IllegalArgumentException.class, () -> Relativity.timeDilation(5.0, C));
        assertThrows(IllegalArgumentException.class, () -> Relativity.velocityAddition(0.3 * C, -C));
        assertThrows(IllegalArgumentException.class, () -> Relativity.velocityAddition(1.4 * C, 0.2 * C));
        assertThrows(IllegalArgumentException.class, () -> Relativity.velocityAddition(-0.4 * C, 1.2 * C));
    }
}
