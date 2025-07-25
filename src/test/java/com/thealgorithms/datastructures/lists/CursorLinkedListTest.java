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

    @Test
    void testSingleElementOperations() {
        // Test operations with just one element
        list.append("Only");
        assertEquals("Only", list.get(0));
        assertEquals(0, list.indexOf("Only"));

        list.remove("Only");
        assertNull(list.get(0));
        assertEquals(-1, list.indexOf("Only"));
    }

    @Test
    void testDuplicateElements() {
        // Test handling of duplicate elements
        list.append("Duplicate");
        list.append("Other");
        list.append("Duplicate");

        assertEquals(0, list.indexOf("Duplicate")); // Should return first occurrence
        assertEquals("Duplicate", list.get(0));
        assertEquals("Duplicate", list.get(2));

        list.remove("Duplicate"); // Should remove first occurrence
        assertEquals("Other", list.get(0));
        assertEquals("Duplicate", list.get(1));
    }

    @Test
    void testRemoveByIndexEdgeCases() {
        list.append("First");
        list.append("Second");
        list.append("Third");

        // Test removing invalid indices
        list.removeByIndex(-1); // Should not crash
        list.removeByIndex(10); // Should not crash

        // Verify list unchanged
        assertEquals("First", list.get(0));
        assertEquals("Second", list.get(1));
        assertEquals("Third", list.get(2));

        // Test removing first element by index
        list.removeByIndex(0);
        assertEquals("Second", list.get(0));
        assertEquals("Third", list.get(1));
    }

    @Test
    void testRemoveByIndexLastElement() {
        list.append("First");
        list.append("Second");
        list.append("Third");

        // Remove last element by index
        list.removeByIndex(2);
        assertEquals("First", list.get(0));
        assertEquals("Second", list.get(1));
        assertNull(list.get(2));
    }

    @Test
    void testConsecutiveOperations() {
        // Test multiple consecutive operations
        list.append("A");
        list.append("B");
        list.append("C");
        list.append("D");

        list.remove("B");
        list.remove("D");

        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
        assertNull(list.get(2));

        list.append("E");
        assertEquals("E", list.get(2));
    }

    @Test
    void testMemoryReclamation() {
        // Test that removed elements free up memory space
        for (int i = 0; i < 50; i++) {
            list.append("Element" + i);
        }

        // Remove some elements
        for (int i = 0; i < 25; i++) {
            list.remove("Element" + i);
        }

        // Should be able to add more elements (testing memory reclamation)
        for (int i = 100; i < 150; i++) {
            list.append("New" + i);
        }

        // Verify some elements exist
        assertEquals("Element25", list.get(0));
        assertEquals("New100", list.get(25));
    }

    @Test
    void testSpecialCharacters() {
        // Test with strings containing special characters
        list.append("Hello World!");
        list.append("Test@123");
        list.append("Special#$%");
        list.append(""); // Empty string

        assertEquals("Hello World!", list.get(0));
        assertEquals("Test@123", list.get(1));
        assertEquals("Special#$%", list.get(2));
        assertEquals("", list.get(3));

        assertEquals(3, list.indexOf(""));
    }

    @Test
    void testLargeIndices() {
        list.append("Test");

        // Test very large indices
        assertNull(list.get(Integer.MAX_VALUE));
        assertNull(list.get(1000));
    }

    @Test
    void testSequentialRemovalByIndex() {
        list.append("A");
        list.append("B");
        list.append("C");
        list.append("D");

        // Remove elements sequentially by index
        list.removeByIndex(1); // Remove "B"
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
        assertEquals("D", list.get(2));

        list.removeByIndex(1); // Remove "C"
        assertEquals("A", list.get(0));
        assertEquals("D", list.get(1));
        assertNull(list.get(2));
    }

    @Test
    void testAppendAfterRemoval() {
        list.append("First");
        list.append("Second");

        list.remove("First");
        list.append("Third");

        assertEquals("Second", list.get(0));
        assertEquals("Third", list.get(1));
        assertEquals(1, list.indexOf("Third"));
    }

    @Test
    void testPerformanceWithManyOperations() {
        // Test with many mixed operations
        for (int i = 0; i < 50; i++) {
            list.append("Item" + i);
        }

        // Remove every other element
        for (int i = 0; i < 50; i += 2) {
            list.remove("Item" + i);
        }

        // Verify remaining elements
        assertEquals("Item1", list.get(0));
        assertEquals("Item3", list.get(1));
        assertEquals("Item5", list.get(2));

        // Add more elements
        for (int i = 100; i < 110; i++) {
            list.append("New" + i);
        }

        assertEquals("New100", list.get(25));
    }
}
