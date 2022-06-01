package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.Assertions.assertEquals;

public class SumOfDigitsTest {
    @Test
    public void TestSumOfDigitsRecursion(){

        Assertions.assertEquals(6, SumOfDigits.SumOfDigitsRecursion(-123));
        Assertions.assertEquals(15, SumOfDigits.SumOfDigitsRecursion(456));
        Assertions.assertEquals(18, SumOfDigits.SumOfDigitsRecursion(-9,12,15));
    }
} 