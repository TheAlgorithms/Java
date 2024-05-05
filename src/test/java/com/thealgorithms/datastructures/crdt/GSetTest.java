package com.thealgorithms.datastructures.crdt;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class GSetTest {

    @Test
    void testAddElement() {
        GSet<String> gSet = new GSet<>();
        gSet.addElement("apple");
        gSet.addElement("orange");

        assertTrue(gSet.lookup("apple"));
        assertTrue(gSet.lookup("orange"));
        assertFalse(gSet.lookup("banana"));
    }

    @Test
    void testLookup() {
        GSet<Integer> gSet = new GSet<>();
        gSet.addElement(1);
        gSet.addElement(2);

        assertTrue(gSet.lookup(1));
        assertTrue(gSet.lookup(2));
        assertFalse(gSet.lookup(3));
    }

    @Test
    void testCompare() {
        GSet<String> gSet1 = new GSet<>();
        GSet<String> gSet2 = new GSet<>();
        gSet1.addElement("apple");
        gSet1.addElement("orange");
        gSet2.addElement("orange");
        assertFalse(gSet1.compare(gSet2));
        gSet2.addElement("apple");
        assertTrue(gSet1.compare(gSet2));
        gSet2.addElement("banana");
        assertTrue(gSet1.compare(gSet2));
    }

    @Test
    void testMerge() {
        GSet<String> gSet1 = new GSet<>();
        GSet<String> gSet2 = new GSet<>();

        gSet1.addElement("apple");
        gSet1.addElement("orange");

        gSet2.addElement("orange");
        gSet2.addElement("banana");

        GSet<String> mergedSet = new GSet<>();
        mergedSet.merge(gSet1);
        mergedSet.merge(gSet2);

        assertTrue(mergedSet.lookup("apple"));
        assertTrue(mergedSet.lookup("orange"));
        assertTrue(mergedSet.lookup("banana"));
        assertFalse(mergedSet.lookup("grape"));
    }
}
