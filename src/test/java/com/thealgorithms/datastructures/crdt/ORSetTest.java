package com.thealgorithms.datastructures.crdt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import org.junit.jupiter.api.Test;

class ORSetTest {

    @Test
    void testContains() {
        ORSet<String> orSet = new ORSet<>();
        orSet.add("A");
        assertTrue(orSet.contains("A"));
    }

    @Test
    void testAdd() {
        ORSet<String> orSet = new ORSet<>();
        orSet.add("A");
        assertTrue(orSet.contains("A"));
    }

    @Test
    void testRemove() {
        ORSet<String> orSet = new ORSet<>();
        orSet.add("A");
        orSet.add("A");
        orSet.remove("A");
        assertFalse(orSet.contains("A"));
    }

    @Test
    void testElements() {
        ORSet<String> orSet = new ORSet<>();
        orSet.add("A");
        orSet.add("B");
        assertEquals(Set.of("A", "B"), orSet.elements());
    }

    @Test
    void testCompareEqualSets() {
        ORSet<String> orSet1 = new ORSet<>();
        ORSet<String> orSet2 = new ORSet<>();

        orSet1.add("A");
        orSet2.add("A");
        orSet2.add("B");
        orSet2.add("C");
        orSet2.remove("C");
        orSet1.merge(orSet2);
        orSet2.merge(orSet1);
        orSet2.remove("B");

        assertTrue(orSet1.compare(orSet2));
    }

    @Test
    void testCompareDifferentSets() {
        ORSet<String> orSet1 = new ORSet<>();
        ORSet<String> orSet2 = new ORSet<>();

        orSet1.add("A");
        orSet2.add("B");

        assertFalse(orSet1.compare(orSet2));
    }

    @Test
    void testMerge() {
        ORSet<String> orSet1 = new ORSet<>();
        ORSet<String> orSet2 = new ORSet<>();

        orSet1.add("A");
        orSet1.add("A");
        orSet1.add("B");
        orSet1.remove("B");
        orSet2.add("B");
        orSet2.add("C");
        orSet2.remove("C");
        orSet1.merge(orSet2);

        assertTrue(orSet1.contains("A"));
        assertTrue(orSet1.contains("B"));
    }
}
