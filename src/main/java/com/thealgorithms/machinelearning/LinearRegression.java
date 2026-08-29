package com.thealgorithms.machinelearning;

/**
 * A simple Linear Regression model implemented from scratch using Gradient Descent.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Linear_regression">Linear Regression (Wikipedia)</a>
 * @author Vraj Prajapati (Rosander0)
 */
public class LinearRegression {
    private double m; // Slope (weight)
    private double b; // Y-intercept (bias)
    private final double learningRate;
    private final int epochs;

    /**
     * Constructs a Linear Regression model with the given hyperparameters.
     *
     * @param learningRate controls the step size during gradient descent
     * @param epochs the number of iterations to train the model
     */
    public LinearRegression(double learningRate, int epochs) {
        this.learningRate = learningRate;
        this.epochs = epochs;
        this.m = 0.0;
        this.b = 0.0;
    }

    /**
     * Trains the model on the provided dataset using batch gradient descent.
     *
     * @param x the input feature values
     * @param y the corresponding target values
     * @throws IllegalArgumentException if the arrays are null, empty, or of differing lengths
     */
    public void fit(double[] x, double[] y) {
        if (x == null || y == null || x.length != y.length || x.length == 0) {
            throw new IllegalArgumentException("X and Y must be non-null, non-empty, and of the same length.");
        }

        int n = x.length;

        for (int epoch = 0; epoch < epochs; epoch++) {
            double mGradient = 0;
            double bGradient = 0;

            // Calculate gradients across the entire dataset
            for (int i = 0; i < n; i++) {
                double prediction = (m * x[i]) + b;
                double error = prediction - y[i];

                // Partial derivatives of the Mean Squared Error cost function
                mGradient += error * x[i];
                bGradient += error;
            }

            // Average the gradients and update the parameters
            m -= 2.0 / n * mGradient * learningRate;
            b -= 2.0 / n * bGradient * learningRate;
        }
    }

    /**
     * Predicts the output for a given input x.
     *
     * @param x the input value
     * @return the predicted output
     */
    public double predict(double x) {
        return (m * x) + b;
    }

    /**
     * Calculates the Mean Squared Error of the model against a dataset.
     *
     * @param x the input feature values
     * @param y the corresponding target values
     * @return the mean squared error
     */
    public double calculateMSE(double[] x, double[] y) {
        double totalSquaredError = 0;
        int n = x.length;
        for (int i = 0; i < n; i++) {
            double error = predict(x[i]) - y[i];
            totalSquaredError += error * error;
        }
        return totalSquaredError / n;
    }

    /**
     * @return the learned slope of the regression line
     */
    public double getSlope() {
        return m;
    }

    /**
     * @return the learned y-intercept of the regression line
     */
    public double getIntercept() {
        return b;
    }
}
