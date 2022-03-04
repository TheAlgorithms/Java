package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static com.thealgorithms.maths.Gaussian.gaussian;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GaussianTest {

    // easy pass test for the whole class. Matrix of 2*3.
    @Test
    void passTest1()
    {
        ArrayList<Double> list = new ArrayList<Double>();
        ArrayList<Double> gaussian = new ArrayList<Double>();
        ArrayList<Double> answer = new ArrayList<Double>();
        answer.add(0.0);
        answer.add(1.0);

        int matrixSize = 2;
        list.add(1.0);
        list.add(1.0);
        list.add(1.0);
        list.add(2.0);
        list.add(1.0);
        list.add(1.0);
        gaussian=gaussian(matrixSize,list);

        assertEquals(answer,gaussian);
    }
}