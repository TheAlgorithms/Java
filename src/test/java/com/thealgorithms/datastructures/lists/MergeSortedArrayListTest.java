package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class MergeSortedArrayListTest {

    @Test
    void testMergeTwoSortedLists() {
        List<Integer> listA = Arrays.asList(1, 3, 5, 7, 9);
        List<Integer> listB = Arrays.asList(2, 4, 6, 8, 10);
        List<Integer> result = new ArrayList<>();

        MergeSortedArrayList.merge(listA, listB, result);

        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertEquals(expected, result, "Merged list should be sorted and contain all elements from both input lists.");
    }

    @Test
    void testMergeWithEmptyList() {
        List<Integer> listA = Arrays.asList(1, 2, 3);
        List<Integer> listB = new ArrayList<>(); // Empty list
        List<Integer> result = new ArrayList<>();

        MergeSortedArrayList.merge(listA, listB, result);

        List<Integer> expected = Arrays.asList(1, 2, 3);
        assertEquals(expected, result, "Merged list should match listA when listB is empty.");
    }

    @Test
    void testMergeWithBothEmptyLists() {
        List<Integer> listA = new ArrayList<>(); // Empty list
        List<Integer> listB = new ArrayList<>(); // Empty list
        List<Integer> result = new ArrayList<>();

        MergeSortedArrayList.merge(listA, listB, result);

        assertTrue(result.isEmpty(), "Merged list should be empty when both input lists are empty.");
    }

    @Test
    void testMergeWithDuplicateElements() {
        List<Integer> listA = Arrays.asList(1, 2, 2, 3);
        List<Integer> listB = Arrays.asList(2, 3, 4);
        List<Integer> result = new ArrayList<>();

        MergeSortedArrayList.merge(listA, listB, result);

        List<Integer> expected = Arrays.asList(1, 2, 2, 2, 3, 3, 4);
        assertEquals(expected, result, "Merged list should correctly handle and include duplicate elements.");
    }

    @Test
    void testMergeWithNegativeAndPositiveNumbers() {
        List<Integer> listA = Arrays.asList(-3, -1, 2);
        List<Integer> listB = Arrays.asList(-2, 0, 3);
        List<Integer> result = new ArrayList<>();

        MergeSortedArrayList.merge(listA, listB, result);

        List<Integer> expected = Arrays.asList(-3, -2, -1, 0, 2, 3);
        assertEquals(expected, result, "Merged list should correctly handle negative and positive numbers.");
    }

    @Test
    void testMergeThrowsExceptionOnNullInput() {
        List<Integer> listA = null;
        List<Integer> listB = Arrays.asList(1, 2, 3);
        List<Integer> result = new ArrayList<>();

        List<Integer> finalListB = listB;
        List<Integer> finalListA = listA;
        List<Integer> finalResult = result;
        assertThrows(NullPointerException.class, () -> MergeSortedArrayList.merge(finalListA, finalListB, finalResult), "Should throw NullPointerException if any input list is null.");

        listA = Arrays.asList(1, 2, 3);
        listB = null;
        List<Integer> finalListA1 = listA;
        List<Integer> finalListB1 = listB;
        List<Integer> finalResult1 = result;
        assertThrows(NullPointerException.class, () -> MergeSortedArrayList.merge(finalListA1, finalListB1, finalResult1), "Should throw NullPointerException if any input list is null.");

        listA = Arrays.asList(1, 2, 3);
        listB = Arrays.asList(4, 5, 6);
        result = null;
        List<Integer> finalListA2 = listA;
        List<Integer> finalListB2 = listB;
        List<Integer> finalResult2 = result;
        assertThrows(NullPointerException.class, () -> MergeSortedArrayList.merge(finalListA2, finalListB2, finalResult2), "Should throw NullPointerException if the result collection is null.");
    }
}
