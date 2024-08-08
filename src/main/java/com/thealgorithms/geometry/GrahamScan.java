package com.thealgorithms.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * A Java program that computes the convex hull using the Graham Scan algorithm.
 * The time complexity is O(n) in the best case and O(n log(n)) in the worst case.
 * The space complexity is O(n).
 * This algorithm is applicable only to integral coordinates.
 *
 * References:
 * https://github.com/TheAlgorithms/C-Plus-Plus/blob/master/geometry/graham_scan_algorithm.cpp
 * https://github.com/TheAlgorithms/C-Plus-Plus/blob/master/geometry/graham_scan_functions.hpp
 * https://algs4.cs.princeton.edu/99hull/GrahamScan.java.html
 */
public class GrahamScan {

    private final Stack<Point> hull = new Stack<>();

    public GrahamScan(Point[] points) {
        // Pre-process points: sort by y-coordinate, then by polar order with respect to the first point
        Arrays.sort(points);
        Arrays.sort(points, 1, points.length, points[0].polarOrder());

        hull.push(points[0]);

        // Find the first point not equal to points[0] (firstNonEqualIndex)
        // and the first point not collinear firstNonCollinearIndex with the previous points
        int firstNonEqualIndex;
        for (firstNonEqualIndex = 1; firstNonEqualIndex < points.length; firstNonEqualIndex++) {
            if (!points[0].equals(points[firstNonEqualIndex])) {
                break;
            }
        }

        if (firstNonEqualIndex == points.length) {
            return;
        }

        int firstNonCollinearIndex;
        for (firstNonCollinearIndex = firstNonEqualIndex + 1; firstNonCollinearIndex < points.length; firstNonCollinearIndex++) {
            if (Point.orientation(points[0], points[firstNonEqualIndex], points[firstNonCollinearIndex]) != 0) {
                break;
            }
        }

        hull.push(points[firstNonCollinearIndex - 1]);

        // Process the remaining points and update the hull
        for (int i = firstNonCollinearIndex; i < points.length; i++) {
            Point top = hull.pop();
            while (Point.orientation(hull.peek(), top, points[i]) <= 0) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(points[i]);
        }
    }

    /**
     * @return An iterable collection of points representing the convex hull.
     */
    public Iterable<Point> hull() {
        return new ArrayList<>(hull);
    }

    public record Point(int x, int y) implements Comparable<Point> {

        /**
         * Default constructor
         * @param x x-coordinate
         * @param y y-coordinate
         */
        public Point {
        }

        /**
         * @return the x-coordinate
         */
        @Override
        public int x() {
            return x;
        }

        /**
         * @return the y-coordinate
         */
        @Override
        public int y() {
            return y;
        }

        /**
         * Determines the orientation of the triplet (a, b, c).
         *
         * @param a The first point
         * @param b The second point
         * @param c The third point
         * @return -1 if (a, b, c) is clockwise, 0 if collinear, +1 if counterclockwise
         */
        public static int orientation(Point a, Point b, Point c) {
            int val = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
            return Integer.compare(val, 0);
        }

        /**
         * Compares this point with another point.
         *
         * @param p2 The point to compare to
         * @return A positive integer if this point is greater, a negative integer if less, or 0 if equal
         */
        @Override
        public int compareTo(Point p2) {
            int cmpY = Integer.compare(this.y, p2.y);
            return cmpY != 0 ? cmpY : Integer.compare(this.x, p2.x);
        }

        /**
         * Returns a comparator to sort points by their polar order relative to this point.
         *
         * @return A polar order comparator
         */
        public Comparator<Point> polarOrder() {
            return new PolarOrder();
        }

        private final class PolarOrder implements Comparator<Point> {
            @Override
            public int compare(Point p1, Point p2) {
                int dx1 = p1.x - x;
                int dy1 = p1.y - y;
                int dx2 = p2.x - x;
                int dy2 = p2.y - y;

                if (dy1 >= 0 && dy2 < 0) {
                    return -1; // p1 above p2
                } else if (dy2 >= 0 && dy1 < 0) {
                    return 1; // p1 below p2
                } else if (dy1 == 0 && dy2 == 0) { // Collinear and horizontal
                    return Integer.compare(dx2, dx1);
                } else {
                    return -orientation(Point.this, p1, p2); // Compare orientation
                }
            }
        }

        /**
         * @return A string representation of this point in the format (x, y)
         */
        @Override
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }
    }
}
