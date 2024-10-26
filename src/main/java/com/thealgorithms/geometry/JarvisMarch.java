package com.thealgorithms.geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class implements the Jarvis March algorithm (also known as the Gift Wrapping algorithm)
 * for computing the convex hull of a set of points in a 2D plane.
 * The convex hull is the smallest convex polygon that can enclose all given points.
 */
public final class JarvisMarch {

    private JarvisMarch() {
        // Private constructor to prevent instantiation
    }

    /**
     * Represents a point in 2D space with x and y coordinates.
     */
    static class Point {
        private double x;
        private double y;

        /**
         * Constructs a Point with specified x and y coordinates.
         *
         * @param x the x-coordinate of the point
         * @param y the y-coordinate of the point
         */
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        @Override
        public boolean equals(Object obj) {
            // Check if both references point to the same object
            if (this == obj) {
                return true;
            }
            // Check if obj is an instance of Point
            if (!(obj instanceof Point)) {
                return false;
            }
            Point other = (Point) obj;
            // Compare x and y coordinates for equality
            return Double.compare(x, other.x) == 0 && Double.compare(y, other.y) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y); // Generate hash code based on x and y coordinates
        }
    }

    /**
     * Computes the convex hull of a given list of points using the Jarvis March algorithm.
     *
     * @param points a list of Points for which to compute the convex hull
     * @return a list of Points representing the vertices of the convex hull in counter-clockwise order
     */
    public static List<Point> jarvisMarch(List<Point> points) {
        List<Point> hull = new ArrayList<>();

        // If there are less than 3 points, a convex hull cannot be formed
        if (points.size() < 3) {
            return hull;
        }

        // Find the leftmost point (with the smallest x-coordinate)
        Point leftmost = points.get(0);
        for (Point p : points) {
            if (p.getX() < leftmost.getX()) {
                leftmost = p; // Update leftmost point if a new leftmost point is found
            }
        }

        Point current = leftmost; // Start from the leftmost point

        do {
            hull.add(current); // Add current point to the hull

            Point nextTarget = points.get(0); // Initialize next target as first point in list

            for (Point candidate : points) {
                // Skip current point
                if (candidate.equals(current)) {
                    continue;
                }

                // Check if candidate makes a left turn or is collinear and farther than nextTarget
                if (nextTarget.equals(current) || isLeftTurn(current, nextTarget, candidate) || (isCollinear(current, nextTarget, candidate) && distance(current, candidate) > distance(current, nextTarget))) {
                    nextTarget = candidate; // Update next target if conditions are met
                }
            }

            current = nextTarget; // Move to the next target point

        } while (!current.equals(leftmost)); // Continue until we loop back to the starting point

        return hull; // Return the computed convex hull
    }

    /**
     * Determines whether moving from point A to point B to point C makes a left turn.
     *
     * @param a the starting point
     * @param b the second point
     * @param c the third point
     * @return true if it makes a left turn, false otherwise
     */
    private static boolean isLeftTurn(Point a, Point b, Point c) {
        return (b.getX() - a.getX()) * (c.getY() - a.getY()) - (b.getY() - a.getY()) * (c.getX() - a.getX()) > 0;
    }

    /**
     * Checks whether three points A, B, and C are collinear.
     *
     * @param a the first point
     * @param b the second point
     * @param c the third point
     * @return true if points are collinear, false otherwise
     */
    private static boolean isCollinear(Point a, Point b, Point c) {
        return (b.getX() - a.getX()) * (c.getY() - a.getY()) == (b.getY() - a.getY()) * (c.getX() - a.getX());
    }

    /**
     * Calculates the Euclidean distance between two points A and B.
     *
     * @param a the first point
     * @param b the second point
     * @return the distance between points A and B
     */
    private static double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
    }
}
