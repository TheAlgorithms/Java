package com.thealgorithms.maths;

/**
 * The DistanceFormula class provides methods to calculate various distance
 * metrics for two-dimensional and multi-dimensional points.
 *
 */
public class DistanceFormula {

    /**
     * Calculates Euclidean distance between two-dimensional points.
     *
     * @param x1 X-coordinate of the first point
     * @param y1 Y-coordinate of the first point
     * @param x2 X-coordinate of the second point
     * @param y2 Y-coordinate of the second point
     * @return Euclidean distance between the points
     */
    public static double euclideanDistance(double x1, double y1, double x2, double y2) {
        double dX = Math.pow(x2 - x1, 2);
        double dY = Math.pow(y2 - y1, 2);
        return Math.sqrt(dX + dY);
    }

    /**
     * Calculates Euclidean distance between multi-dimensional points.
     *
     * @param point1 Array representing the first multi-dimensional point
     * @param point2 Array representing the second multi-dimensional point
     * @return Euclidean distance between the points
     */
    public static double euclideanDistance(double[] point1, double[] point2) {
        if (point1.length != point2.length) {
            return -1; // error, both points must have the same dimensions
        }

        double sum = 0;
        for (int i = 0; i < point1.length; i++) {
            sum += Math.pow(point1[i] - point2[i], 2);
        }

        return Math.sqrt(sum);
    }

    /**
     * Calculates Manhattan distance between two-dimensional points.
     *
     * @param x1 X-coordinate of the first point
     * @param y1 Y-coordinate of the first point
     * @param x2 X-coordinate of the second point
     * @param y2 Y-coordinate of the second point
     * @return Manhattan distance between the points
     */
    public static double manhattanDistance(double x1, double y1, double x2, double y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    /**
     * Calculates Manhattan distance between multi-dimensional points.
     *
     * @param point1 Array representing the first multi-dimensional point
     * @param point2 Array representing the second multi-dimensional point
     * @return Manhattan distance between the points
     */
    public static double manhattanDistance(double[] point1, double[] point2) {
        if (point1.length != point2.length) {
            return -1; // error, both points must have the same dimensions
        }

        double sum = 0;
        for (int i = 0; i < point1.length; i++) {
            sum += Math.abs(point1[i] - point2[i]);
        }

        return sum;
    }

    /**
     * Calculates Hamming distance between two-dimensional binary points.
     *
     * @param b1 Binary array representing the first point
     * @param b2 Binary array representing the second point
     * @return Hamming distance between the binary points
     */
    public static int hammingDistance(int[] b1, int[] b2) {
        int d = 0;

        if (b1.length != b2.length) {
            return -1; // error, both arrays must have the same length
        }

        for (int i = 0; i < b1.length; i++) {
            if (b1[i] != b2[i]) {
                d++;
            }
        }

        return d;
    }

    /**
     * Calculates Hamming distance between multi-dimensional binary points.
     *
     * @param binary1 Binary array representing the first multi-dimensional point
     * @param binary2 Binary array representing the second multi-dimensional point
     * @return Hamming distance between the binary points
     */
    public static int hammingDistance(int[] binary1, int[] binary2) {
        int d = 0;

        if (binary1.length != binary2.length) {
            return -1; // error, both arrays must have the same length
        }

        for (int i = 0; i < binary1.length; i++) {
            if (binary1[i] != binary2[i]) {
                d++;
            }
        }

        return d;
    }

    /**
     * Calculates Minkowski distance between multi-dimensional points with default p=2.
     *
     * @param point1 Array representing the first multi-dimensional point
     * @param point2 Array representing the second multi-dimensional point
     * @return Minkowski distance between the points
     */
    public static double minkowskiDistance(double[] point1, double[] point2) {
        return minkowskiDistance(point1, point2, 2);
    }

    /**
     * Calculates Minkowski distance between multi-dimensional points.
     *
     * @param point1 Array representing the first multi-dimensional point
     * @param point2 Array representing the second multi-dimensional point
     * @param p Power parameter for Minkowski distance (p >= 1)
     * @return Minkowski distance between the points
     */
    public static double minkowskiDistance(double[] point1, double[] point2, int p) {
        if (point1.length != point2.length) {
            return -1; // error, both points must have the same dimensions
        }

        double sum = 0;
        for (int i = 0; i < point1.length; i++) {
            sum += Math.pow(Math.abs(point1[i] - point2[i]), p);
        }

        return Math.pow(sum, 1.0 / p);
    }
}
