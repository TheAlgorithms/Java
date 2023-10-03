package com.thealgorithms.others;
import java.util.ArrayList;

/**
 * Java program defines a LinearRegression class with methods for fitting a linear regression model to data
 * and making predictions. It uses gradient descent to update the model's parameters (slope and intercept)
 * based on the input data. You can adjust the learning rate and the number of iterations to control the training process.
 *
 * Linear regression is a simple machine learning algorithm for modeling the relationship between a dependent variable (Y)
 * and one or more independent variables (X). In this program, we focus on simple linear regression with a single input
 * variable (X) to predict a single output variable (Y).
 *
 * The program includes the following components:
 * - Fitting the linear regression model to the provided training data (X and Y).
 * - Predicting the output (Y) for a given input value (X) using the trained model.
 * - Adjustable parameters: learning rate and the number of iterations for gradient descent.
 */

public class LinearRegression {
    public static void main(String[] args) {
        // Sample input data: X and corresponding Y values
        double[] X = {1, 2, 3, 4, 5};
        double[] Y = {2, 3, 4, 5, 6};

        // Create a LinearRegression object and fit the model
        LinearRegression model = new LinearRegression();
        model.fit(X, Y);

        // Predict a value using the trained model
        double inputX = 6;
        double predictedY = model.predict(inputX);
        System.out.println("Predicted Y for X=" + inputX + ": " + predictedY);
    }

    private double slope;  // Coefficient for X (slope)
    private double intercept;  // Intercept term

    // Constructor
    public LinearRegression() {
        this.slope = 0;
        this.intercept = 0;
    }

    // Fit the linear regression model to the data
    public void fit(double[] X, double[] Y) {
        int n = X.length;
        double learningRate = 0.01; // Learning rate for gradient descent
        int numIterations = 1000; // Number of iterations for gradient descent

        // Perform gradient descent to find the best fit line
        for (int i = 0; i < numIterations; i++) {
            double predictedY[] = predict(X); // Predicted Y values
            double error[] = new double[n]; // Error for each data point

            // Calculate error for each data point
            for (int j = 0; j < n; j++) {
                error[j] = predictedY[j] - Y[j];
            }

            // Update slope and intercept using gradient descent
            double sumError = 0;
            double sumErrorX = 0;

            for (int j = 0; j < n; j++) {
                sumError += error[j];
                sumErrorX += error[j] * X[j];
            }

            slope -= (learningRate / n) * sumErrorX;
            intercept -= (learningRate / n) * sumError;
        }
    }

    // Predict the output for a given input
    public double predict(double inputX) {
        return slope * inputX + intercept;
    }
}
