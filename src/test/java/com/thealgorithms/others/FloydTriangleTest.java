package com.thealgorithms.others;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FloydTriangleTest {

    @Test
    public void testGenerateFloydTriangleWithValidInput() {
        List<List<Integer>> expectedOutput = Arrays.asList(singletonList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        assertEquals(expectedOutput, FloydTriangle.generateFloydTriangle(3));
    }

    @Test
    public void testGenerateFloydTriangleWithOneRow() {
        List<List<Integer>> expectedOutput = singletonList(singletonList(1));
        assertEquals(expectedOutput, FloydTriangle.generateFloydTriangle(1));
    }

    @Test
    public void testGenerateFloydTriangleWithZeroRows() {
        List<List<Integer>> expectedOutput = emptyList();
        assertEquals(expectedOutput, FloydTriangle.generateFloydTriangle(0));
    }

    @Test
    public void testGenerateFloydTriangleWithNegativeRows() {
        List<List<Integer>> expectedOutput = emptyList();
        assertEquals(expectedOutput, FloydTriangle.generateFloydTriangle(-3));
    }

    @Test
    public void testGenerateFloydTriangleWithMultipleRows() {
        List<List<Integer>> expectedOutput = Arrays.asList(singletonList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9, 10), Arrays.asList(11, 12, 13, 14, 15));
        assertEquals(expectedOutput, FloydTriangle.generateFloydTriangle(5));
    }

    @Test
    public void testGenerateFloydTriangleWithMoreMultipleRows() {
        List<List<Integer>> expectedOutput = Arrays.asList(singletonList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9, 10), Arrays.asList(11, 12, 13, 14, 15), Arrays.asList(16, 17, 18, 19, 20, 21), Arrays.asList(22, 23, 24, 25, 26, 27, 28));
        assertEquals(expectedOutput, FloydTriangle.generateFloydTriangle(7));
    }
}
