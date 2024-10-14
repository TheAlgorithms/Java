package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MergeSortedArrayListTest {

    @Test
    public void testMergeSortedWithSameSizeLists() {
        List<Integer> listA = Arrays.asList(1, 3, 5, 7, 9);
        List<Integer> listB = Arrays.asList(2, 4, 6, 8, 10);
        List<Integer> listC = new ArrayList<>();

        MergeSortedArrayList.merge(listA, listB, listC);

        // Expected merged result
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Assert the merged list is as expected
        assertEquals(expected, listC);
    }

    @Test
    public void testMergeSortedWithEmptyListA() {
        List<Integer> listA = new ArrayList<>(); // Empty list
        List<Integer> listB = Arrays.asList(2, 4, 6, 8, 10);
        List<Integer> listC = new ArrayList<>();

        MergeSortedArrayList.merge(listA, listB, listC);

        // Expected result is listB since listA is empty
        List<Integer> expected = Arrays.asList(2, 4, 6, 8, 10);
        assertEquals(expected, listC);
    }

    @Test
    public void testMergeSortedWithEmptyListB() {
        List<Integer> listA = Arrays.asList(1, 3, 5, 7, 9);
        List<Integer> listB = new ArrayList<>(); // Empty list
        List<Integer> listC = new ArrayList<>();

        MergeSortedArrayList.merge(listA, listB, listC);

        // Expected result is listA since listB is empty
        List<Integer> expected = Arrays.asList(1, 3, 5, 7, 9);
        assertEquals(expected, listC);
    }

    @Test
    public void testMergeSortedWithBothEmptyLists() {
        List<Integer> listA = new ArrayList<>(); // Empty list
        List<Integer> listB = new ArrayList<>(); // Empty list
        List<Integer> listC = new ArrayList<>();

        MergeSortedArrayList.merge(listA, listB, listC);

        // Expected result is an empty list since both inputs are empty
        List<Integer> expected = new ArrayList<>();
        assertEquals(expected, listC);
    }

    @Test
    public void testMergeSortedWithDifferentSizeLists() {
        List<Integer> listA = Arrays.asList(1, 3);
        List<Integer> listB = Arrays.asList(2, 4, 6, 8, 10);
        List<Integer> listC = new ArrayList<>();

        MergeSortedArrayList.merge(listA, listB, listC);

        // Expected merged result
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 6, 8, 10);
        assertEquals(expected, listC);
    }

    @Test
    public void testMergeSortedWithDuplicateElements() {
        List<Integer> listA = Arrays.asList(1, 3, 5);
        List<Integer> listB = Arrays.asList(1, 3, 5);
        List<Integer> listC = new ArrayList<>();

        MergeSortedArrayList.merge(listA, listB, listC);

        // Expected merged result with duplicates
        List<Integer> expected = Arrays.asList(1, 1, 3, 3, 5, 5);
        assertEquals(expected, listC);
    }

    @Test
    public void testMergeSortedWithNullListA() {
        List<Integer> listA = null; // Null list
        List<Integer> listB = Arrays.asList(2, 4, 6, 8, 10);
        List<Integer> listC = new ArrayList<>();

        assertThrows(NullPointerException.class, () -> MergeSortedArrayList.merge(listA, listB, listC));
    }

    @Test
    public void testMergeSortedWithNullListB() {
        List<Integer> listA = Arrays.asList(1, 3, 5, 7, 9);
        List<Integer> listB = null; // Null list
        List<Integer> listC = new ArrayList<>();

        assertThrows(NullPointerException.class, () -> MergeSortedArrayList.merge(listA, listB, listC));
    }

    @Test
    public void testMergeSortedWithBothNullLists() {
        List<Integer> listA = null; // Null list
        List<Integer> listB = null; // Null list
        List<Integer> listC = new ArrayList<>();

        assertThrows(NullPointerException.class, () -> MergeSortedArrayList.merge(listA, listB, listC));
    }

    @Test
    public void testMergeSortedNullLists() {
        List<Integer> listA = null; // Null list
        List<Integer> listB = null; // Null list
        List<Integer> listC = null; // Null list

        assertThrows(NullPointerException.class, () -> MergeSortedArrayList.merge(listA, listB, listC));
    }
}
