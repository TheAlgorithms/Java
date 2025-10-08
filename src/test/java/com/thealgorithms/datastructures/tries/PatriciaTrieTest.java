package com.thealgorithms.datastructures.tries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PatriciaTrieTest {

    @Test
    void insertAndGet_basic() {
        var t = new PatriciaTrie<Integer>();
        t.put("ant", 1);
        t.put("ante", 2);
        t.put("anti", 3);
        assertEquals(3, t.size());
        assertEquals(1, t.get("ant"));
        assertEquals(2, t.get("ante"));
        assertEquals(3, t.get("anti"));
        assertNull(t.get("aunt"));
    }

    @Test
    void overwriteValue_doesNotChangeSize() {
        var t = new PatriciaTrie<String>();
        t.put("car", "A");
        assertEquals(1, t.size());
        t.put("car", "B");
        assertEquals(1, t.size());
        assertEquals("B", t.get("car"));
    }

    @Test
    void startsWith_variousPrefixes() {
        var t = new PatriciaTrie<Integer>();
        t.put("an", 1); t.put("ant", 2); t.put("anthem", 3); t.put("banana", 4);
        assertTrue(t.startsWith("an"));
        assertTrue(t.startsWith("ant"));
        assertTrue(t.startsWith("anthem"));
        assertFalse(t.startsWith("ante"));
        assertTrue(t.startsWith("b"));
        assertFalse(t.startsWith("c"));
        assertTrue(t.startsWith("")); // non-empty trie => true
    }

    @Test
    void unicodeKeys_supported() {
        var t = new PatriciaTrie<String>();
        t.put("mañana", "sun");
        t.put("манго", "mango-cyrillic");
        assertTrue(t.contains("mañana"));
        assertTrue(t.contains("манго"));
        assertEquals("sun", t.get("mañana"));
        assertTrue(t.startsWith("ма")); // prefix in Cyrillic
    }

    @Test
    void remove_leafKey() {
        var t = new PatriciaTrie<Integer>();
        t.put("cat", 1);
        t.put("car", 2);
        assertTrue(t.remove("car"));
        assertFalse(t.contains("car"));
        assertTrue(t.contains("cat"));
        assertEquals(1, t.size());
    }

    @Test
    void remove_internalCausesMerge() {
        var t = new PatriciaTrie<Integer>();
        t.put("card", 1);
        t.put("care", 2);
        t.put("car", 3);
        // remove "car" which sits on the path to "card" and "care"
        assertTrue(t.remove("car"));
        assertFalse(t.contains("car"));
        assertTrue(t.contains("card"));
        assertTrue(t.contains("care"));
        // structure should remain accessible after merge
        assertEquals(2, t.size());
        assertEquals(1, t.get("card"));
        assertEquals(2, t.get("care"));
    }

    @Test
    void remove_absentKey_noop() {
        var t = new PatriciaTrie<Integer>();
        t.put("alpha", 1);
        assertFalse(t.remove("alphabet"));
        assertEquals(1, t.size());
        assertTrue(t.contains("alpha"));
    }

    @Test
    void emptyKey_supported() {
        var t = new PatriciaTrie<String>();
        t.put("", "root");
        assertTrue(t.contains(""));
        assertEquals("root", t.get(""));
        assertTrue(t.remove(""));
        assertFalse(t.contains(""));
        assertEquals(0, t.size());
    }

    @Test
    void nullContracts() {
        var t = new PatriciaTrie<Integer>();
        assertThrows(IllegalArgumentException.class, () -> t.put(null, 1));
        assertThrows(IllegalArgumentException.class, () -> t.put("a", null));
        assertThrows(IllegalArgumentException.class, () -> t.get(null));
        assertThrows(IllegalArgumentException.class, () -> t.contains(null));
        assertThrows(IllegalArgumentException.class, () -> t.remove(null));
        assertThrows(IllegalArgumentException.class, () -> t.startsWith(null));
    }

    @Test
    void isEmptyAndSize() {
        var t = new PatriciaTrie<Integer>();
        assertTrue(t.isEmpty());
        t.put("x", 10);
        assertFalse(t.isEmpty());
        assertEquals(1, t.size());
        t.remove("x");
        assertTrue(t.isEmpty());
        assertEquals(0, t.size());
    }
}
