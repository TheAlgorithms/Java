package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.Assertions.assertEquals;

public class SumOfDigitsTest {
    @Test
    public void TestSumOfDigitsRecursion(){

        Assertions.assertEquals(6, SumOfDigits.sumOfDigitsRecursion(-123));
        Assertions.assertEquals(15, SumOfDigits.sumOfDigitsRecursion(456));
        Assertions.assertEquals(9, SumOfDigits.sumOfDigitsRecursion(-292));
    }
} 