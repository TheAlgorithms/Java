package com.thealgorithms.geometry;

import java.util.List;

/**
 * Rotating Calipers algorithm to find the farthest pair of points (diameter)
 * from a convex polygon.
 *
 * Time Complexity: O(n)
 * 
 * Reference: https://cp-algorithms.com/geometry/rotating_calipers.html
 */
public final class RotatingCalipers {
    private RotatingCalipers() {}

    public static double findDiameter(List<Point> points) {
        int n = points.size();
        if (n < 2) return 0;

        int j = 1;
        double maxDist = 0;

        for (int i = 0; i < n; i++) {
            Point nextI = points.get((i + 1) % n);
            while (Point.cross(nextI.subtract(points.get(i)), points.get((j + 1) % n).subtract(points.get(j))) > 0) {
                j = (j + 1) % n;
            }
            maxDist = Math.max(maxDist, points.get(i).distance(points.get(j)));
        }

        return maxDist;
    }
}
