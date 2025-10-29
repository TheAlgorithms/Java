package com.thealgorithms.datastructures.tries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PatriciaTrieTest {

    @Test
    void insert_splitCausesNewBranch_incrementsSize() {
        var t = new PatriciaTrie<Integer>();
        t.put("romane", 1);
        assertEquals(1, t.size());

        // Split "romane" at "roman", create branch "us"
        t.put("romanus", 2);

        assertEquals(2, t.size(), "Size should increment when a split creates a new key branch");
        assertTrue(t.contains("romane"));
        assertTrue(t.contains("romanus"));
        assertEquals(1, t.get("romane"));
        assertEquals(2, t.get("romanus"));
    }

    @Test
    void basicPutGetContainsAndRemove() {
        var t = new PatriciaTrie<String>();
        assertTrue(t.isEmpty());

        t.put("", "root"); // empty key
        t.put("a", "x");
        t.put("ab", "y");
        t.put("abc", "z");

        assertEquals(4, t.size());
        assertEquals("root", t.get(""));
        assertEquals("x", t.get("a"));
        assertEquals("y", t.get("ab"));
        assertEquals("z", t.get("abc"));

        assertTrue(t.contains("ab"));
        assertFalse(t.contains("abcd"));

        assertTrue(t.startsWith("ab"));
        assertTrue(t.startsWith("abc"));
        assertFalse(t.startsWith("zzz"));

        assertTrue(t.remove("ab"));
        assertFalse(t.contains("ab"));
        assertEquals(3, t.size());

        // removing non-existent
        assertFalse(t.remove("ab"));
        assertEquals(3, t.size());
    }

    @Test
    void updatesDoNotIncreaseSize() {
        var t = new PatriciaTrie<Integer>();
        t.put("apple", 1);
        t.put("apple", 2);
        assertEquals(1, t.size());
        assertEquals(2, t.get("apple"));
    }

    @Test
    void nullContracts() {
        var t = new PatriciaTrie<Integer>();
        assertThrows(IllegalArgumentException.class, () -> t.put(null, 1));
        assertThrows(IllegalArgumentException.class, () -> t.put("x", null));
        assertThrows(IllegalArgumentException.class, () -> t.get(null));
        assertThrows(IllegalArgumentException.class, () -> t.contains(null));
        assertThrows(IllegalArgumentException.class, () -> t.remove(null));
        assertThrows(IllegalArgumentException.class, () -> t.startsWith(null));
    }
}
