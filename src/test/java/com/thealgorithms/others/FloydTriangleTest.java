package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class FloydTriangleTest {

    @Test
    public void testGenerateFloydTriangleWithValidInput() {
        List<List<Integer>> expectedOutput = Arrays.asList(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        assertEquals(expectedOutput, FloydTriangle.generateFloydTriangle(3));
    }

    @Test
    public void testGenerateFloydTriangleWithOneRow() {
        List<List<Integer>> expectedOutput = Arrays.asList(Arrays.asList(1));
        assertEquals(expectedOutput, FloydTriangle.generateFloydTriangle(1));
    }

    @Test
    public void testGenerateFloydTriangleWithZeroRows() {
        List<List<Integer>> expectedOutput = Arrays.asList();
        assertEquals(expectedOutput, FloydTriangle.generateFloydTriangle(0));
    }

    @Test
    public void testGenerateFloydTriangleWithNegativeRows() {
        List<List<Integer>> expectedOutput = Arrays.asList();
        assertEquals(expectedOutput, FloydTriangle.generateFloydTriangle(-3));
    }

    @Test
    public void testGenerateFloydTriangleWithMultipleRows() {
        List<List<Integer>> expectedOutput = Arrays.asList(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9, 10), Arrays.asList(11, 12, 13, 14, 15));
        assertEquals(expectedOutput, FloydTriangle.generateFloydTriangle(5));
    }
}

