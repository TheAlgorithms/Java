package com.thealgorithms.geometry;

import java.util.ArrayList;
import java.util.Arrays;
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
}
