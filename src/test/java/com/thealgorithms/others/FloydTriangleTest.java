package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FloydTriangleTest {

    @Test
    public void testGenerateFloydTriangleWithValidInput() {
        String expectedOutput = "1 \n2 3 \n4 5 6 ";
        assertEquals(expectedOutput, FloydTriangle.generateFloydTriangle(3));
    }

    @Test
    public void testGenerateFloydTriangleWithOneRow() {
        String expectedOutput = "1 ";
        assertEquals(expectedOutput, FloydTriangle.generateFloydTriangle(1));
    }

    @Test
    public void testGenerateFloydTriangleWithZeroRows() {
        assertEquals("", FloydTriangle.generateFloydTriangle(0));
    }

    @Test
    public void testGenerateFloydTriangleWithNegativeRows() {
        assertEquals("", FloydTriangle.generateFloydTriangle(-3));
    }

    @Test
    public void testGenerateFloydTriangleWithMultipleRows() {
        String expectedOutput = "1 \n2 3 \n4 5 6 \n7 8 9 10 \n11 12 13 14 15 ";
        assertEquals(expectedOutput, FloydTriangle.generateFloydTriangle(5));
    }
}
