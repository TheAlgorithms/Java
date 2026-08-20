package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SelfOrganizingLinkedListTest {

    private SelfOrganizingLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new SelfOrganizingLinkedList<>();
    }

    /** Helper to extract all list elements in order via repeated search/head inspection. */
    private List<Integer> toList() {
        List<Integer> result = new ArrayList<>();
        int currentSize = list.getSize();
        if (currentSize == 0) {
            return result;
        }

        // Search each node sequentially from head to verify integrity without breaking state
        for (int i = 0; i < currentSize; i++) {
            Integer val = list.getHeadValue();
            result.add(val);
            // Move head to back by searching for it, allowing us to inspect the next element
            // or we can verify order by tracking transitions.
        }
        return result;
    }

    @Test
    void testEmptyListAndGetters() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.getSize());
        assertNull(list.getHeadValue());
        assertFalse(list.search(10));
    }

    @Test
    void testInsertAndSizeState() {
        assertTrue(list.isEmpty());
        list.insert(10);
        assertFalse(list.isEmpty());
        assertEquals(1, list.getSize());

        list.insert(20);
        assertEquals(2, list.getSize());
    }

    @Test
    void testMoveMiddleElementToFrontPreservesFullListStructure() {
        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.insert(40);

        // Initial order: [10, 20, 30, 40]
        assertTrue(list.search(30));

        // Expected order: [30, 10, 20, 40]
        assertEquals(4, list.getSize());
        assertEquals(30, list.getHeadValue());

        // Sequential head tracking to verify middle and tail pointers didn't break
        assertTrue(list.search(10)); // [10, 30, 20, 40]
        assertEquals(10, list.getHeadValue());

        assertTrue(list.search(20)); // [20, 10, 30, 40]
        assertEquals(20, list.getHeadValue());

        assertTrue(list.search(40)); // [40, 20, 10, 30]
        assertEquals(40, list.getHeadValue());
        assertEquals(4, list.getSize());
    }

    @Test
    void testMoveLastElementToFrontPreservesFullListStructure() {
        list.insert(10);
        list.insert(20);
        list.insert(30);

        // Search tail element '30'
        assertTrue(list.search(30));// Order becomes [30, 10, 20]

        assertEquals(30, list.getHeadValue());
        assertEquals(3, list.getSize());

        // Verify remaining chain order [10, 20]
        assertTrue(list.search(20));// [20, 30, 10]
        assertEquals(20, list.getHeadValue());

        assertTrue(list.search(10)); // [10, 20, 30]
        assertEquals(10, list.getHeadValue());
        assertEquals(3, list.getSize());
    }

    @Test
    void testSearchNonExistentElementPreservesStructureAndSize() {
        list.insert(10);
        list.insert(20);
        list.insert(30);

        assertFalse(list.search(99));
        assertEquals(3, list.getSize());
        assertEquals(10, list.getHeadValue());
    }

    @Test
    void testDuplicateValuesMovesFirstMatchedToFront() {
        list.insert(10);
        list.insert(20);
        list.insert(10);// Duplicate '10' at tail
        list.insert(30);

        // Initial list state: [10, 20, 10, 30]
        // Searching '10' hits the head immediately -> no re-linking
        assertTrue(list.search(10));
        assertEquals(10, list.getHeadValue());
        assertEquals(4, list.getSize());

        // Searching '20' moves middle element to head: [20, 10, 10, 30]
        assertTrue(list.search(20));
        assertEquals(20, list.getHeadValue());

        // Searching '10' moves the FIRST instance of '10' (index 1) to head: [10, 20, 10, 30]
        assertTrue(list.search(10));
        assertEquals(10, list.getHeadValue());
        assertEquals(4, list.getSize());
    }
}
