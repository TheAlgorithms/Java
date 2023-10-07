package com.thealgorithms.maths;

/**
 * Wikipedia for HeronsFormula => https://en.wikipedia.org/wiki/Heron%27s_formula
 * Find the area of a triangle using only side lengths
 */

public class HeronsFormula {
    /*
     * A function to get the Area of a Triangle using Heron's Formula
     * @param s1,s2,s3 => the three sides of the Triangle
     * @return area using the formula (√(s(s – s1)(s – s2)(s – s3)))
     * here s is called semi-perimeter and it is the half of the perimeter (i.e; s = (s1+s2+s3)/2)
     * @author satyabarghav
     */
    public static double Herons(double a, double b, double c) {
        if (!(a + b > c && b + c > a && c + a > b)) {
            throw new IllegalArgumentException("Triangle can't be formed with the given side lengths");
        } else {
            double s = (a + b + c) / 2.0;
            double area = 0;
            area = Math.sqrt((s) * (s - a) * (s - b) * (s - c));
            return area;
        }
    }
}
