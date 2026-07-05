package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareRootWithNewtonRaphsonTestMethod {

    @Test
    void test_for_one() {
        Assertions.assertEquals(1, SquareRootWithNewtonRaphsonMethod.squareRoot(1));
    }

    @Test
    void test_for_two() {
        Assertions.assertEquals(1.414213562373095, SquareRootWithNewtonRaphsonMethod.squareRoot(2));
    }

    @Test
    void test_for_six_hundred_twenty_five() {
        Assertions.assertEquals(25.0, SquareRootWithNewtonRaphsonMethod.squareRoot(625));
    }
}
