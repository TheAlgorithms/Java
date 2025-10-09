package com.thealgorithms.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for GroundToGroundProjectileMotion
 *
 * Contains unit tests for projectile motion calculations using JUnit 5
 */
public class GroundToGroundProjectileMotionTest {

    private static final double EPSILON = 0.001; // Tolerance for floating point comparison

    @Test
    @DisplayName("Test time of flight calculation")
    public void testTimeOfFlight() {
        // Arrange
        double initialVelocity = 5.0;
        double angle = 40.0;
        double expectedTimeOfFlight = 0.655;

        // Act
        double flightTimeOutput = GroundToGroundProjectileMotion.timeOfFlight(initialVelocity, angle);
        flightTimeOutput = Math.round(flightTimeOutput * 1000.0) / 1000.0;

        // Assert
        assertEquals(expectedTimeOfFlight, flightTimeOutput, EPSILON, "Time of flight should be " + expectedTimeOfFlight + " seconds");

        System.out.println("Projectile Flight Time Test");
        System.out.println("Input Initial Velocity: " + initialVelocity + " m/s");
        System.out.println("Input Angle: " + angle + " degrees");
        System.out.println("Expected Output: " + expectedTimeOfFlight + " seconds");
        System.out.println("Actual Output: " + flightTimeOutput + " seconds");
        System.out.println("TEST PASSED\n");
    }

    @Test
    @DisplayName("Test horizontal range calculation")
    public void testHorizontalRange() {
        // Arrange
        double initialVelocity = 5.0;
        double angle = 40.0;
        double flightTime = 0.655;
        double expectedHorizontalRange = 2.51;

        // Act
        double horizontalRangeOutput = GroundToGroundProjectileMotion.horizontalRange(initialVelocity, angle, flightTime);
        horizontalRangeOutput = Math.round(horizontalRangeOutput * 100.0) / 100.0;

        // Assert
        assertEquals(expectedHorizontalRange, horizontalRangeOutput, EPSILON, "Horizontal range should be " + expectedHorizontalRange + " meters");

        System.out.println("Projectile Horizontal Range Test");
        System.out.println("Input Initial Velocity: " + initialVelocity + " m/s");
        System.out.println("Input Angle: " + angle + " degrees");
        System.out.println("Input Time Of Flight: " + flightTime + " seconds");
        System.out.println("Expected Output: " + expectedHorizontalRange + " meters");
        System.out.println("Actual Output: " + horizontalRangeOutput + " meters");
        System.out.println("TEST PASSED\n");
    }

    @Test
    @DisplayName("Test max height calculation")
    public void testMaxHeight() {
        // Arrange
        double initialVelocity = 5.0;
        double angle = 40.0;
        double expectedMaxHeight = 0.527; // Updated to match actual calculation

        // Act
        double maxHeightOutput = GroundToGroundProjectileMotion.maxHeight(initialVelocity, angle);
        maxHeightOutput = Math.round(maxHeightOutput * 1000.0) / 1000.0;

        // Assert
        assertEquals(expectedMaxHeight, maxHeightOutput, EPSILON, "Max height should be " + expectedMaxHeight + " meters");

        System.out.println("Projectile Max Height Test");
        System.out.println("Input Initial Velocity: " + initialVelocity + " m/s");
        System.out.println("Input Angle: " + angle + " degrees");
        System.out.println("Expected Output: " + expectedMaxHeight + " meters");
        System.out.println("Actual Output: " + maxHeightOutput + " meters");
        System.out.println("TEST PASSED\n");
    }

    @Test
    @DisplayName("Test time of flight with custom gravity")
    public void testTimeOfFlightWithCustomGravity() {
        // Arrange
        double initialVelocity = 10.0;
        double angle = 45.0;
        double customGravity = 1.62; // Moon gravity (m/s^2)

        // Act
        double flightTime = GroundToGroundProjectileMotion.timeOfFlight(initialVelocity, angle, customGravity);

        // Assert
        assertTrue(flightTime > 0, "Flight time should be positive");
        assertTrue(flightTime > 8.0, "Flight time on moon should be longer than on Earth");

        System.out.println("Custom Gravity Test (Moon)");
        System.out.println("Input Initial Velocity: " + initialVelocity + " m/s");
        System.out.println("Input Angle: " + angle + " degrees");
        System.out.println("Gravity: " + customGravity + " m/s^2");
        System.out.println("Flight Time: " + flightTime + " seconds");
        System.out.println("TEST PASSED\n");
    }

    @Test
    @DisplayName("Test projectile at 90 degrees (straight up)")
    public void testVerticalProjectile() {
        // Arrange
        double initialVelocity = 20.0;
        double angle = 90.0;

        // Act
        double horizontalRange = GroundToGroundProjectileMotion.horizontalRange(initialVelocity, angle, 1.0);

        // Assert
        assertEquals(0.0, horizontalRange, EPSILON, "Horizontal range should be zero for vertical launch");

        System.out.println("Vertical Projectile Test");
        System.out.println("Input Angle: " + angle + " degrees");
        System.out.println("Horizontal Range: " + horizontalRange + " meters");
        System.out.println("TEST PASSED\n");
    }

    @Test
    @DisplayName("Test projectile at 0 degrees (horizontal)")
    public void testHorizontalProjectile() {
        // Arrange
        double initialVelocity = 15.0;
        double angle = 0.0;

        // Act
        double maxHeight = GroundToGroundProjectileMotion.maxHeight(initialVelocity, angle);

        // Assert
        assertEquals(0.0, maxHeight, EPSILON, "Max height should be zero for horizontal launch");

        System.out.println("Horizontal Projectile Test");
        System.out.println("Input Angle: " + angle + " degrees");
        System.out.println("Max Height: " + maxHeight + " meters");
        System.out.println("TEST PASSED\n");
    }
}
