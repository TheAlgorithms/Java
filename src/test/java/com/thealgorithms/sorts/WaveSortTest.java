package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WaveSortTest {
    @Test
    public void testWaveSortGeneralCase() {
        Integer[] array = {7, 7, 11, 3, 4, 5, 15};
        WaveSort waveSort = new WaveSort();
        assertTrue(isWaveSorted(waveSort.sort(array)));
    }

    @Test
    public void testWaveSortAscendingOrder() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        WaveSort waveSort = new WaveSort();
        assertTrue(isWaveSorted(waveSort.sort(array)));
    }

    @Test
    public void testWaveSortDescendingOrder() {
        Integer[] array = {8, 7, 6, 5, 4, 3, 2, 1};
        WaveSort waveSort = new WaveSort();
        assertTrue(isWaveSorted(waveSort.sort(array)));
    }

    @Test
    public void testWaveSortSameElements() {
        Integer[] array = {3, 3, 3, 3};
        WaveSort waveSort = new WaveSort();
        assertTrue(isWaveSorted(waveSort.sort(array)));
    }

    @Test
    public void testWaveSortNegativeElements() {
        Integer[] array = {-1, -3, -2, -4, -6, -5};
        WaveSort waveSort = new WaveSort();
        assertTrue(isWaveSorted(waveSort.sort(array)));
    }

    @Test
    public void testWaveSortSingleElement() {
        Integer[] array = {1};
        WaveSort waveSort = new WaveSort();
        assertTrue(isWaveSorted(waveSort.sort(array)));
    }

    @Test
    public void testWaveSortTwoElements() {
        Integer[] array = {2, 1};
        WaveSort waveSort = new WaveSort();
        assertTrue(isWaveSorted(waveSort.sort(array)));
    }

    @Test
    public void testWaveSortLargeMixedElements() {
        Integer[] array = {5, 3, 1, 2, 9, 7, 6, 8, 4, 0};
        WaveSort waveSort = new WaveSort();
        assertTrue(isWaveSorted(waveSort.sort(array)));
    }

    @Test
    public void testWaveSortEmptyArray() {
        Integer[] array = {};
        WaveSort waveSort = new WaveSort();
        assertTrue(isWaveSorted(waveSort.sort(array)));
    }

    @Test
    public void testWaveSortMixedPositiveNegativeZero() {
        Integer[] array = {0, 5, -3, 2, -1, 4, -2, 1, 3};
        WaveSort waveSort = new WaveSort();
        assertTrue(isWaveSorted(waveSort.sort(array)));
    }

    private <T extends Comparable<T>> boolean isWaveSorted(T[] array) {
        for (int i = 0; i < array.length; i += 2) {
            if (i > 0 && SortUtils.less(array[i], array[i - 1])) {
                return false;
            }
            if (i < array.length - 1 && SortUtils.less(array[i], array[i + 1])) {
                return false;
            }
        }
        return true;
    }
}
