package com.thealgorithms.datastructures.crdt;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LWWElementSetTest {

    private LWWElementSet set;
    private final Bias bias = Bias.ADDS;

    @BeforeEach
    void setUp() {
        set = new LWWElementSet();
    }

    @Test
    void testAdd() {
        Element element = new Element("key1", 1, bias);
        set.add(element);

        assertTrue(set.lookup(element));
    }

    @Test
    void testRemove() {
        Element element = new Element("key1", 1, bias);
        set.add(element);
        set.remove(element);

        assertFalse(set.lookup(element));
    }

    @Test
    void testRemoveNonexistentElement() {
        Element element = new Element("key1", 1, bias);
        set.remove(element);

        assertFalse(set.lookup(element));
    }

    @Test
    void testLookupNonexistentElement() {
        Element element = new Element("key1", 1, bias);

        assertFalse(set.lookup(element));
    }

    @Test
    void testCompareEqualSets() {
        LWWElementSet otherSet = new LWWElementSet();

        Element element = new Element("key1", 1, bias);
        set.add(element);
        otherSet.add(element);

        assertTrue(set.compare(otherSet));

        otherSet.add(new Element("key2", 2, bias));
        assertTrue(set.compare(otherSet));
    }

    @Test
    void testCompareDifferentSets() {
        LWWElementSet otherSet = new LWWElementSet();

        Element element1 = new Element("key1", 1, bias);
        Element element2 = new Element("key2", 2, bias);

        set.add(element1);
        otherSet.add(element2);

        assertFalse(set.compare(otherSet));
    }

    @Test
    void testMerge() {
        LWWElementSet otherSet = new LWWElementSet();

        Element element1 = new Element("key1", 1, bias);
        Element element2 = new Element("key2", 2, bias);

        set.add(element1);
        otherSet.add(element2);

        set.merge(otherSet);

        assertTrue(set.lookup(element1));
        assertTrue(set.lookup(element2));
    }

    @Test
    void testCompareTimestampsEqualTimestamps() {
        LWWElementSet lwwElementSet = new LWWElementSet();

        Element e1 = new Element("key1", 10, Bias.REMOVALS);
        Element e2 = new Element("key1", 10, Bias.REMOVALS);

        assertTrue(lwwElementSet.compareTimestamps(e1, e2));

        e1 = new Element("key1", 10, Bias.ADDS);
        e2 = new Element("key1", 10, Bias.ADDS);

        assertFalse(lwwElementSet.compareTimestamps(e1, e2));
    }
}
