package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AverageTest {

    private static final double SMALL_VALUE = 0.00001d;

    @Test
    public void testAverage_double_12() {
        double[] numbers = {3d, 6d, 9d, 12d, 15d, 18d, 21d};
        Assertions.assertEquals(12d, Average.average(numbers), SMALL_VALUE);
    }

    @Test
    public void testAverage_double_20() {
        double[] numbers = {5d, 10d, 15d, 20d, 25d, 30d, 35d};
        Assertions.assertEquals(20d, Average.average(numbers), SMALL_VALUE);
    }

    @Test
    public void testAverage_double_4_5() {
        double[] numbers = {1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d};
        Assertions.assertEquals(4.5d, Average.average(numbers), SMALL_VALUE);
    }

    @Test
    public void testAverage_int_5() {
        int[] numbers = {2, 4, 10};
        Assertions.assertEquals(5, Average.average(numbers));
    }

}
