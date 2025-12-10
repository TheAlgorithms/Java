package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CountSinglyLinkedListRecursionTest {

    private CountSinglyLinkedListRecursion list;

    @BeforeEach
    public void setUp() {
        list = new CountSinglyLinkedListRecursion();
    }

    @Test
    @DisplayName("Count of an empty list should be 0")
    public void testCountEmptyList() {
        assertEquals(0, list.count());
    }

    @Test
    @DisplayName("Count after inserting a single element should be 1")
    public void testCountSingleElementList() {
        list.insert(1);
        assertEquals(1, list.count());
    }

    @Test
    @DisplayName("Count after inserting multiple distinct elements")
    public void testCountMultipleElements() {
        for (int i = 1; i <= 5; i++) {
            list.insert(i);
        }
        assertEquals(5, list.count());
    }

    @Test
    @DisplayName("Count should reflect total number of nodes with duplicate values")
    public void testCountWithDuplicateElements() {
        list.insert(2);
        list.insert(2);
        list.insert(3);
        list.insert(3);
        list.insert(1);
        assertEquals(5, list.count());
    }

    @Test
    @DisplayName("Count should return 0 after clearing the list")
    public void testCountAfterClearingList() {
        for (int i = 1; i <= 4; i++) {
            list.insert(i);
        }
        list.clear(); // assumed to exist
        assertEquals(0, list.count());
    }

    @Test
    @DisplayName("Count on a very large list should be accurate")
    public void testCountOnVeryLargeList() {
        int n = 1000;
        for (int i = 0; i < n; i++) {
            list.insert(i);
        }
        assertEquals(n, list.count());
    }

    @Test
    @DisplayName("Count should work correctly with negative values")
    public void testCountOnListWithNegativeNumbers() {
        list.insert(-1);
        list.insert(-2);
        list.insert(-3);
        assertEquals(3, list.count());
    }

    @Test
    @DisplayName("Calling count multiple times should return the same value if list is unchanged")
    public void testCountIsConsistentWithoutModification() {
        list.insert(1);
        list.insert(2);
        int count1 = list.count();
        int count2 = list.count();
        assertEquals(count1, count2);
    }

    @Test
    @DisplayName("Count should reflect total even if all values are the same")
    public void testCountAllSameValues() {
        for (int i = 0; i < 5; i++) {
            list.insert(42);
        }
        assertEquals(5, list.count());
    }

    @Test
    @DisplayName("Count should remain correct after multiple interleaved insert and count operations")
    public void testCountAfterEachInsert() {
        assertEquals(0, list.count());
        list.insert(1);
        assertEquals(1, list.count());
        list.insert(2);
        assertEquals(2, list.count());
        list.insert(3);
        assertEquals(3, list.count());
    }

    @Test
    @DisplayName("List should not throw on edge count (0 nodes)")
    public void testEdgeCaseNoElements() {
        assertDoesNotThrow(() -> list.count());
    }

    @Test
    @DisplayName("Should count accurately after inserting then removing all elements")
    public void testCountAfterInsertAndClear() {
        for (int i = 0; i < 10; i++) {
            list.insert(i);
        }
        assertEquals(10, list.count());
        list.clear();
        assertEquals(0, list.count());
    }
}
