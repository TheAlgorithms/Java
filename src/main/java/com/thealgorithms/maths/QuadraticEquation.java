package com.thealgorithms.maths;

public class QuadraticEquation {
    static double[] solve(double a, double b, double delta) {

        double x1, x2;
        double[] results = new double[2];

        if (a != 0) {
            if (delta > 0) {
                x1 = ((-b) - delta) / (2 * a);
                x2 = ((-b) + delta) / (2 * a);
                results[0] = x1;
                results[1] = x2;
                System.out.println("x1=" + x1 + "\n" + "x2=" + x2);
            } else if (delta == 0) {
                x1 = (-b) / (2 * a);
                System.out.println("x1=" + x1);
            } else {
                System.out.println("No real roots.");
            }
        } else {
            System.out.println("Error: division by zero.");
        }

        return results;
    }
}
