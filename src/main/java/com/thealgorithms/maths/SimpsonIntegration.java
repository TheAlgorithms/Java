package com.thealgorithms.maths;

import java.util.TreeMap;

public class SimpsonIntegration {

    /*
     * Calculate definite integrals by using Composite Simpson's rule.
     * Wiki: https://en.wikipedia.org/wiki/Simpson%27s_rule#Composite_Simpson's_rule
     * Given f a function and an even number N of intervals that divide the integration interval e.g. [a, b],
     * we calculate the step h = (b-a)/N and create a table that contains all the x points of
     * the real axis xi = x0 + i*h and the value f(xi) that corresponds to these xi.
     *
     * To evaluate the integral i use the formula below:
     * I = h/3 * {f(x0) + 4*f(x1) + 2*f(x2) + 4*f(x3) + ... + 2*f(xN-2) + 4*f(xN-1) + f(xN)}
     *
     */
    public static void main(String[] args) {
        SimpsonIntegration integration = new SimpsonIntegration();

        // Give random data for the example purposes
        int N = 16;
        double a = 1;
        double b = 3;

        // Check so that N is even
        if (N % 2 != 0) {
            System.out.println(
                "N must be even number for Simpsons method. Aborted"
            );
            System.exit(1);
        }

        // Calculate step h and evaluate the integral
        double h = (b - a) / (double) N;
        double integralEvaluation = integration.simpsonsMethod(N, h, a);
        System.out.println("The integral is equal to: " + integralEvaluation);
    }

    /*
     * @param N: Number of intervals (must be even number N=2*k)
     * @param h: Step h = (b-a)/N
     * @param a: Starting point of the interval
     * @param b: Ending point of the interval
     *
     * The interpolation points xi = x0 + i*h are stored the treeMap data
     *
     * @return result of the integral evaluation
     */
    public double simpsonsMethod(int N, double h, double a) {
        TreeMap<Integer, Double> data = new TreeMap<>(); // Key: i, Value: f(xi)
        double temp;
        double xi = a; // Initialize the variable xi = x0 + 0*h

        // Create the table of xi and yi points
        for (int i = 0; i <= N; i++) {
            temp = f(xi); // Get the value of the function at that point
            data.put(i, temp);
            xi += h; // Increase the xi to the next point
        }

        // Apply the formula
        double integralEvaluation = 0;
        for (int i = 0; i < data.size(); i++) {
            if (i == 0 || i == data.size() - 1) {
                integralEvaluation += data.get(i);
                System.out.println("Multiply f(x" + i + ") by 1");
            } else if (i % 2 == 1) {
                integralEvaluation += (double) 4 * data.get(i);
                System.out.println("Multiply f(x" + i + ") by 4");
            } else {
                integralEvaluation += (double) 2 * data.get(i);
                System.out.println("Multiply f(x" + i + ") by 2");
            }
        }

        // Multiply by h/3
        integralEvaluation = h / 3 * integralEvaluation;

        // Return the result
        return integralEvaluation;
    }

    // Sample function f
    // Function f(x) = e^(-x) * (4 - x^2)
    public double f(double x) {
        return Math.exp(-x) * (4 - Math.pow(x, 2));
        //        return Math.sqrt(x);
    }
}
