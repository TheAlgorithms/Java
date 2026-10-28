package com.thealgorithms.physics;

/**
 * Implements Coulomb's Law for electrostatics.
 * Provides simple static methods to calculate electrostatic force and circular orbit velocity.
 *
 * @author [Priyanshu Singh](https://github.com/Priyanshu1303d)
 * @see <a href="https://en.wikipedia.org/wiki/Coulomb%27s_law">Wikipedia</a>
 */
public final class CoulombsLaw {

    /** Coulomb's constant in N·m²/C² */
    public static final double COULOMBS_CONSTANT = 8.9875517923e9;

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private CoulombsLaw() {
    }

    /**
     * Calculates the electrostatic force vector exerted by one charge on another.
     * The returned vector is the force *on* the second charge (q2).
     *
     * @param q1 Charge of the first particle (in Coulombs).
     * @param x1 X-position of the first particle (m).
     * @param y1 Y-position of the first particle (m).
     * @param q2 Charge of the second particle (in Coulombs).
     * @param x2 X-position of the second particle (m).
     * @param y2 Y-position of the second particle (m).
     * @return A double array `[fx, fy]` representing the force vector on the second charge.
     */
    public static double[] calculateForceVector(double q1, double x1, double y1, double q2, double x2, double y2) {
        // Vector from 1 to 2
        double dx = x2 - x1;
        double dy = y2 - y1;
        double distanceSq = dx * dx + dy * dy;

        // If particles are at the same position, force is zero to avoid division by zero.
        if (distanceSq == 0) {
            return new double[] {0, 0};
        }

        double distance = Math.sqrt(distanceSq);
        // Force magnitude: k * (q1 * q2) / r^2
        // A positive result is repulsive (pushes q2 away from q1).
        // A negative result is attractive (pulls q2 toward q1).
        double forceMagnitude = COULOMBS_CONSTANT * q1 * q2 / distanceSq;

        // Calculate the components of the force vector
        // (dx / distance) is the unit vector pointing from 1 to 2.
        double fx = forceMagnitude * (dx / distance);
        double fy = forceMagnitude * (dy / distance);

        return new double[] {fx, fy};
    }

    /**
     * Calculates the speed required for a stable circular orbit of a charged particle
     * around a central charge (e.g., an electron orbiting a nucleus).
     *
     * @param centralCharge The charge of the central body (in Coulombs).
     * @param orbitingCharge The charge of the orbiting body (in Coulombs).
     * @param orbitingMass The mass of the orbiting body (in kg).
     * @param radius The radius of the orbit (in m).
     * @return The orbital speed (in m/s).
     * @throws IllegalArgumentException if mass or radius are not positive.
     */
    public static double calculateCircularOrbitVelocity(double centralCharge, double orbitingCharge, double orbitingMass, double radius) {
        if (orbitingMass <= 0 || radius <= 0) {
            throw new IllegalArgumentException("Orbiting mass and radius must be positive.");
        }

        // We only need the magnitude of the force, which is always positive.
        double forceMagnitude = Math.abs(COULOMBS_CONSTANT * centralCharge * orbitingCharge) / (radius * radius);

        // F_c = m * v^2 / r  =>  v = sqrt(F_c * r / m)
        return Math.sqrt(forceMagnitude * radius / orbitingMass);
    }
}
