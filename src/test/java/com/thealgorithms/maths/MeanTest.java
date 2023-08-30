package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MeanTest {

    @Test
    void arithmeticMeanZeroNumbers() {
        double[] numbers = {};
        assertEquals(0, Mean.arithmeticMean(numbers));
    }

    @Test
    void geometricMeanZeroNumbers() {
        double[] numbers = {};
        assertEquals(1, Mean.geometricMean(numbers));
    }

    @Test
    void harmonicMeanZeroNumbers() {
        double[] numbers = {};
        assertEquals(0, Mean.harmonicMean(numbers));
    }

    @Test
    void arithmeticMeanSingleNumber() {
        double[] numbers = {2.5};
        assertEquals(2.5, Mean.arithmeticMean(numbers));
    }

    @Test
    void geometricMeanSingleNumber() {
        double[] numbers = {2.5};
        assertEquals(2.5, Mean.geometricMean(numbers));
    }

    @Test
    void harmonicMeanSingleNumber() {
        double[] numbers = {2.5};
        assertEquals(2.5, Mean.geometricMean(numbers));
    }

    @Test
    void arithmeticMeanMultipleNumbers() {
        double[] numbers = {1, 2.5, 83.3, 25.9999, 46.0001, 74.7, 74.5};
        assertEquals(44, Mean.arithmeticMean(numbers));
    }

    @Test
    void geometricMeanMultipleNumbers() {
        double[] numbers = {1, 2, 3, 4, 5, 6, 1.25};
        assertEquals(2.6426195539300585, Mean.geometricMean(numbers));
    }

    @Test
    void harmonicMeanMultipleNumbers() {
        double[] numbers = {1, 2.5, 83.3, 25.9999, 46.0001, 74.7, 74.5};
        assertEquals(4.6697322801074135, Mean.harmonicMean(numbers));
    }
}