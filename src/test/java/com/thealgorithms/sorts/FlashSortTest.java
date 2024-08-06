package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class FlashSortTest extends SortingAlgorithmTest {
    @Override
    SortAlgorithm getSortAlgorithm() {
        return new FlashSort();
    }

    @Test
    public void testDefaultConstructor() {
        double defaultRation = 0.45;
        FlashSort sorter = new FlashSort();
        assertEquals(defaultRation, sorter.getClassificationRatio());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.9})
    public void testCustomConstructorValidRatio(double ratio) {
        FlashSort sorter = new FlashSort(ratio);
        assertEquals(ratio, sorter.getClassificationRatio());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 1, -0.1, 1.1})
    public void testCustomConstructorInvalidRatio(double ratio) {
        assertThrows(IllegalArgumentException.class, () -> new FlashSort(ratio));
    }
}
