package com.thealgorithms.physics;

/**
 * Ground to ground projectile motion calculator
 *
 * Ground to ground projectile motion is when a projectile's trajectory
 * starts at the ground, reaches the apex, then falls back on the ground.
 *
 * @author [Yash Rajput](https://github.com/the-yash-rajput)
 */
public final class GroundToGroundProjectileMotion {

    private GroundToGroundProjectileMotion() {
        throw new AssertionError("No instances.");
    }

    /** Standard gravity constant (m/s^2) */
    private static final double GRAVITY = 9.80665;

    /**
     * Convert degrees to radians
     *
     * @param degrees Angle in degrees
     * @return Angle in radians
     */
    private static double degreesToRadians(double degrees) {
        return degrees * (Math.PI / 180.0);
    }

    /**
     * Calculate the time of flight
     *
     * @param initialVelocity The starting velocity of the projectile (m/s)
     * @param angle The angle that the projectile is launched at in degrees
     * @return The time that the projectile is in the air for (seconds)
     */
    public static double timeOfFlight(double initialVelocity, double angle) {
        return timeOfFlight(initialVelocity, angle, GRAVITY);
    }

    /**
     * Calculate the time of flight with custom gravity
     *
     * @param initialVelocity The starting velocity of the projectile (m/s)
     * @param angle The angle that the projectile is launched at in degrees
     * @param gravity The value used for the gravity constant (m/s^2)
     * @return The time that the projectile is in the air for (seconds)
     */
    public static double timeOfFlight(double initialVelocity, double angle, double gravity) {
        double viy = initialVelocity * Math.sin(degreesToRadians(angle));
        return 2.0 * viy / gravity;
    }

    /**
     * Calculate the horizontal distance that the projectile travels
     *
     * @param initialVelocity The starting velocity of the projectile (m/s)
     * @param angle The angle that the projectile is launched at in degrees
     * @param time The time that the projectile is in the air (seconds)
     * @return Horizontal distance that the projectile travels (meters)
     */
    public static double horizontalRange(double initialVelocity, double angle, double time) {
        double vix = initialVelocity * Math.cos(degreesToRadians(angle));
        return vix * time;
    }

    /**
     * Calculate the max height of the projectile
     *
     * @param initialVelocity The starting velocity of the projectile (m/s)
     * @param angle The angle that the projectile is launched at in degrees
     * @return The max height that the projectile reaches (meters)
     */
    public static double maxHeight(double initialVelocity, double angle) {
        return maxHeight(initialVelocity, angle, GRAVITY);
    }

    /**
     * Calculate the max height of the projectile with custom gravity
     *
     * @param initialVelocity The starting velocity of the projectile (m/s)
     * @param angle The angle that the projectile is launched at in degrees
     * @param gravity The value used for the gravity constant (m/s^2)
     * @return The max height that the projectile reaches (meters)
     */
    public static double maxHeight(double initialVelocity, double angle, double gravity) {
        double viy = initialVelocity * Math.sin(degreesToRadians(angle));
        return Math.pow(viy, 2) / (2.0 * gravity);
    }
}
