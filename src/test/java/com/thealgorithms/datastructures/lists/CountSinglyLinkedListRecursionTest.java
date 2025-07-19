package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CountSinglyLinkedListRecursionTest {

    private CountSinglyLinkedListRecursion list;

    @BeforeEach
    public void setUp() {
        list = new CountSinglyLinkedListRecursion();
    }

    @Test
    public void testCountEmptyList() {
        // An empty list should have a count of 0
        assertEquals(0, list.count(), "Count of an empty list should be 0.");
    }

    @Test
    public void testCountSingleElementList() {
        // Insert a single element and check the count
        list.insert(1);
        assertEquals(1, list.count(), "Count of a single-element list should be 1.");
    }

    @Test
    public void testCountMultipleElements() {
        // Insert multiple elements and check the count
        for (int i = 1; i <= 5; i++) {
            list.insert(i);
        }
        assertEquals(5, list.count(), "Count of a list with 5 elements should be 5.");
    }

    @Test
    public void testCountWithDuplicateElements() {
        // Insert duplicate elements and verify the count is correct
        list.insert(1);
        list.insert(2);
        list.insert(2);
        list.insert(3);
        list.insert(3);
        assertEquals(5, list.count(), "Count of a list with duplicate elements should match total node count.");
    }

    @Test
    public void testCountAfterClearingList() {
        for (int i = 1; i <= 4; i++) {
            list.insert(i);
        }
        list.clear(); // assuming you have a clear method; if not, skip this
        assertEquals(0, list.count(), "Count after clearing the list should be 0.");
    }

    @Test
    public void testCountOnVeryLargeList() {
        int n = 1000;
        for (int i = 0; i < n; i++) {
            list.insert(i);
        }
        assertEquals(n, list.count(), "Count should correctly return for large list sizes.");
    }

    @Test
    public void testCountOnListWithNegativeNumbers() {
        list.insert(-1);
        list.insert(-5);
        list.insert(-10);
        assertEquals(3, list.count(), "Count should correctly handle negative values.");
    }

    @Test
    public void testCountIsConsistentWithoutModification() {
        list.insert(1);
        list.insert(2);
        int firstCount = list.count();
        int secondCount = list.count();
        assertEquals(firstCount, secondCount, "Repeated count calls should return consistent values.");
    }
}
