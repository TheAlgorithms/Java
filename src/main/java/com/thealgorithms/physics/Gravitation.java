package com.thealgorithms.physics;

/**
 * Implements Newton's Law of Universal Gravitation.
 * Provides simple static methods to calculate gravitational force and circular orbit velocity.
 *
 * @author [Priyanshu Kumar Singh](https://github.com/Priyanshu1303d)
 * @see <a href="https://en.wikipedia.org/wiki/Newton%27s_law_of_universal_gravitation">Wikipedia</a>
 */
public final class Gravitation {

    /** Gravitational constant in m^3 kg^-1 s^-2 */
    public static final double GRAVITATIONAL_CONSTANT = 6.67430e-11;

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Gravitation() {
    }

    /**
     * Calculates the gravitational force vector exerted by one body on another.
     *
     * @param m1 Mass of the first body (kg).
     * @param x1 X-position of the first body (m).
     * @param y1 Y-position of the first body (m).
     * @param m2 Mass of the second body (kg).
     * @param x2 X-position of the second body (m).
     * @param y2 Y-position of the second body (m).
     * @return A double array `[fx, fy]` representing the force vector on the second body.
     */
    public static double[] calculateGravitationalForce(double m1, double x1, double y1, double m2, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        double distanceSq = dx * dx + dy * dy;

        // If bodies are at the same position, force is zero to avoid division by zero.
        if (distanceSq == 0) {
            return new double[] {0, 0};
        }

        double distance = Math.sqrt(distanceSq);
        double forceMagnitude = GRAVITATIONAL_CONSTANT * m1 * m2 / distanceSq;

        // Calculate the components of the force vector
        double fx = forceMagnitude * (dx / distance);
        double fy = forceMagnitude * (dy / distance);

        return new double[] {fx, fy};
    }

    /**
     * Calculates the speed required for a stable circular orbit.
     *
     * @param centralMass The mass of the central body (kg).
     * @param radius The radius of the orbit (m).
     * @return The orbital speed (m/s).
     * @throws IllegalArgumentException if mass or radius are not positive.
     */
    public static double calculateCircularOrbitVelocity(double centralMass, double radius) {
        if (centralMass <= 0 || radius <= 0) {
            throw new IllegalArgumentException("Mass and radius must be positive.");
        }
        return Math.sqrt(GRAVITATIONAL_CONSTANT * centralMass / radius);
    }
}
