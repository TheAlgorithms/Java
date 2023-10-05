package com.thealgorithms.geometry;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class PolyIntersect {
    public static Point[] getIntersections(Polygon p, int l1x1, int l1y1, int l1x2, int l1y2) {
        List<Point> intersections = new ArrayList<Point>();
        for (int i = 0; i < p.npoints; i++) {
            int j = i == p.npoints - 1 ? 0 : i + 1;
            int l2x1 = p.xpoints[i];
            int l2x2 = p.xpoints[j];
            int l2y1 = p.ypoints[i];
            int l2y2 = p.ypoints[j];

            Point intersect = getIntersection(l1x1, l1y1, l1x2, l1y2, l2x1, l2y1, l2x2, l2y2);
            if (intersect != null) intersections.add(intersect);
        }
        return intersections.toArray(new Point[] {});
    }

    public static Point[] getIntersections(List<Polygon> polygons, int x1, int y1, int x2, int y2) {
        List<Point> intersections = new ArrayList<Point>();
        for (Polygon p : polygons) {
            Point[] polygonIntersects = getIntersections(p, x1, y1, x2, y2);
            for (Point pt : polygonIntersects) intersections.add(pt);
        }
        return intersections.toArray(new Point[] {});
    }

    public static Point getIntersection(int l1x1, int l1y1, int l1x2, int l1y2, int l2x1, int l2y1, int l2x2, int l2y2) {
        double d = (l2y2 - l2y1) * (l1x2 - l1x1) - (l2x2 - l2x1) * (l1y2 - l1y1);
        double na = (l2x2 - l2x1) * (l1y1 - l2y1) - (l2y2 - l2y1) * (l1x1 - l2x1);
        double nb = (l1x2 - l1x1) * (l1y1 - l2y1) - (l1y2 - l1y1) * (l1x1 - l2x1);
        if (d == 0D) return null;

        double ua = na / d;
        double ub = nb / d;
        if (ua >= 0D && ua <= 1D && ub >= 0D && ub <= 1D) {
            Point intersect = new Point();
            intersect.x = (int) (l1x1 + (ua * (l1x2 - l1x1)));
            intersect.y = (int) (l1y1 + (ua * (l1y2 - l1y1)));
            return intersect;
        }

        return null;
    }

    // This Method has been created separately to test the code
    public static void main(String[] args) {
        // Create a Polygon object (for example, a triangle)
        Polygon polygon = new Polygon();
        polygon.addPoint(0, 0);
        polygon.addPoint(5, 0);
        polygon.addPoint(0, 5);

        // Define the line segment coordinates
        int l1x1 = 2;
        int l1y1 = 2;
        int l1x2 = 6;
        int l1y2 = 6;

        // Find intersections between the line segment and the polygon
        Point[] intersections = PolyIntersect.getIntersections(polygon, l1x1, l1y1, l1x2, l1y2);

        // Print the intersection points
        System.out.println("Intersection Points:");
        for (Point intersection : intersections) {
            System.out.println("(" + intersection.x + ", " + intersection.y + ")");
        }
    }
}
