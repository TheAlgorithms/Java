package com.thealgorithms.geometry;

import java.util.Comparator;

public record Point(int x, int y) implements Comparable<Point> {

    @Override
    public int compareTo(Point other) {
        int cmpY = Integer.compare(this.y, other.y);
        return cmpY != 0 ? cmpY : Integer.compare(this.x, other.x);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    public Comparator<Point> polarOrder() {
        return new PolarOrder();
    }

    public static int orientation(Point a, Point b, Point c) {
        return Integer.compare((b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x), 0);
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
}
