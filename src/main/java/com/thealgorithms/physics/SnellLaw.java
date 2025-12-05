package com.thealgorithms.physics;

/**
 * Calculates refraction angle using Snell's Law:
 * n1 * sin(theta1) = n2 * sin(theta2)
 * @see <a href="https://en.wikipedia.org/wiki/Snell%27s_law">Snell's Law</a>
 */
public final class SnellLaw {

    private SnellLaw() {
        throw new AssertionError("No instances.");
    }

    /**
     * Computes the refracted angle (theta2) in radians.
     *
     * @param n1 index of refraction of medium 1
     * @param n2 index of refraction of medium 2
     * @param theta1 incident angle in radians
     * @return refracted angle (theta2) in radians
     * @throws IllegalArgumentException if total internal reflection occurs
     */
    public static double refractedAngle(double n1, double n2, double theta1) {
        double ratio = n1 / n2;
        double sinTheta2 = ratio * Math.sin(theta1);

        if (Math.abs(sinTheta2) > 1.0) {
            throw new IllegalArgumentException("Total internal reflection: no refraction possible.");
        }

        return Math.asin(sinTheta2);
    }
}
