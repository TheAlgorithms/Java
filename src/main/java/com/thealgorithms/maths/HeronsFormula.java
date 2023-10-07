package com.thealgorithms.maths;

/**
 * Find the area of a triangle using only side lengths
 */

public class HeronsFormula {

    public static double Herons(double s1, double s2, double s3) {
        double s = (s1 + s2 + s3) / 2.0;
        double area = 0;
        area = Math.sqrt((s) * (s - s1) * (s - s2) * (s - s3));
        return area;
    }
}
