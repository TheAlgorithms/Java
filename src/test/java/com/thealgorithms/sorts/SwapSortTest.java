package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class SwapSortTest extends SortingAlgorithmTest {
    @Override
    SortAlgorithm getSortAlgorithm() {
        return new SwapSort();
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    void shouldSortWhenDuplicateWouldBeSwappedOntoItsTwin() {
        Integer[] array = {2, 2, 1, 1, 0, 0, 0};
        assertArrayEquals(new Integer[] {0, 0, 0, 1, 1, 2, 2}, new SwapSort().sort(array));
    }
}
