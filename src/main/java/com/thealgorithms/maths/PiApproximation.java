package com.thealgorithms.maths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Implementation to calculate an estimate of the number π (Pi).
 *
 * We take a random point P with coordinates (x, y) such that 0 ≤ x ≤ 1 and 0 ≤ y ≤ 1.
 * If x² + y² ≤ 1, then the point is inside the quarter disk of radius 1,
 * else the point is outside. We know that the probability of the point being
 * inside the quarter disk is equal to π/4.
 *
 *
 * @author [Yash Rajput](https://github.com/the-yash-rajput)
 */
public final class PiApproximation {

    private PiApproximation() {
        throw new AssertionError("No instances.");
    }

    /**
     * Structure representing a point with coordinates (x, y)
     * where 0 ≤ x ≤ 1 and 0 ≤ y ≤ 1.
     */
    static class Point {
        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * This function uses the points in a given list (drawn at random)
     * to return an approximation of the number π.
     *
     * @param pts List of points where each point contains x and y coordinates
     * @return An estimate of the number π
     */
    public static double approximatePi(List<Point> pts) {
        double count = 0; // Points in circle

        for (Point p : pts) {
            if ((p.x * p.x) + (p.y * p.y) <= 1) {
                count++;
            }
        }

        return 4.0 * count / pts.size();
    }

    /**
     * Generates random points for testing the Pi approximation.
     *
     * @param numPoints Number of random points to generate
     * @return List of random points
     */
    public static List<Point> generateRandomPoints(int numPoints) {
        List<Point> points = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < numPoints; i++) {
            double x = rand.nextDouble(); // Random value between 0 and 1
            double y = rand.nextDouble(); // Random value between 0 and 1
            points.add(new Point(x, y));
        }

        return points;
    }
}
