package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

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
    void removeFromHead() {
        SkipList<String> skipList = createSkipList();
        String mostLeftElement = skipList.get(0);
        int initialSize = skipList.size();
        print(skipList);

        skipList.remove(mostLeftElement);

        print(skipList);
        assertEquals(initialSize - 1, skipList.size());
    }

    @Test
    void removeFromTail() {
        SkipList<String> skipList = createSkipList();
        String mostRightValue = skipList.get(skipList.size() - 1);
        int initialSize = skipList.size();
        print(skipList);

        skipList.remove(mostRightValue);

        print(skipList);
        assertEquals(initialSize - 1, skipList.size());
    }

    @Test
    void checkSortedOnLowestLayer() {
        SkipList<String> skipList = new SkipList<>();
        String[] values = { "d", "b", "a", "c" };
        Arrays.stream(values).forEach(skipList::add);
        print(skipList);

        String[] actualOrder = IntStream
            .range(0, values.length)
            .mapToObj(skipList::get)
            .toArray(String[]::new);

        assertArrayEquals(new String[] { "a", "b", "c", "d" }, actualOrder);
    }

    private SkipList<String> createSkipList() {
        SkipList<String> skipList = new SkipList<>();
        String[] values = {
            "a",
            "b",
            "c",
            "d",
            "e",
            "f",
            "g",
            "h",
            "i",
            "j",
            "k",
        };
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
