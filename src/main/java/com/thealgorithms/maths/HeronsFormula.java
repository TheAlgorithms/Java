package com.thealgorithms.maths;

/**
 * Find the area of a triangle using only side lengths
 */

public class HeronsFormula {

    public static double Herons(int s1, int s2, int s3) {
        double a = s1;
        double b = s2;
        double c = s3;
        double s = (a + b + c) / 2.0;
        double area = 0;
        area = Math.sqrt((s) * (s - a) * (s - b) * (s - c));
        return area;
    }
}
