package com.thealgorithms.maths;

import java.util.ArrayList;
import java.util.function.BiFunction;

/**
 * In mathematics and computational science, the Euler method (also called
 * forward Euler method) is a first-order numerical procedure for solving
 * ordinary differential equations (ODEs) with a given initial value. It is the
 * most basic explicit method for numerical integration of ordinary differential
 * equations. The method proceeds in a series of steps. At each step the y-value
 * is calculated by evaluating the differential equation at the previous step,
 * multiplying the result with the step-size and adding it to the last y-value:
 * y_n+1 = y_n + stepSize * f(x_n, y_n). (description adapted from
 * https://en.wikipedia.org/wiki/Euler_method ) (see also:
 * https://www.geeksforgeeks.org/euler-method-solving-differential-equation/ )
 */
public class EulerMethod {

    /**
     * Illustrates how the algorithm is used in 3 examples and prints the
     * results to the console.
     */
    public static void main(String[] args) {
        System.out.println("example 1:");
        BiFunction<Double, Double, Double> exampleEquation1 = (x, y) -> x;
        ArrayList<double[]> points1 = eulerFull(0, 4, 0.1, 0, exampleEquation1);
        assert points1.get(points1.size() - 1)[1] == 7.800000000000003;
        points1.forEach(
                point -> System.out.println(String.format("x: %1$f; y: %2$f", point[0], point[1])));

        // example from https://en.wikipedia.org/wiki/Euler_method
        System.out.println("\n\nexample 2:");
        BiFunction<Double, Double, Double> exampleEquation2 = (x, y) -> y;
        ArrayList<double[]> points2 = eulerFull(0, 4, 0.1, 1, exampleEquation2);
        assert points2.get(points2.size() - 1)[1] == 45.25925556817596;
        points2.forEach(
                point -> System.out.println(String.format("x: %1$f; y: %2$f", point[0], point[1])));

        // example from https://www.geeksforgeeks.org/euler-method-solving-differential-equation/
        System.out.println("\n\nexample 3:");
        BiFunction<Double, Double, Double> exampleEquation3 = (x, y) -> x + y + x * y;
        ArrayList<double[]> points3 = eulerFull(0, 0.1, 0.025, 1, exampleEquation3);
        assert points3.get(points3.size() - 1)[1] == 1.1116729841674804;
        points3.forEach(
                point -> System.out.println(String.format("x: %1$f; y: %2$f", point[0], point[1])));
    }

    /**
     * calculates the next y-value based on the current value of x, y and the
     * stepSize the console.
     *
     * @param xCurrent Current x-value.
     * @param stepSize Step-size on the x-axis.
     * @param yCurrent Current y-value.
     * @param differentialEquation The differential equation to be solved.
     * @return The next y-value.
     */
    public static double eulerStep(
            double xCurrent,
            double stepSize,
            double yCurrent,
            BiFunction<Double, Double, Double> differentialEquation) {
        if (stepSize <= 0) {
            throw new IllegalArgumentException("stepSize should be greater than zero");
        }
        double yNext = yCurrent + stepSize * differentialEquation.apply(xCurrent, yCurrent);
        return yNext;
    }

    /**
     * Loops through all the steps until xEnd is reached, adds a point for each
     * step and then returns all the points
     *
     * @param xStart First x-value.
     * @param xEnd Last x-value.
     * @param stepSize Step-size on the x-axis.
     * @param yStart First y-value.
     * @param differentialEquation The differential equation to be solved.
     * @return The points constituting the solution of the differential
     * equation.
     */
    public static ArrayList<double[]> eulerFull(
            double xStart,
            double xEnd,
            double stepSize,
            double yStart,
            BiFunction<Double, Double, Double> differentialEquation) {
        if (xStart >= xEnd) {
            throw new IllegalArgumentException("xEnd should be greater than xStart");
        }
        if (stepSize <= 0) {
            throw new IllegalArgumentException("stepSize should be greater than zero");
        }

        ArrayList<double[]> points = new ArrayList<double[]>();
        double[] firstPoint = {xStart, yStart};
        points.add(firstPoint);
        double yCurrent = yStart;
        double xCurrent = xStart;

        while (xCurrent < xEnd) {
            // Euler method for next step
            yCurrent = eulerStep(xCurrent, stepSize, yCurrent, differentialEquation);
            xCurrent += stepSize;
            double[] point = {xCurrent, yCurrent};
            points.add(point);
        }

        return points;
    }
}
