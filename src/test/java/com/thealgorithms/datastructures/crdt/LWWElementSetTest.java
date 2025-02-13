package com.thealgorithms.datastructures.crdt;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import org.junit.jupiter.api.Test;

class LWWElementSetTest {

    @Test
    void testAddElement() {
        LWWElementSet<String> set = new LWWElementSet<>();
        set.add("A");
        assertTrue(set.lookup("A"));
    }

    @Test
    void testRemoveElement() {
        LWWElementSet<String> set = new LWWElementSet<>();
        set.add("A");
        set.remove("A");
        assertFalse(set.lookup("A"));
    }

    @Test
    void testLookupWithoutAdding() {
        LWWElementSet<String> set = new LWWElementSet<>();
        assertFalse(set.lookup("A"));
    }

    @Test
    void testLookupLaterTimestampsFalse() {
        LWWElementSet<String> set = new LWWElementSet<>();

        set.addSet.put("A", new Element<>("A", Instant.now()));
        set.removeSet.put("A", new Element<>("A", Instant.now().plusSeconds(10)));

        assertFalse(set.lookup("A"));
    }

    @Test
    void testLookupEarlierTimestampsTrue() {
        LWWElementSet<String> set = new LWWElementSet<>();

        set.addSet.put("A", new Element<>("A", Instant.now()));
        set.removeSet.put("A", new Element<>("A", Instant.now().minusSeconds(10)));

        assertTrue(set.lookup("A"));
    }

    @Test
    void testLookupWithConcurrentTimestamps() {
        LWWElementSet<String> set = new LWWElementSet<>();
        Instant now = Instant.now();
        set.addSet.put("A", new Element<>("A", now));
        set.removeSet.put("A", new Element<>("A", now));
        assertFalse(set.lookup("A"));
    }

    @Test
    void testMergeTwoSets() {
        LWWElementSet<String> set1 = new LWWElementSet<>();
        LWWElementSet<String> set2 = new LWWElementSet<>();

        set1.add("A");
        set2.add("B");
        set2.remove("A");

        set1.merge(set2);

        assertFalse(set1.lookup("A"));
        assertTrue(set1.lookup("B"));
    }

    @Test
    void testMergeWithConflictingTimestamps() {
        LWWElementSet<String> set1 = new LWWElementSet<>();
        LWWElementSet<String> set2 = new LWWElementSet<>();

        Instant now = Instant.now();
        set1.addSet.put("A", new Element<>("A", now.minusSeconds(10)));
        set2.addSet.put("A", new Element<>("A", now));

        set1.merge(set2);

        assertTrue(set1.lookup("A"));
    }

    @Test
    void testRemoveOlderThanAdd() {
        LWWElementSet<String> set = new LWWElementSet<>();
        Instant now = Instant.now();
        set.addSet.put("A", new Element<>("A", now));
        set.removeSet.put("A", new Element<>("A", now.minusSeconds(10)));
        assertTrue(set.lookup("A"));
    }
}
