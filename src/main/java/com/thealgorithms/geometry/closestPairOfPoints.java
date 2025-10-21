package com.thealgorithms.geometry;

import java.util.*;

public final class ClosestPairOfPoints {
    private ClosestPairOfPoints() {}

    public static double closestPair(List<Point> points) {
        List<Point> sortedByX = new ArrayList<>(points);
        sortedByX.sort(Comparator.comparingDouble(p -> p.x));
        return divide(sortedByX);
    }

    private static double divide(List<Point> pts) {
        int n = pts.size();
        if (n <= 3) return bruteForce(pts);

        int mid = n / 2;
        Point midPoint = pts.get(mid);

        double dl = divide(pts.subList(0, mid));
        double dr = divide(pts.subList(mid, n));
        double d = Math.min(dl, dr);

        List<Point> strip = new ArrayList<>();
        for (Point p : pts) {
            if (Math.abs(p.x - midPoint.x) < d) strip.add(p);
        }

        strip.sort(Comparator.comparingDouble(p -> p.y));
        for (int i = 0; i < strip.size(); ++i) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < d; ++j) {
                d = Math.min(d, strip.get(i).distance(strip.get(j)));
            }
        }
        return d;
    }

    private static double bruteForce(List<Point> pts) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.size(); ++i) {
            for (int j = i + 1; j < pts.size(); ++j) {
                min = Math.min(min, pts.get(i).distance(pts.get(j)));
            }
        }
        return min;
    }
}
