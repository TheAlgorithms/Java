package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SelfOrganizingLinkedListTest {
    private SelfOrganizingLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new SelfOrganizingLinkedList<>();
    }

    @Test
    void testSearchOnEmptyList() {
        assertFalse(list.search(10));
        assertNull(list.getHeadValue());
    }

    @Test
    void testSearchElementAtHeadValueDoesNotChangeStructure() {
        list.insert(10);
        list.insert(20);
        list.insert(30);

        assertTrue(list.search(10));
        assertEquals(10, list.getHeadValue());
    }

    @Test
    void testMoveMiddleElementToFront() {
        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.insert(40);

        // Search middle element '30'
        assertTrue(list.search(30));

        // '30' should now be the new head
        assertEquals(30, list.getHeadValue());
    }

    @Test
    void testMoveLastElementToFront() {
        list.insert(10);
        list.insert(20);
        list.insert(30);

        // Search last element '30'
        assertTrue(list.search(30));

        assertEquals(30, list.getHeadValue());
    }

    @Test
    void testSearchNonExistentElement() {
        list.insert(10);
        list.insert(20);

        assertFalse(list.search(99));
        assertEquals(10, list.getHeadValue()); // Head remains unchanged
    }

    @Test
    void testMultipleSearchesSequentialMoveToFront() {
        list.insert(1);
        list.insert(2);
        list.insert(3);

        list.search(2); // Head becomes 2
        assertEquals(2, list.getHeadValue());

        list.search(3); // Head becomes 3
        assertEquals(3, list.getHeadValue());

        list.search(1); // Head becomes 1
        assertEquals(1, list.getHeadValue());
    }

    
}
