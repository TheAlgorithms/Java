package com.thealgorithms.optimization;
/*
 * Hill climbing is a simple optimization algorithm used in Artificial Intelligence (AI)
 * to find the best possible solution for a given problem. It belongs to the family of
 * local search algorithms and is often used in optimization problems where the goal is
 * to find the best solution from a set of possible solutions.
 * For more information, refer https://en.wikipedia.org/wiki/Hill_climbing.
 */

import java.util.Random;

public class HillClimbing {
    // This function returns the value of the objective function at the given point
    // x.
    public static double purposeFunction(double x) {
        // Implement the function here for which you want to apply this algorithm.
        return -Math.pow(x, 2) + 5;
    }

    // This function returns a random number between the given lower and upper
    // bounds.
    public static double randomNumber(double lower, double upper) {
        // Generate a random number between the given lower and upper bounds.
        return new Random().nextDouble() * (upper - lower) + lower;
    }

    // This function implements the Hill Climbing algorithm for finding the maximum
    // of a function.
    public static void hill_Climbing(double lowerLimit, double upperLimit, double stepSize, int maxIterations) {
        // Initialize the current best solution.
        double currentAnswer = randomNumber(lowerLimit, upperLimit);
        double currentValue = purposeFunction(currentAnswer);

        // Perform the iterations.
        for (int i = 0; i < maxIterations; i++) {
            // Generate a random neighbor.
            double neighbor = currentAnswer + randomNumber(-stepSize, stepSize);
            neighbor = Math.max(Math.min(neighbor, upperLimit), lowerLimit); // Ensure the neighbor is within the
                                                                             // boundaries.

            // Calculate the objective value of the neighbor.
            double neighborValue = purposeFunction(neighbor);

            // Update the current answer if the neighbor is better.
            if (neighborValue > currentValue) {
                currentAnswer = neighbor;
                currentValue = neighborValue;
            }
        }
        // Print the best solution found.
        System.out.println("Best solution: " + currentAnswer);
        System.out.println("Best value: " + currentValue);
    }

    // This function sets the seed for random number generation.
    public static void main(String[] args) {
        // Set the seed for random number generation.
        new Random().setSeed(System.currentTimeMillis());
        double LOWER_LIMIT = -10;
        double UPPER_LIMIT = 10;
        double STEP_SIZE = 0.1;
        int MAX_ITERATIONS = 100;

        // Run the Hill Climbing algorithm.
        hill_Climbing(LOWER_LIMIT, UPPER_LIMIT, STEP_SIZE, MAX_ITERATIONS);
    }
}
