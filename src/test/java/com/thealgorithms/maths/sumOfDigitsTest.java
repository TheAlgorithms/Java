package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class sumOfDigitsTest {
    @Test
    void test1(){
        Assertions.assertEquals(6, SumOfDigits.sumOfDigitsRecursion(123));
    }

    @Test
    void test2(){
        Assertions.assertEquals(15, SumOfDigits.sumOfDigitsRecursion(456));
    }

}