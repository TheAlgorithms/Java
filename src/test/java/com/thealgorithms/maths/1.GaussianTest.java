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
        list.add(1.1);
        list.add(1.2);
        list.add(1.3);
        list.add(2.4);
        list.add(1.0);
        list.add(1.0);
        gaussian=gaussian(matrixSize,list);

        assertEquals(answer,gaussian);
    }
}

    @Test
    void passTest2()
    {
        ArrayList<Double> list = new ArrayList<Double>();
        ArrayList<Double> gaussian = new ArrayList<Double>();
        ArrayList<Double> answer = new ArrayList<Double>();
        answer.add(0.0);
        answer.add(2.0);

        int matrixSize = 3;
        list.add(0.1);
        list.add(1.2);
        list.add(1.3);
        list.add(2.9);
        list.add(2.3);
        list.add(3.0);
        gaussian=gaussian(matrixSize,list);

        assertEquals(answer,gaussian);
    }
}
