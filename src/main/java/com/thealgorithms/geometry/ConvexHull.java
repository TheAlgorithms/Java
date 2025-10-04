package com.thealgorithms.geometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class providing algorithms to compute the convex hull of a set of points
 * using the recursive (Divide-and-Conquer) method.
 *
 * Convex hull: The smallest convex polygon that contains all the given points.
 *
 * This implementation ensures that the points on the hull are returned
 * in counter-clockwise order, which is required by algorithms like
 * Rotating Calipers.
 */
public final class ConvexHull {
    private ConvexHull() {
    }

    public static List<Point> convexHullRecursive(List<Point> points) {
        if (points.size() <= 1) {
            return new ArrayList<>(points);
        }

        // Sort points by x (then by y)
        List<Point> sorted = new ArrayList<>(points);
        Collections.sort(sorted);

        List<Point> lower = new ArrayList<>();
        for (Point p : sorted) {
            while (lower.size() >= 2
                    && Point.orientation(
                                   lower.get(lower.size() - 2),
                                   lower.get(lower.size() - 1),
                                   p)
                            <= 0) {
                lower.remove(lower.size() - 1);
            }
            lower.add(p);
        }

        List<Point> upper = new ArrayList<>();
        for (int i = sorted.size() - 1; i >= 0; i--) {
            Point p = sorted.get(i);
            while (upper.size() >= 2
                    && Point.orientation(
                                   upper.get(upper.size() - 2),
                                   upper.get(upper.size() - 1),
                                   p)
                            <= 0) {
                upper.remove(upper.size() - 1);
            }
            upper.add(p);
        }

        // Remove last point of each list (duplicate with start of other list)
        lower.remove(lower.size() - 1);
        upper.remove(upper.size() - 1);

        List<Point> hull = new ArrayList<>(lower);
        hull.addAll(upper);

        return hull;
    }
}
