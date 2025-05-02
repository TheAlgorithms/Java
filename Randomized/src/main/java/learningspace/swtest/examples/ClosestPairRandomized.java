package learningspace.swtest.examples;

import java.util.*;

public class ClosestPairRandomized {

    public static class Point {
        double x, y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double distance(Point a, Point b) {
        return Math.hypot(a.x - b.x, a.y - b.y);
    }

    public static double closestPair(Point[] points, int samples) {
        Random rand = new Random();
        List<Point> shuffled = new ArrayList<>(Arrays.asList(points));
        Collections.shuffle(shuffled, rand);

        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < samples; i++) {
            Point p1 = shuffled.get(rand.nextInt(shuffled.size()));
            Point p2 = shuffled.get(rand.nextInt(shuffled.size()));
            if (p1 != p2) {
                double d = distance(p1, p2);
                if (d < minDist) {
                    minDist = d;
                }
            }
        }
        return minDist;
    }
}
