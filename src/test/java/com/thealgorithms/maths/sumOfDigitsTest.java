package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class sumOfDigitsTest {
    /**
    * Test case for float input
    */
    @Test
    public void testSumWithFloat(){
        Assertions.assertEquals(9, SumOfDigits.sumOfDigitsRecursion(2.34));
    }
    /**
     * Test case with input equal 0
     */
    @Test
    public void testSumWith0(){
        Assertions.assertEquals(0, SumOfDigits.sumOfDigitsRecursion(0));
    }
    /**
     * Test case for valid input
     */
    @Test
    public void testSumWithPositiveInput(){
        Assertions.assertEquals(15, SumOfDigits.sumOfDigitsRecursion(456));
    }
    /**
     * Test case with Negative number
     */
    @Test
    public void testSumWithNegativeInput(){
        Assertions.assertEquals(6, SumOfDigits.sumOfDigitsRecursion(-123));
    }
}