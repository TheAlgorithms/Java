package com.thealgorithms.physics;

/**
 * Implements relativity theory formulae.
 * Provides simple static methods to calculate length contraction and time dilation
 * in the laboratory frame with respect to the object's own frame, and velocity
 * with respect to the moving frame.
 *
 * @see <a href="https://en.wikipedia.org/wiki/List_of_relativistic_equations">Wikipedia</a>
 */
public final class Relativity {

    /* Speed of light in m s^-1 */
    public static final double SPEED_OF_LIGHT = 299792458.0;

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Relativity() {
    }

    /**
     * Calculates the gamma parameter that is of paramount importance in relativity
     * theory. It is a dimensionless parameter that is equal to 1 for zero velocity
     * but tends to infinity when velocity approaches the speed of light.
     *
     * @param v The velocity (m/s).
     * @return The value of gamma parameter.
     */
    public static double gamma(double v) {
        if (Math.abs(v) >= SPEED_OF_LIGHT) {
            throw new IllegalArgumentException("Speed must be lower than the speed of light");
        }
        return 1.0 / Math.sqrt(1 - v * v / (SPEED_OF_LIGHT * SPEED_OF_LIGHT));
    }

    /**
     * Calculates the length of an object in the moving frame.
     *
     * @param length The length of an object in its own frame (m).
     * @param v The velocity of the object (m/s).
     * @return The length of an object in the laboratory frame (m).
     */
    public static double lengthContraction(double length, double v) {
        if (length < 0) {
            throw new IllegalArgumentException("Length must be non-negative");
        }
        return length / gamma(v);
    }

    /**
     * Calculates the time that has passed in the moving frame.
     *
     * @param length The time that has passed in the object's own frame (s).
     * @param v The velocity of the object (m/s).
     * @return The time that has passed in the laboratory frame (s).
     */
    public static double timeDilation(double time, double v) {
        if (time < 0) {
            throw new IllegalArgumentException("Time must be non-negative");
        }
        return time * gamma(v);
    }

    /**
     * Calculates the velocity with respect to the moving frame.
     *
     * @param v1 The velocity of the object with respect to laboratory frame (m/s).
     * @param v The velocity of the moving frame (m/s).
     * @return The velocity with respect to the moving frame (m/s).
     */
    public static double velocityAddition(double v1, double v) {
        if (Math.abs(v1) > SPEED_OF_LIGHT) {
            throw new IllegalArgumentException("Speed must not exceed the speed of light");
        }
        if (Math.abs(v) >= SPEED_OF_LIGHT) {
            throw new IllegalArgumentException("Frame speed must be lower than the speed of light");
        }
        return (v1 - v) / (1 - v1 * v / (SPEED_OF_LIGHT * SPEED_OF_LIGHT));
    }
}
