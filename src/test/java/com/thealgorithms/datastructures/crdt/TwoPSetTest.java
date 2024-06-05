package com.thealgorithms.datastructures.crdt;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TwoPSetTest {

    private TwoPSet<String> set;

    @BeforeEach
    void setUp() {
        set = new TwoPSet<>();
    }

    @Test
    void testLookup() {
        set.add("A");
        assertTrue(set.lookup("A"));
        assertFalse(set.lookup("B"));
        set.remove("A");
        assertFalse(set.lookup("A"));
    }

    @Test
    void testAdd() {
        set.add("A");
        assertTrue(set.lookup("A"));
    }

    @Test
    void testRemove() {
        set.add("A");
        set.remove("A");
        assertFalse(set.lookup("A"));
    }

    @Test
    void testCompare() {
        TwoPSet<String> set1 = new TwoPSet<>();
        set1.add("A");
        set1.add("B");
        TwoPSet<String> set2 = new TwoPSet<>();
        set2.add("A");
        assertFalse(set1.compare(set2));
        set2.add("B");
        assertTrue(set1.compare(set2));
        set1.remove("A");
        assertFalse(set1.compare(set2));
        set2.remove("A");
        assertTrue(set1.compare(set2));
    }

    @Test
    void testMerge() {
        TwoPSet<String> set1 = new TwoPSet<>();
        set1.add("A");
        set1.add("B");
        TwoPSet<String> set2 = new TwoPSet<>();
        set2.add("B");
        set2.add("C");
        TwoPSet<String> mergedSet = set1.merge(set2);
        assertTrue(mergedSet.lookup("A"));
        assertTrue(mergedSet.lookup("B"));
        assertTrue(mergedSet.lookup("C"));
    }
}
