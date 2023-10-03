package com.thealgorithms.others;

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

import java.util.Arrays;

public class LinearRegression {

    private double intercept;
    private double slope;

    public LinearRegression(double[] x, double[] y) {
        int n = x.length;

        // Compute the mean of x and y
        double meanX = Arrays.stream(x).sum() / n;
        double meanY = Arrays.stream(y).sum() / n;

        // Compute the covariance between x and y
        double covariance = 0.0;
        for (int i = 0; i < n; i++) {
            covariance += (x[i] - meanX) * (y[i] - meanY);
        }

        // Compute the variance of x
        double varianceX = 0.0;
        for (int i = 0; i < n; i++) {
            varianceX += (x[i] - meanX) * (x[i] - meanX);
        }

        // Compute the slope and intercept of the regression line
        slope = covariance / varianceX;
        intercept = meanY - slope * meanX;
    }

    public double predict(double x) {
        return slope * x + intercept;
    }
} error[j] * X[j];
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
