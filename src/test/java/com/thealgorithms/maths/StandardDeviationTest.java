package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StandardDeviationTest {

    @Test
    void test1() {
        double[] t1 = new double[] { 1, 1, 1, 1, 1 };
        Assertions.assertEquals(StandardDeviation.stdDev(t1), 0.0);
    }

    @Test
    void test2() {
        double[] t2 = new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        Assertions.assertEquals(
            StandardDeviation.stdDev(t2),
            2.8722813232690143
        );
    }

    @Test
    void test3() {
        double[] t3 = new double[] { 1.1, 8.5, 20.3, 2.4, 6.2 };
        Assertions.assertEquals(
            StandardDeviation.stdDev(t3),
            6.8308125431752265
        );
    }

    @Test
    void test4() {
        double[] t4 = new double[] {
            3.14,
            2.22222,
            9.89898989,
            100.00045,
            56.7,
        };
        Assertions.assertEquals(
            StandardDeviation.stdDev(t4),
            38.506117353865775
        );
    }
}
