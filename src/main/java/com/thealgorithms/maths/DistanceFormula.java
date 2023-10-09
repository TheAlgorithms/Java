package com.thealgorithms.maths;

public class DistanceFormula {

    public static double euclideanDistance(double x1, double y1, double x2, double y2) {
        double dX = Math.pow(x2 - x1, 2);
        double dY = Math.pow(y2 - x1, 2);
        return Math.sqrt(dX + dY);
    }

    public static double manhattanDistance(double x1, double y1, double x2, double y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static int hammingDistance(int[] b1, int[] b2) {
        int d = 0;

        if (b1.length != b2.length) {
            return -1; // error, both array must be have the same length
        }

        for (int i = 0; i < b1.length; i++) {
            d += Math.abs(b1[i] - b2[i]);
        }

        return d;
    }

    public static double minkowskiDistance(double[] p1, double[] p2, int p) {
        double d = 0;
        double distance = 0.0;

        if (p1.length != p2.length) {
            return -1; // error, both array must be have the same length
        }

        for (int i = 0; i < p1.length; i++) {
            distance += Math.abs(Math.pow(p1[i] - p2[i], p));
        }

        distance = Math.pow(distance, (double) 1 / p);
        d = distance;
        return d;
    }
}
