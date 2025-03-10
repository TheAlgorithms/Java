package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SortedLinkedListTest {

    private SortedLinkedList list;

    @BeforeEach
    public void setUp() {
        list = new SortedLinkedList();
    }

    @Test
    public void testInsertIntoEmptyList() {
        list.insert(5);
        assertEquals("[5]", list.toString());
    }

    @Test
    public void testInsertInSortedOrder() {
        list.insert(5);
        list.insert(3);
        list.insert(7);
        assertEquals("[3, 5, 7]", list.toString());
    }

    @Test
    public void testInsertDuplicateValues() {
        list.insert(5);
        list.insert(5);
        list.insert(5);
        assertEquals("[5, 5, 5]", list.toString());
    }

    @Test
    public void testDeleteHeadElement() {
        list.insert(1);
        list.insert(2);
        list.insert(3);
        assertTrue(list.delete(1));
        assertEquals("[2, 3]", list.toString());
    }

    @Test
    public void testDeleteTailElement() {
        list.insert(1);
        list.insert(2);
        list.insert(3);
        assertTrue(list.delete(3));
        assertEquals("[1, 2]", list.toString());
    }

    @Test
    public void testDeleteMiddleElement() {
        list.insert(1);
        list.insert(2);
        list.insert(3);
        assertTrue(list.delete(2));
        assertEquals("[1, 3]", list.toString());
    }

    @Test
    public void testDeleteNonexistentElement() {
        list.insert(1);
        list.insert(2);
        assertFalse(list.delete(3));
    }

    @Test
    public void testDeleteFromSingleElementList() {
        list.insert(5);
        assertTrue(list.delete(5));
        assertEquals("[]", list.toString());
    }

    @Test
    public void testDeleteFromEmptyList() {
        assertFalse(list.delete(5));
    }

    @Test
    public void testSearchInEmptyList() {
        assertFalse(list.search(5));
    }

    @Test
    public void testSearchForExistingElement() {
        list.insert(3);
        list.insert(1);
        list.insert(5);
        assertTrue(list.search(3));
    }

    @Test
    public void testSearchForNonexistentElement() {
        list.insert(3);
        list.insert(1);
        list.insert(5);
        assertFalse(list.search(10));
    }

    @Test
    public void testIsEmptyOnEmptyList() {
        assertTrue(list.isEmpty());
    }

    @Test
    public void testIsEmptyOnNonEmptyList() {
        list.insert(10);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testIsEmptyAfterInsertion() {
        list.insert(10);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testIsEmptyAfterDeletion() {
        list.insert(10);
        list.delete(10);
        assertTrue(list.isEmpty());
    }
}
