package com.thealgorithms.machinelearning;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class LinearRegressionTest {

    private static final double DELTA = 0.1;

    @Test
    void fitLearnsCorrectSlopeAndIntercept() {
        double trueM = 2.5;
        double trueB = 1.5;

        double[] xTrain = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};
        double[] yTrain = new double[xTrain.length];
        for (int i = 0; i < xTrain.length; i++) {
            yTrain[i] = (trueM * xTrain[i]) + trueB;
        }

        LinearRegression model = new LinearRegression(0.01, 1000);
        model.fit(xTrain, yTrain);

        assertEquals(trueM, model.getSlope(), DELTA);
        assertEquals(trueB, model.getIntercept(), DELTA);
    }

    @Test
    void predictMatchesExpectedOnUnseenData() {
        double trueM = 2.5;
        double trueB = 1.5;

        double[] xTrain = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};
        double[] yTrain = new double[xTrain.length];
        for (int i = 0; i < xTrain.length; i++) {
            yTrain[i] = (trueM * xTrain[i]) + trueB;
        }

        LinearRegression model = new LinearRegression(0.01, 1000);
        model.fit(xTrain, yTrain);

        double[] testInputs = {0.0, 3.5, 7.0};
        for (double testX : testInputs) {
            double expectedY = (trueM * testX) + trueB;
            assertEquals(expectedY, model.predict(testX), DELTA);
        }
    }

    @Test
    void calculateMSEIsNearZeroAfterTraining() {
        double[] xTrain = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] yTrain = {3.0, 5.0, 7.0, 9.0, 11.0}; // y = 2x + 1

        LinearRegression model = new LinearRegression(0.01, 1000);
        model.fit(xTrain, yTrain);

        assertEquals(0.0, model.calculateMSE(xTrain, yTrain), 0.01);
    }

    @Test
    void fitThrowsExceptionOnMismatchedArrayLengths() {
        LinearRegression model = new LinearRegression(0.01, 100);
        double[] x = {1.0, 2.0};
        double[] y = {1.0};

        assertThrows(IllegalArgumentException.class, () -> model.fit(x, y));
    }

    @Test
    void fitThrowsExceptionOnEmptyArrays() {
        LinearRegression model = new LinearRegression(0.01, 100);
        double[] x = {};
        double[] y = {};

        assertThrows(IllegalArgumentException.class, () -> model.fit(x, y));
    }

    @Test
    void fitThrowsExceptionOnNullX() {
        LinearRegression model = new LinearRegression(0.01, 100);
        double[] y = {1.0, 2.0};

        assertThrows(IllegalArgumentException.class, () -> model.fit(null, y));
    }

    @Test
    void fitThrowsExceptionOnNullY() {
        LinearRegression model = new LinearRegression(0.01, 100);
        double[] x = {1.0, 2.0};

        assertThrows(IllegalArgumentException.class, () -> model.fit(x, null));
    }
}
