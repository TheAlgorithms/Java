package com.thealgorithms.maths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuadraticEqnTest {
    @Test
    public void testSolve() {
        double a = 1;
        double b = -3;
        double delta = 5;
        double[] expectedResult = {-1,4}; // x1 and x2
        double[] result = QuadraticEquation.solve(a, b, delta);

        Assertions.assertArrayEquals(expectedResult, result);
    }
    @Test
    public void testSolve2() {
        double a = 1;
        double b = -2;
        double delta = 5;
        double[] expectedResult = {-1.5,3.5}; // x1 and x2
        double[] result = QuadraticEquation.solve(a, b, delta);

        Assertions.assertArrayEquals(expectedResult, result);
    }
    @Test
    public void testSolve3() {
        double a = 1;
        double b = -1;
        double delta = 5;
        double[] expectedResult = {-2,3}; // x1 and x2
        double[] result = QuadraticEquation.solve(a, b, delta);

        Assertions.assertArrayEquals(expectedResult, result);
    }
}
