package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class FindKthNumberTest {
    @Test
    public void testFindKthMaxTypicalCases() {
        int[] array1 = {3, 2, 1, 4, 5};
        assertEquals(3, FindKthNumber.findKthMax(array1, 3));
        assertEquals(4, FindKthNumber.findKthMax(array1, 2));
        assertEquals(5, FindKthNumber.findKthMax(array1, 1));
        assertEquals(3, FindKthNumber.findKthMaxUsingHeap(array1, 3));
        assertEquals(4, FindKthNumber.findKthMaxUsingHeap(array1, 2));
        assertEquals(5, FindKthNumber.findKthMaxUsingHeap(array1, 1));

        int[] array2 = {7, 5, 8, 2, 1, 6};
        assertEquals(5, FindKthNumber.findKthMax(array2, 4));
        assertEquals(6, FindKthNumber.findKthMax(array2, 3));
        assertEquals(8, FindKthNumber.findKthMax(array2, 1));
        assertEquals(5, FindKthNumber.findKthMaxUsingHeap(array2, 4));
        assertEquals(6, FindKthNumber.findKthMaxUsingHeap(array2, 3));
        assertEquals(8, FindKthNumber.findKthMaxUsingHeap(array2, 1));
    }

    @Test
    public void testFindKthMaxEdgeCases() {
        int[] array1 = {1};
        assertEquals(1, FindKthNumber.findKthMax(array1, 1));
        assertEquals(1, FindKthNumber.findKthMaxUsingHeap(array1, 1));

        int[] array2 = {5, 3};
        assertEquals(5, FindKthNumber.findKthMax(array2, 1));
        assertEquals(3, FindKthNumber.findKthMax(array2, 2));
        assertEquals(5, FindKthNumber.findKthMaxUsingHeap(array2, 1));
        assertEquals(3, FindKthNumber.findKthMaxUsingHeap(array2, 2));
    }

    @Test
    public void testFindKthMaxInvalidK() {
        int[] array = {1, 2, 3, 4, 5};
        assertThrows(IllegalArgumentException.class, () -> FindKthNumber.findKthMax(array, 0));
        assertThrows(IllegalArgumentException.class, () -> FindKthNumber.findKthMax(array, 6));
        assertThrows(IllegalArgumentException.class, () -> FindKthNumber.findKthMaxUsingHeap(array, 0));
        assertThrows(IllegalArgumentException.class, () -> FindKthNumber.findKthMaxUsingHeap(array, 6));
    }

    @Test
    public void testFindKthMaxLargeArray() {
        int[] array = generateArray(1000);
        int k = new Random().nextInt(1, array.length);
        int result = FindKthNumber.findKthMax(array, k);
        int maxK = FindKthNumber.findKthMaxUsingHeap(array, k);
        Arrays.sort(array);
        assertEquals(array[array.length - k], result);
        assertEquals(array[array.length - k], maxK);
    }

    public static int[] generateArray(int capacity) {
        int size = new Random().nextInt(2, capacity);
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = new Random().nextInt(100); // Ensure positive values for testing
        }
        return array;
    }
}
