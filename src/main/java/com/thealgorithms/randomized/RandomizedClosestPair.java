package com.thealgorithms.randomized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Randomized Closest Pair of Points Algorithm
 *
 * Use Case:
 * - Efficiently finds the closest pair of points in a 2D plane.
 * - Applicable in computational geometry, clustering, and graphics.
 *
 * Time Complexity:
 * - Expected: O(n log n) using randomized divide and conquer
 *
 * @see <a href="https://en.wikipedia.org/wiki/Closest_pair_of_points_problem">Closest Pair of Points - Wikipedia</a>
 */
public final class RandomizedClosestPair {

    // Prevent instantiation of utility class
    private RandomizedClosestPair() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static class Point {
        public final double x;
        public final double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double findClosestPairDistance(Point[] points) {
        List<Point> shuffled = new ArrayList<>(Arrays.asList(points));
        Collections.shuffle(shuffled, new Random());

        Point[] px = shuffled.toArray(new Point[0]);
        Arrays.sort(px, Comparator.comparingDouble(p -> p.x));

        Point[] py = px.clone();
        Arrays.sort(py, Comparator.comparingDouble(p -> p.y));

        return closestPair(px, py);
    }

    private static double closestPair(Point[] px, Point[] py) {
        int n = px.length;
        if (n <= 3) {
            return bruteForce(px);
        }

        int mid = n / 2;
        Point midPoint = px[mid];

        Point[] qx = Arrays.copyOfRange(px, 0, mid);
        Point[] rx = Arrays.copyOfRange(px, mid, n);

        List<Point> qy = new ArrayList<>();
        List<Point> ry = new ArrayList<>();
        for (Point p : py) {
            if (p.x <= midPoint.x) {
                qy.add(p);
            } else {
                ry.add(p);
            }
        }

        double d1 = closestPair(qx, qy.toArray(new Point[0]));
        double d2 = closestPair(rx, ry.toArray(new Point[0]));

        double d = Math.min(d1, d2);

        List<Point> strip = new ArrayList<>();
        for (Point p : py) {
            if (Math.abs(p.x - midPoint.x) < d) {
                strip.add(p);
            }
        }

        return Math.min(d, stripClosest(strip, d));
    }

    private static double bruteForce(Point[] points) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                min = Math.min(min, distance(points[i], points[j]));
            }
        }
        return min;
    }

    private static double stripClosest(List<Point> strip, double d) {
        double min = d;
        int n = strip.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n && (strip.get(j).y - strip.get(i).y) < min; j++) {
                min = Math.min(min, distance(strip.get(i), strip.get(j)));
            }
        }
        return min;
    }

    private static double distance(Point p1, Point p2) {
        return Math.hypot(p1.x - p2.x, p1.y - p2.y);
    }
}
