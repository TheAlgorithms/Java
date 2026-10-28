package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class TournamentSortTest extends SortingAlgorithmTest {

    @Test
    void shouldAcceptWhenNullArrayIsPassed() {
        Integer[] array = null;
        assertNull(getSortAlgorithm().sort(array));
    }

    @Override
    SortAlgorithm getSortAlgorithm() {
        return new TournamentSort();
    }
}
