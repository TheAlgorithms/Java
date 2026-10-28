package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BitonicSortTest extends SortingAlgorithmTest {
    @Override
    SortAlgorithm getSortAlgorithm() {
        return new BitonicSort();
    }

    @Test
    void shouldRejectNullArray() {
        assertThrows(IllegalArgumentException.class, () -> getSortAlgorithm().sort((Integer[]) null));
    }
}
