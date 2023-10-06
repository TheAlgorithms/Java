package com.thealgorithms.maths;

/**
 * Wikipedia for HeronsFormula => https://en.wikipedia.org/wiki/Heron%27s_formula
 * Find the area of a triangle using only side lengths
 */

public class HeronsFormula {
    
    public static double Herons(double a, double b, double c) {
        double s = (a + b + c) / 2.0;
        double area = 0;
        area = Math.sqrt((s) * (s - a) * (s - b) * (s - c));
        return area;
    }
}
