package com.thealgorithms.datastructures.lists;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class SkipListTest {

    @Test
    void add() {
        SkipList<String> skipList = new SkipList<>();
        assertEquals(0, skipList.size());

        skipList.add("value");

        print(skipList);
        assertEquals(1, skipList.size());
    }

    @Test
    void get() {
        SkipList<String> skipList = new SkipList<>();
        skipList.add("value");

        String actualValue = skipList.get(0);

        print(skipList);
        assertEquals("value", actualValue);
    }

    @Test
    void contains() {
        SkipList<String> skipList = createSkipList();
        print(skipList);

        boolean contains = skipList.contains("b");

        assertTrue(contains);
    }

    @Test
    void remove() {
        SkipList<String> skipList = createSkipList();
        int initialSize = skipList.size();
        print(skipList);

        skipList.remove("a");

        print(skipList);
        assertEquals(initialSize - 1, skipList.size());
    }

    @Test
    void checkSortedOnLowestLayer() {
        SkipList<String> skipList = new SkipList<>();
        String[] values = {"d", "b", "a", "c"};
        Arrays.stream(values).forEach(skipList::add);
        print(skipList);

        String[] actualOrder = IntStream.range(0, values.length)
                .mapToObj(skipList::get)
                .toArray(String[]::new);

        assertArrayEquals(new String[]{"a", "b", "c", "d"}, actualOrder);
    }

    private SkipList<String> createSkipList() {
        SkipList<String> skipList = new SkipList<>();
        String[] values = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
        Arrays.stream(values).forEach(skipList::add);
        return skipList;
    }

    /**
     * Print Skip List representation to console.
     * Optional method not involved in testing process. Used only for visualisation purposes.
     * @param skipList to print
     */
    private void print(SkipList<?> skipList) {
        System.out.println(skipList);
    }
}
