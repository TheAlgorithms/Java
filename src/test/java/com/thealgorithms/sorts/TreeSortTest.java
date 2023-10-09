package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

/**
 * @author Tabbygray (https://github.com/Tabbygray)
 * @see TreeSort
 */

public class TreeSortTest {
    private TreeSort treeSort = new TreeSort();

    @Test
    public void treeSortEmptyArray() {
        Integer[] inputArray = {};
        Integer[] outputArray = treeSort.sort(inputArray);
        Integer[] expectedOutput = {};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void treeSortSingleStringElement() {
        String[] inputArray = {"Test"};
        String[] outputArray = treeSort.sort(inputArray);
        String[] expectedArray = {"Test"};
        assertArrayEquals(outputArray, expectedArray);
    }

    @Test
    public void treeSortStringArray() {
        String[] inputArray = {"F6w9", "l1qz", "dIxH", "larj", "kRzy", "vnNH", "3ftM", "hc4n", "C5Qi", "btGF"};
        String[] outputArray = treeSort.sort(inputArray);
        String[] expectedArray = {"3ftM", "C5Qi", "F6w9", "btGF", "dIxH", "hc4n", "kRzy", "l1qz", "larj", "vnNH"};
        assertArrayEquals(outputArray, expectedArray);
    }

    @Test
    public void treeSortIntegerArray() {
        Integer[] inputArray = {-97, -44, -4, -85, -92, 74, 79, -26, 76, -5};
        Integer[] outputArray = treeSort.sort(inputArray);
        Integer[] expectedArray = {-97, -92, -85, -44, -26, -5, -4, 74, 76, 79};
        assertArrayEquals(outputArray, expectedArray);
    }

    @Test
    public void treeSortDoubleArray() {
        Double[] inputArray = {0.8047485045, 0.4493112337, 0.8298433723, 0.2691406748, 0.2482782839, 0.5976243420, 0.6746235284, 0.0552623569, 0.3515624123, 0.0536747336};
        Double[] outputArray = treeSort.sort(inputArray);
        Double[] expectedArray = {0.0536747336, 0.0552623569, 0.2482782839, 0.2691406748, 0.3515624123, 0.4493112337, 0.5976243420, 0.6746235284, 0.8047485045, 0.8298433723};
        assertArrayEquals(outputArray, expectedArray);
    }
}
