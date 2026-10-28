package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class SigmoidActivationTest {

    @Test
    public void calculationTest() {
        assertEquals(0.5, SigmoidActivation.activate(0), 0.01, "1 case correct");
        assertEquals(0.73, SigmoidActivation.activate(1), 0.01, "2 case correct");
        assertEquals(0.26, SigmoidActivation.activate(-1), 0.01, "3 case correct");
        assertEquals(0.88, SigmoidActivation.activate(2), 0.01, "4 case correct");
        assertEquals(0.11, SigmoidActivation.activate(-2), 0.01, "5 case correct");

        double[][] xBatch = new double[4][3];
        double[][] expectedX = new double[4][3];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                xBatch[i][j] = 0;
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                expectedX[i][j] = 0.5;
            }
        }

        assertTrue(Arrays.deepEquals(expectedX, SigmoidActivation.activate(xBatch)), "batch case correct");

        assertEquals(0.25, SigmoidActivation.grad(0.5), 0.01, "grad calculation correct");

        double[][] yBatch = new double[4][3];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                yBatch[i][j] = 0.5;
            }
        }

        double[][] expectedY = new double[4][3];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                expectedY[i][j] = 0.25;
            }
        }
        assertTrue(Arrays.deepEquals(expectedY, SigmoidActivation.grad(yBatch)), "grad batch case correct");
    }

    @Test
    public void willReturnNaN() {
        double x = Double.NaN;

        assertTrue(Double.isNaN(SigmoidActivation.activate(x)), "returned NaN");
    }

    @Test
    public void extremumNumbersOnActivate() {
        double x = 777;

        assertEquals(1.0, SigmoidActivation.activate(x), 0.01, "big number case correct");
        assertEquals(0.0, SigmoidActivation.activate((-1) * x), 0.01, "small number case correct");
    }
}
