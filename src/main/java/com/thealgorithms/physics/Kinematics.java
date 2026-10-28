package com.thealgorithms.physics;
/**
 * Implements the fundamental "SUVAT" equations for motion
 * under constant acceleration.
 *
 * @author [Priyanshu Kumar Singh](https://github.com/Priyanshu1303d)
 * @see <a href="https://en.wikipedia.org/wiki/Equations_of_motion#Uniform_acceleration">Wikipedia</a>
 */
public final class Kinematics {
    private Kinematics() {
    }

    /**
     * Calculates the final velocity (v) of an object.
     * Formula: v = u + at
     *
     * @param u Initial velocity (m/s).
     * @param a Constant acceleration (m/s^2).
     * @param t Time elapsed (s).
     * @return The final velocity (m/s).
     */

    public static double calculateFinalVelocity(double u, double a, double t) {
        return u + a * t;
    }

    /**
     * Calculates the displacement (s) of an object.
     * Formula: s = ut + 0.5 * a * t^2
     *
     * @param u Initial velocity (m/s).
     * @param a Constant acceleration (m/s^2).
     * @param t Time elapsed (s).
     * @return The displacement (m).
     */

    public static double calculateDisplacement(double u, double a, double t) {
        return u * t + 0.5 * a * t * t;
    }

    /**
     * Calculates the displacement (s) of an object.
     * Formula: v^2 = u^2 + 2 * a * s
     *
     * @param u Initial velocity (m/s).
     * @param a Constant acceleration (m/s^2).
     * @param s Displacement (m).
     * @return The final velocity squared (m/s)^2.
     */

    public static double calculateFinalVelocitySquared(double u, double a, double s) {
        return u * u + 2 * a * s;
    }

    /**
     * Calculates the displacement (s) using the average velocity.
     * Formula: s = (u + v) / 2 * t
     *
     * @param u Initial velocity (m/s).
     * @param v Final velocity (m/s).
     * @param t Time elapsed (s).
     * @return The displacement (m).
     */

    public static double calculateDisplacementFromVelocities(double u, double v, double t) {
        double velocitySum = u + v;
        return velocitySum / 2 * t;
    }
}
