package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MedianOfMatrixtest {

    @Test
    public void testMedianWithOddNumberOfElements() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(1, 3, 5));
        matrix.add(Arrays.asList(2, 4, 6));
        matrix.add(Arrays.asList(7, 8, 9));

        int result = MedianOfMatrix.median(matrix);

        assertEquals(5, result);
    }

    @Test
    public void testMedianWithEvenNumberOfElements() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(2, 4));
        matrix.add(Arrays.asList(1, 3));

        int result = MedianOfMatrix.median(matrix);

        assertEquals(2, result);
    }
}
