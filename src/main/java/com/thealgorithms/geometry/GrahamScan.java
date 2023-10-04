package com.thealgorithms.geometry;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/*
 * A Java program that computes the convex hull using the Graham Scan algorithm
 * In the best case, time complexity is O(n), while in the worst case, it is log(n).
 * O(n) space complexity
 *
 * This algorithm is only applicable to integral coordinates.
 *
 * Reference:
 * https://github.com/TheAlgorithms/C-Plus-Plus/blob/master/geometry/graham_scan_algorithm.cpp
 * https://github.com/TheAlgorithms/C-Plus-Plus/blob/master/geometry/graham_scan_functions.hpp
 * https://algs4.cs.princeton.edu/99hull/GrahamScan.java.html
 */
public class GrahamScan {
    private final Stack<Point> hull = new Stack<>();

    public GrahamScan(Point[] points) {

        /*
         * pre-process the points by sorting them with respect to the bottom-most point, then we'll
         * push the first point in the array to be our first extreme point.
         */
        Arrays.sort(points);
        Arrays.sort(points, 1, points.length, points[0].polarOrder());
        hull.push(points[0]);

        // find index of first point not equal to a[0] (indexPoint1) and the first point that's not
        // collinear with either (indexPoint2).
        int indexPoint1;
        for (indexPoint1 = 1; indexPoint1 < points.length; indexPoint1++)
            if (!points[0].equals(points[indexPoint1])) break;
        if (indexPoint1 == points.length) return;

        int indexPoint2;
        for (indexPoint2 = indexPoint1 + 1; indexPoint2 < points.length; indexPoint2++)
            if (Point.orientation(points[0], points[indexPoint1], points[indexPoint2]) != 0) break;
        hull.push(points[indexPoint2 - 1]);

        // Now we simply add the point to the stack based on the orientation.
        for (int i = indexPoint2; i < points.length; i++) {
            Point top = hull.pop();
            while (Point.orientation(hull.peek(), top, points[i]) <= 0) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(points[i]);
        }
    }

    /**
     * @return A stack of points representing the convex hull.
     */
    public Iterable<Point> hull() {
        Stack<Point> s = new Stack<>();
        for (Point p : hull) s.push(p);
        return s;
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
         * Finds the orientation of ordered triplet.
         *
         * @param a Co-ordinates of point a <int, int>
         * @param b Co-ordinates of point a <int, int>
         * @param c Co-ordinates of point a <int, int>
         * @return { -1, 0, +1 } if a -→ b -→ c is a { clockwise, collinear; counterclockwise }
         *     turn.
         */
        public static int orientation(Point a, Point b, Point c) {
            int val = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
            if (val == 0) {
                return 0;
            }
            return (val > 0) ? +1 : -1;
        }

        /**
         * @param p2 Co-ordinate of point to compare to.
         * This function will compare the points and will return a positive integer it the
         * point is greater than the argument point and a negative integer if the point is
         * less than the argument point.
         */
        public int compareTo(Point p2) {
            if (this.y < p2.y) return -1;
            if (this.y > p2.y) return +1;
            if (this.x < p2.x) return -1;
            if (this.x > p2.x) return +1;
            return 0;
        }

        /**
         * A helper function that will let us sort points by their polar order
         * This function will compare the angle between 2 polar Co-ordinates
         *
         * @return the comparator
         */
        public Comparator<Point> polarOrder() {
            return new PolarOrder();
        }

        private class PolarOrder implements Comparator<Point> {
            public int compare(Point p1, Point p2) {
                int dx1 = p1.x - x;
                int dy1 = p1.y - y;
                int dx2 = p2.x - x;
                int dy2 = p2.y - y;

                if (dy1 >= 0 && dy2 < 0)
                    return -1; // q1 above; q2 below
                else if (dy2 >= 0 && dy1 < 0)
                    return +1; // q1 below; q2 above
                else if (dy1 == 0 && dy2 == 0) { // 3-collinear and horizontal
                    if (dx1 >= 0 && dx2 < 0)
                        return -1;
                    else if (dx2 >= 0 && dx1 < 0)
                        return +1;
                    else
                        return 0;
                } else
                    return -orientation(Point.this, p1, p2); // both above or below
            }
        }

        /**
         * Override of the toString method, necessary to compute the difference
         * between the expected result and the derived result
         *
         * @return a string representation of any given 2D point in the format (x, y)
         */
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
