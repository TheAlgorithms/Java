package com.thealgorithms.maths;
/**
 * In mathematics, a Geometric Progression (GP) or geometric sequence is a
 * sequence of numbers such that the ratio between the consecutive terms is
 * constant. Ratio here means the second divided by the first. For instance, the
 * sequence 2,4,8,16 . . . (till infinity) is a geometric progression with common
 * ratio of 2.
 */

import java.util.Scanner;

public class SumOfInfiniteGeometricProgression {
    public static void main(String[] args) {


        /**
         * Calculate the sum of infinite geometric progression
         *
         * @param a the initial term of the geometric series
         * @param r the common ratio of the geometric series
         * @return the sum of infinite geometric progression
         */
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter the value of first term: ");
        double a = scn.nextDouble();
        System.out.print("Enter the value of common ratio: ");
        double r = scn.nextDouble();
        double sum = a / (1 - r);
        System.out.println("The sum of infinite geometric progression is "+sum);
    }
}
