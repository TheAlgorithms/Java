package com.thealgorithms.machinelearning;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class LinearRegressionTest {

    @Test
    void testLinearRegression() {
        ArrayList<Double> dependentX = new ArrayList<>();
        ArrayList<Double> independentY = new ArrayList<>();

        dependentX.add(1.0);
        independentY.add(2.0);
        dependentX.add(2.0);
        independentY.add(3.0);
        dependentX.add(3.0);
        independentY.add(4.0);
        dependentX.add(4.0);
        independentY.add(5.0);
        dependentX.add(5.0);
        independentY.add(6.0);

        // Create LinearRegression object
        LinearRegression lr = new LinearRegression(dependentX, independentY);

        // Check the slope (m) and intercept (c)
        assertEquals(1.0, lr.getM(), 0.001);
        assertEquals(1.0, lr.getC(), 0.001);

        // Check prediction for X = 6
        double predictedY = lr.predictForX(6.0);
        assertEquals(7.0, predictedY, 0.001);
    }
}
