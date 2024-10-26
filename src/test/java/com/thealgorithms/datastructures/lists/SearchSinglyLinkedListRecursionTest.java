package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SearchSinglyLinkedListRecursionTest {

    private SearchSinglyLinkedListRecursion list;

    @BeforeEach
    public void setUp() {
        list = new SearchSinglyLinkedListRecursion();
    }

    @Test
    public void testSearchInEmptyList() {
        // Test searching for a value in an empty list (should return false)
        assertFalse(list.search(1));
    }

    @Test
    public void testSearchSingleElementListFound() {
        // Insert a single element and search for it
        list.insert(5);
        assertTrue(list.search(5));
    }

    @Test
    public void testSearchSingleElementListNotFound() {
        // Insert a single element and search for a non-existent value
        list.insert(5);
        assertFalse(list.search(10));
    }

    @Test
    public void testSearchMultipleElementsListFound() {
        // Insert multiple elements and search for a middle value
        for (int i = 1; i <= 10; i++) {
            list.insert(i);
        }
        assertTrue(list.search(5));
    }

    @Test
    public void testSearchMultipleElementsListFirstElement() {
        // Insert multiple elements and search for the first element
        for (int i = 1; i <= 10; i++) {
            list.insert(i);
        }
        assertTrue(list.search(1));
    }

    @Test
    public void testSearchMultipleElementsListLastElement() {
        // Insert multiple elements and search for the last element
        for (int i = 1; i <= 10; i++) {
            list.insert(i);
        }
        assertTrue(list.search(10));
    }

    @Test
    public void testSearchMultipleElementsListNotFound() {
        // Insert multiple elements and search for a non-existent element
        for (int i = 1; i <= 10; i++) {
            list.insert(i);
        }
        assertFalse(list.search(15));
    }

    @Test
    public void testSearchNegativeValues() {
        // Insert positive and negative values and search for a negative value
        list.insert(-5);
        list.insert(-10);
        list.insert(5);
        assertTrue(list.search(-10));
        assertFalse(list.search(-3));
    }

    @Test
    public void testSearchZeroValue() {
        list.insert(0);
        assertTrue(list.search(0));
    }
}
