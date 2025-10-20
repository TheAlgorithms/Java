package com.thealgorithms.geometry;

/**
 * Utility class to check if two line segments intersect.
 *
 * <p>This class provides methods to determine whether two given line segments
 * intersect or not, using orientation tests.
 *
 * <p>Time Complexity: O(1)
 *
 * @author Sandeep
 */
public final class LineIntersection {

    private LineIntersection() {
    }

    /**
     * Represents a point in 2D space.
     */
    public static final class Point {
        public final double x;
        public final double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int orientation(Point p, Point q, Point r) {
        double val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0.0) {
            return 0; // collinear
        }
        return (val > 0.0) ? 1 : 2; // clockwise or counterclockwise
    }

    private static boolean onSegment(Point p, Point q, Point r) {
        return q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x)
            && q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y);
    }

    /**
     * Checks whether two line segments (p1,q1) and (p2,q2) intersect.
     *
     * @param p1 starting point of first segment
     * @param q1 ending point of first segment
     * @param p2 starting point of second segment
     * @param q2 ending point of second segment
     * @return true if the segments intersect, false otherwise
     */
    public static boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4) {
            return true;
        }

        if (o1 == 0 && onSegment(p1, p2, q1)) return true;
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false;
    }
}
