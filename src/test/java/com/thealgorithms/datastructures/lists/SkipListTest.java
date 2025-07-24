package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SkipListTest {

    private SkipList<String> skipList;

    @BeforeEach
    void setUp() {
        skipList = new SkipList<>();
    }

    @Test
    @DisplayName("Add element and verify size and retrieval")
    void testAdd() {
        assertEquals(0, skipList.size());

        skipList.add("value");

        assertEquals(1, skipList.size());
        assertEquals("value", skipList.get(0));
    }

    @Test
    @DisplayName("Get retrieves correct element by index")
    void testGet() {
        skipList.add("value");
        assertEquals("value", skipList.get(0));
    }

    @Test
    @DisplayName("Contains returns true if element exists")
    void testContains() {
        skipList = createSkipList();
        assertTrue(skipList.contains("b"));
        assertTrue(skipList.contains("a"));
        assertFalse(skipList.contains("z")); // negative test
    }

    @Test
    @DisplayName("Remove element from head and check size and order")
    void testRemoveFromHead() {
        skipList = createSkipList();
        String first = skipList.get(0);
        int initialSize = skipList.size();

        skipList.remove(first);

        assertEquals(initialSize - 1, skipList.size());
        assertFalse(skipList.contains(first));
    }

    @Test
    @DisplayName("Remove element from tail and check size and order")
    void testRemoveFromTail() {
        skipList = createSkipList();
        String last = skipList.get(skipList.size() - 1);
        int initialSize = skipList.size();

        skipList.remove(last);

        assertEquals(initialSize - 1, skipList.size());
        assertFalse(skipList.contains(last));
    }

    @Test
    @DisplayName("Elements should be sorted at base level")
    void testSortedOrderOnBaseLevel() {
        String[] values = {"d", "b", "a", "c"};
        Arrays.stream(values).forEach(skipList::add);

        String[] actualOrder = IntStream.range(0, values.length).mapToObj(skipList::get).toArray(String[] ::new);

        org.junit.jupiter.api.Assertions.assertArrayEquals(new String[] {"a", "b", "c", "d"}, actualOrder);
    }

    @Test
    @DisplayName("Duplicate elements can be added and count correctly")
    void testAddDuplicates() {
        skipList.add("x");
        skipList.add("x");
        assertEquals(2, skipList.size());
        assertEquals("x", skipList.get(0));
        assertEquals("x", skipList.get(1));
    }

    @Test
    @DisplayName("Add multiple and remove all")
    void testClearViaRemovals() {
        String[] values = {"a", "b", "c"};
        Arrays.stream(values).forEach(skipList::add);

        for (String v : values) {
            skipList.remove(v);
        }

        assertEquals(0, skipList.size());
    }

    private SkipList<String> createSkipList() {
        SkipList<String> s = new SkipList<>();
        String[] values = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
        Arrays.stream(values).forEach(s::add);
        return s;
    }
}
