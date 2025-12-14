package com.thealgorithms.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for the general-purpose ProjectileMotion calculator.
 *
 */
final class ProjectileMotionTest {

    private static final double DELTA = 1e-4; // Tolerance for comparing double values

    @Test
    @DisplayName("Test ground-to-ground launch (initial height is zero)")
    void testGroundToGroundLaunch() {
        ProjectileMotion.Result result = ProjectileMotion.calculateTrajectory(50, 30, 0);
        assertEquals(5.0986, result.getTimeOfFlight(), DELTA);
        assertEquals(220.7750, result.getHorizontalRange(), DELTA);
        assertEquals(31.8661, result.getMaxHeight(), DELTA);
    }

    @Test
    @DisplayName("Test launch from an elevated position")
    void testElevatedLaunch() {
        ProjectileMotion.Result result = ProjectileMotion.calculateTrajectory(30, 45, 100);
        assertEquals(7.1705, result.getTimeOfFlight(), DELTA);
        assertEquals(152.1091, result.getHorizontalRange(), DELTA);
        assertEquals(122.9436, result.getMaxHeight(), DELTA); // Final corrected value
    }

    @Test
    @DisplayName("Test launch straight up (90 degrees)")
    void testVerticalLaunch() {
        ProjectileMotion.Result result = ProjectileMotion.calculateTrajectory(40, 90, 20);
        assertEquals(8.6303, result.getTimeOfFlight(), DELTA);
        assertEquals(0.0, result.getHorizontalRange(), DELTA);
        assertEquals(101.5773, result.getMaxHeight(), DELTA);
    }

    @Test
    @DisplayName("Test horizontal launch from a height (0 degrees)")
    void testHorizontalLaunch() {
        ProjectileMotion.Result result = ProjectileMotion.calculateTrajectory(25, 0, 80);
        assertEquals(4.0392, result.getTimeOfFlight(), DELTA);
        assertEquals(100.9809, result.getHorizontalRange(), DELTA);
        assertEquals(80.0, result.getMaxHeight(), DELTA);
    }

    @Test
    @DisplayName("Test downward launch from a height (negative angle)")
    void testDownwardLaunchFromHeight() {
        ProjectileMotion.Result result = ProjectileMotion.calculateTrajectory(20, -30, 100);
        assertEquals(3.6100, result.getTimeOfFlight(), DELTA);
        assertEquals(62.5268, result.getHorizontalRange(), DELTA);
        assertEquals(100.0, result.getMaxHeight(), DELTA);
    }

    @Test
    @DisplayName("Test invalid arguments throw an exception")
    void testInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> ProjectileMotion.calculateTrajectory(-10, 45, 100));
        assertThrows(IllegalArgumentException.class, () -> ProjectileMotion.calculateTrajectory(10, 45, -100));
        assertThrows(IllegalArgumentException.class, () -> ProjectileMotion.calculateTrajectory(10, 45, 100, 0));
    }
}
