package com.thealgorithms.physics;

/**
 *
 * This implementation calculates the flight path of a projectile launched from any INITIAL HEIGHT.
 * It is a more flexible version of the ground-to-ground model.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Projectile_motion">Wikipedia - Projectile Motion</a>
 * @author [Priyanshu Kumar Singh](https://github.com/Priyanshu1303d)
 */
public final class ProjectileMotion {

    private ProjectileMotion() {
    }

    /** Standard Earth gravity constant*/
    private static final double GRAVITY = 9.80665;

    /**
     * A simple container for the results of a projectile motion calculation.
     */
    public static final class Result {
        private final double timeOfFlight;
        private final double horizontalRange;
        private final double maxHeight;

        public Result(double timeOfFlight, double horizontalRange, double maxHeight) {
            this.timeOfFlight = timeOfFlight;
            this.horizontalRange = horizontalRange;
            this.maxHeight = maxHeight;
        }

        /** @return The total time the projectile is in the air (seconds). */
        public double getTimeOfFlight() {
            return timeOfFlight;
        }

        /** @return The total horizontal distance traveled (meters). */
        public double getHorizontalRange() {
            return horizontalRange;
        }

        /** @return The maximum vertical height from the ground (meters). */
        public double getMaxHeight() {
            return maxHeight;
        }
    }

    /**
     * Calculates projectile trajectory using standard Earth gravity.
     *
     * @param initialVelocity Initial speed of the projectile (m/s).
     * @param launchAngleDegrees Launch angle from the horizontal (degrees).
     * @param initialHeight Starting height of the projectile (m).
     * @return A {@link Result} object with the trajectory data.
     */
    public static Result calculateTrajectory(double initialVelocity, double launchAngleDegrees, double initialHeight) {
        return calculateTrajectory(initialVelocity, launchAngleDegrees, initialHeight, GRAVITY);
    }

    /**
     * Calculates projectile trajectory with a custom gravity value.
     *
     * @param initialVelocity Initial speed (m/s). Must be non-negative.
     * @param launchAngleDegrees Launch angle (degrees).
     * @param initialHeight Starting height (m). Must be non-negative.
     * @param gravity Acceleration due to gravity (m/s^2). Must be positive.
     * @return A {@link Result} object with the trajectory data.
     */
    public static Result calculateTrajectory(double initialVelocity, double launchAngleDegrees, double initialHeight, double gravity) {
        if (initialVelocity < 0 || initialHeight < 0 || gravity <= 0) {
            throw new IllegalArgumentException("Velocity, height, and gravity must be non-negative, and gravity must be positive.");
        }

        double launchAngleRadians = Math.toRadians(launchAngleDegrees);
        double initialVerticalVelocity = initialVelocity * Math.sin(launchAngleRadians); // Initial vertical velocity
        double initialHorizontalVelocity = initialVelocity * Math.cos(launchAngleRadians); // Initial horizontal velocity

        // Correctly calculate total time of flight using the quadratic formula for vertical motion.
        // y(t) = y0 + initialVerticalVelocity*t - 0.5*g*t^2. We solve for t when y(t) = 0.
        double totalTimeOfFlight = (initialVerticalVelocity + Math.sqrt(initialVerticalVelocity * initialVerticalVelocity + 2 * gravity * initialHeight)) / gravity;

        // Calculate max height. If launched downwards, max height is the initial height.
        double maxHeight;
        if (initialVerticalVelocity > 0) {
            double heightGained = initialVerticalVelocity * initialVerticalVelocity / (2 * gravity);
            maxHeight = initialHeight + heightGained;
        } else {
            maxHeight = initialHeight;
        }

        double horizontalRange = initialHorizontalVelocity * totalTimeOfFlight;

        return new Result(totalTimeOfFlight, horizontalRange, maxHeight);
    }
}
