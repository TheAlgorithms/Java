package com.thealgorithms.maths;

/**
 * Algorithm to find length and area of an arc of a circle
 * https://en.wikipedia.org/wiki/Circular_arc
 * @author Prashal Ruchiranga
 */
public class CircularArc {

    /**
     *
     * @param degrees central angle in degrees
     * @return central angle in radians
     */
    public static double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }

    /**
     *
     * @param radius length of the radius
     * @param degrees the central angle in degrees
     * @return the length of an arc of a circle
     */
    public static double circularArcLength(double radius, double degrees) {
        return radius * degreesToRadians(degrees);
    }

    /**
     *
     * @param radius length of the radius
     * @param degrees the central angle in degrees
     * @return the area of the sector formed by an arc and the center of a circle
     */
    public static double circularArcArea(double radius, double degrees) {
        return Math.pow(radius, 2) * degreesToRadians(degrees) / 2;
    }
}
