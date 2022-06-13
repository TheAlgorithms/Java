package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GaussianTest {

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
        gaussian=gaussian(matrixSize,list);

        assertEquals(answer,gaussian);
    }
}

