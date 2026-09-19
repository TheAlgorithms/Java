package com.thealgorithms.maths;

/**
 * Implementation of the Sigmoid Activation function.
 * Sigmoid function is used as an activation function in machine learning and neural networks
 * for modeling binary classification problems, smoothing outputs, and introducing non-linearity
 * into models.
 *
 * @author <a href="https://github.com/crapxxi">Alikhan Turugeldiyev</a>
 */

public final class SigmoidActivation {

    private SigmoidActivation() {
    }

    /**
     * @summary Maps any real-valued number into a value between 0 and 1
     * @param x In machine learning, x could be a weighted sum of inputs in a neural network neuron or a raw score in logistic regression.
     * @return The output (range) of the sigmoid function is always strictly between 0 and 1.
     */
    public static double activate(double x) {
        // If the number x is NaN then, returning NaN to saving from unexpected output.
        if (Double.isNaN(x)) {
            return Double.NaN;
        }
        // Saving from unnecessary and heavy calculations.
        // lim x->-inf sigmoid(x) will return number very close to 0
        if (x < -745) {
            return 0.0;
        }
        // lim x->inf sigmoid(x) will return number very close to 1
        if (x > 745) {
            return 1.0;
        }
        // sigmoid function's formula
        return 1.0 / (1 + Math.exp((-1) * x));
    }

    public static double[][] activate(double[][] x) {
        // apply calculation to every value in batch.
        double[][] activatedNumbers = new double[x.length][];
        for (int i = 0; i < x.length; i++) {
            activatedNumbers[i] = new double[x[i].length];
            for (int j = 0; j < x[i].length; j++) {
                activatedNumbers[i][j] = activate(x[i][j]);
            }
        }
        return activatedNumbers;
    }

    /**
     * @summary Calculates gradients for mapped values. By the chain rule, you can calculate error.
     * @param y Activated by sigmoid function value.
     * @return The output is a gradient of the activated value.
     */
    public static double grad(double y) {
        // sigmoid function derivative is reducing to this value.
        // sigmoid'(x) = sigmoid(x) * (1-sigmoid(x))
        return y * (1 - y);
    }

    public static double[][] grad(double[][] y) {
        // apply calculation to every value in batch.
        double[][] grads = new double[y.length][];
        for (int i = 0; i < y.length; i++) {
            grads[i] = new double[y[i].length];
            for (int j = 0; j < y[i].length; j++) {
                grads[i][j] = grad(y[i][j]);
            }
        }
        return grads;
    }
}
