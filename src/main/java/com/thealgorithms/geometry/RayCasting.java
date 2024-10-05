package com.thealgorithms.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * A Java program that determines whether a point is inside a polygon using the Ray Casting algorithm.
 * The time complexity is O(n), where n is the number of vertices in the polygon.
 * The space complexity is O(1).
 *
 * References:
 * https://en.wikipedia.org/wiki/Point_in_polygon
 */
public class RayCasting {

    /**
     * Determines if the given point is inside the polygon.
     *
     * @param polygon An array of points representing the vertices of the polygon.
     * @param point   The point to check.
     * @return true if the point is inside the polygon, false otherwise.
     */
    public static boolean isPointInPolygon(Point[] polygon, Point point) {
        int n = polygon.length;
        boolean inside = false;

        for (int i = 0, j = n - 1; i < n; j = i++) {
            if ((polygon[i].y > point.y) != (polygon[j].y > point.y) &&
                (point.x < (polygon[j].x - polygon[i].x) * (point.y - polygon[i].y) /
                (polygon[j].y - polygon[i].y) + polygon[i].x)) {
                inside = !inside;
            }
        }
        return inside;
    }

    /**
     * A record representing a point in 2D space.
     */
    public record Point(int x, int y) {
        /**
         * @return A string representation of this point in the format (x, y)
         */
        @Override
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }
    }

    public static void main(String[] args) {
        Point[] polygon = {
            new Point(0, 0),
            new Point(5, 0),
            new Point(5, 5),
            new Point(0, 5)
        };
        Point testPoint = new Point(3, 3);
        System.out.println("Is point " + testPoint + " inside the polygon? " + isPointInPolygon(polygon, testPoint)); // true

        Point outsidePoint = new Point(6, 3);
        System.out.println("Is point " + outsidePoint + " inside the polygon? " + isPointInPolygon(polygon, outsidePoint)); // false
    }
}
