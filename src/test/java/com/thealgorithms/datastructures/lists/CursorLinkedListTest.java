package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CursorLinkedListTest {
    private CursorLinkedList<String> list;

    @BeforeEach
    void setUp() {
        list = new CursorLinkedList<>();
    }

    @Test
    void testAppendAndGet() {
        list.append("First");
        list.append("Second");
        list.append("Third");

        assertEquals("First", list.get(0));
        assertEquals("Second", list.get(1));
        assertEquals("Third", list.get(2));
        assertNull(list.get(3));
        assertNull(list.get(-1));
    }

    @Test
    void testIndexOf() {
        list.append("First");
        list.append("Second");
        list.append("Third");

        assertEquals(0, list.indexOf("First"));
        assertEquals(1, list.indexOf("Second"));
        assertEquals(2, list.indexOf("Third"));
        assertEquals(-1, list.indexOf("NonExistent"));
    }

    @Test
    void testRemove() {
        list.append("First");
        list.append("Second");
        list.append("Third");

        list.remove("Second");
        assertEquals("First", list.get(0));
        assertEquals("Third", list.get(1));
        assertNull(list.get(2));
        assertEquals(-1, list.indexOf("Second"));
    }

    @Test
    void testRemoveByIndex() {
        list.append("First");
        list.append("Second");
        list.append("Third");

        list.removeByIndex(1);
        assertEquals("First", list.get(0));
        assertEquals("Third", list.get(1));
        assertNull(list.get(2));
    }

    @Test
    void testRemoveFirstElement() {
        list.append("First");
        list.append("Second");

        list.remove("First");
        assertEquals("Second", list.get(0));
        assertNull(list.get(1));
        assertEquals(-1, list.indexOf("First"));
    }

    @Test
    void testRemoveLastElement() {
        list.append("First");
        list.append("Second");

        list.remove("Second");
        assertEquals("First", list.get(0));
        assertNull(list.get(1));
        assertEquals(-1, list.indexOf("Second"));
    }

    @Test
    void testNullHandling() {
        assertThrows(NullPointerException.class, () -> list.append(null));
        assertThrows(NullPointerException.class, () -> list.remove(null));
        assertThrows(NullPointerException.class, () -> list.indexOf(null));
    }

    @Test
    void testEmptyList() {
        assertNull(list.get(0));
        assertEquals(-1, list.indexOf("Any"));
    }

    @Test
    void testMemoryLimitExceeded() {
        // Test adding more elements than CURSOR_SPACE_SIZE
        assertThrows(OutOfMemoryError.class, () -> {
            for (int i = 0; i < 101; i++) { // CURSOR_SPACE_SIZE is 100
                list.append("Element" + i);
            }
        });
    }
}
