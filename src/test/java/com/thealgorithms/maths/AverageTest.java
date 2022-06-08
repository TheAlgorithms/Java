package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class AverageTest {
    double [] numbers = {3, 6, 9, 12, 15, 18, 21};
    @Test
    public void testAverage() {
        
        Assertions.assertEquals(12, Average.average(numbers));
    }
}
