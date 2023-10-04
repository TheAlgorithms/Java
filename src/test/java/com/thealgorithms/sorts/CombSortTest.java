package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

/**
 * @author Tabbygray (https://github.com/Tabbygray)
 * @see CombSort
 */

public class CombSortTest {

    private CombSort combSort = new CombSort();

    @Test
    public void combSortEmptyArray() {
        Integer[] inputArray = {};
        Integer[] outputArray = combSort.sort(inputArray);
        Integer[] expectedOutput = {};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void combSortSingleStringElement() {
        String[] inputArray = {"Test"};
        String[] outputArray = combSort.sort(inputArray);
        String[] expectedArray = {"Test"};
        assertArrayEquals(outputArray, expectedArray);
    }

    @Test
    public void combSortStringArray() {
        String[] inputArray = {"4gp8", "aBJ2", "85cW", "Pmk9", "ewZO", "meuU", "RhNd", "5TKB", "eDd5", "zzyo"};
        String[] outputArray = combSort.sort(inputArray);
        String[] expectedArray = {"4gp8", "5TKB", "85cW", "Pmk9", "RhNd", "aBJ2", "eDd5", "ewZO", "meuU", "zzyo"};
        assertArrayEquals(outputArray, expectedArray);
    }

    @Test
    public void combSortIntegerArray() {
        Integer[] inputArray = {36, 98, -51, -23, 66, -58, 31, 25, -30, 40};
        Integer[] outputArray = combSort.sort(inputArray);
        Integer[] expectedArray = {-58, -51, -30, -23, 25, 31, 36, 40, 66, 98};
        assertArrayEquals(outputArray, expectedArray);
    }

    @Test
    public void combSortDoubleArray() {
        Double[] inputArray = {0.8335545399, 0.9346214114, 0.3096396752, 0.6433840668, 0.3973191975, 0.6118850724, 0.0553975453, 0.1961108601, 0.6172800885, 0.1065247772};
        Double[] outputArray = combSort.sort(inputArray);
        Double[] expectedArray = {
            0.0553975453,
            0.1065247772,
            0.1961108601,
            0.3096396752,
            0.3973191975,
            0.6118850724,
            0.6172800885,
            0.6433840668,
            0.8335545399,
            0.9346214114,
        };
        assertArrayEquals(outputArray, expectedArray);
    }
}
