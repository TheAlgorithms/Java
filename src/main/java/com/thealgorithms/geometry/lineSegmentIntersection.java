package com.thealgorithms.geometry;

/**
 * Line Segment Intersection detection using orientation method.
 * 
 * Time Complexity: O(1)
 */
public final class LineSegmentIntersection {
    private LineSegmentIntersection() {}

    public static boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
        int o1 = Point.orientation(p1, q1, p2);
        int o2 = Point.orientation(p1, q1, q2);
        int o3 = Point.orientation(p2, q2, p1);
        int o4 = Point.orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4) return true;

        // Collinear cases
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false;
    }

    private static boolean onSegment(Point p, Point q, Point r) {
        return q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x)
            && q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y);
    }
}
