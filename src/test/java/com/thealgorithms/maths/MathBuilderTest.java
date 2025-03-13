package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MathBuilderTest {

    @Test
    void simpleMath() {
        double result = new MathBuilder.Builder(100).add(200).multiply(10).print().divideIf(20, (a, b) -> a % 2 == 0).sqrt().print().ceil().build().get();
        assertEquals(13, result);
    }

    @Test
    void memoryTest() {
        long result = new MathBuilder.Builder().set(100).sqrt().remember().add(21).recallIf(a -> a < 50, true).mod(2).build().toLong();
        assertEquals(0, result);
    }

    @Test
    void freeFallDistance() {
        long distance = new MathBuilder.Builder(9.81).multiply(0.5).multiply(5 * 5).round().build().toLong();
        assertEquals(123, distance); // Expected result: 0.5 * 9.81 * 25 = 122.625 ≈ 123
    }

    @Test
    void batchSalaryProcessing() {
        double[] salaries = {2000, 3000, 4000, 5000};
        long[] processedSalaries = new long[salaries.length];
        for (int i = 0; i < salaries.length; i++) {
            processedSalaries[i] = new MathBuilder.Builder(salaries[i]).addIf(salaries[i] * 0.1, (sal, bonus) -> sal > 2500).multiply(0.92).round().build().toLong();
        }
        long[] expectedSalaries = {1840, 3036, 4048, 5060};
        assertArrayEquals(expectedSalaries, processedSalaries);
    }

    @Test
    void parenthesis() {
        // 10 + (20*5) - 40 + (100 / 10) = 80
        double result = new MathBuilder.Builder(10).openParenthesis(20).multiply(5).closeParenthesisAndPlus().minus(40).openParenthesis(100).divide(10).closeParenthesisAndPlus().build().get();
        assertEquals(80, result);
    }

    @Test
    void areaOfCircle() {
        // Radius is 4
        double area = new MathBuilder.Builder().pi().openParenthesis(4).multiply(4).closeParenthesisAndMultiply().build().get();
        assertEquals(Math.PI * 4 * 4, area);
    }
}
