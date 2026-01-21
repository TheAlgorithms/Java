package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test cases for TernarySearchTree implementation.
 * 
 * @author JeevanYewale
 */
class TernarySearchTreeTest {

    private TernarySearchTree tst;

    @BeforeEach
    void setUp() {
        tst = new TernarySearchTree();
    }

    @Test
    void testInsertAndSearch() {
        tst.insert("cat");
        tst.insert("cats");
        tst.insert("up");
        tst.insert("bug");

        assertTrue(tst.search("cat"));
        assertTrue(tst.search("cats"));
        assertTrue(tst.search("up"));
        assertTrue(tst.search("bug"));
        assertFalse(tst.search("ca"));
        assertFalse(tst.search("dog"));
    }

    @Test
    void testInsertNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> tst.insert(null));
        assertThrows(IllegalArgumentException.class, () -> tst.insert(""));
    }

    @Test
    void testSearchNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> tst.search(null));
        assertThrows(IllegalArgumentException.class, () -> tst.search(""));
    }

    @Test
    void testStartsWith() {
        tst.insert("cat");
        tst.insert("cats");
        tst.insert("car");
        tst.insert("card");

        assertTrue(tst.startsWith("ca"));
        assertTrue(tst.startsWith("cat"));
        assertTrue(tst.startsWith("car"));
        assertFalse(tst.startsWith("dog"));
        assertFalse(tst.startsWith("cb"));
    }

    @Test
    void testStartsWithNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> tst.startsWith(null));
        assertThrows(IllegalArgumentException.class, () -> tst.startsWith(""));
    }

    @Test
    void testDelete() {
        tst.insert("cat");
        tst.insert("cats");
        tst.insert("car");

        assertTrue(tst.delete("cat"));
        assertFalse(tst.search("cat"));
        assertTrue(tst.search("cats"));
        assertTrue(tst.search("car"));

        assertFalse(tst.delete("dog"));
        assertFalse(tst.delete("cat")); // Already deleted
    }

    @Test
    void testDeleteNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> tst.delete(null));
        assertThrows(IllegalArgumentException.class, () -> tst.delete(""));
    }

    @Test
    void testIsEmpty() {
        assertTrue(tst.isEmpty());
        
        tst.insert("test");
        assertFalse(tst.isEmpty());
        
        tst.delete("test");
        assertTrue(tst.isEmpty());
    }

    @Test
    void testSingleCharacterWords() {
        tst.insert("a");
        tst.insert("b");
        tst.insert("z");

        assertTrue(tst.search("a"));
        assertTrue(tst.search("b"));
        assertTrue(tst.search("z"));
        assertFalse(tst.search("c"));
    }

    @Test
    void testOverlappingWords() {
        tst.insert("he");
        tst.insert("she");
        tst.insert("his");
        tst.insert("hers");

        assertTrue(tst.search("he"));
        assertTrue(tst.search("she"));
        assertTrue(tst.search("his"));
        assertTrue(tst.search("hers"));
        
        assertTrue(tst.startsWith("he"));
        assertTrue(tst.startsWith("h"));
        assertFalse(tst.search("her"));
    }

    @Test
    void testCaseSensitivity() {
        tst.insert("Cat");
        tst.insert("cat");

        assertTrue(tst.search("Cat"));
        assertTrue(tst.search("cat"));
        assertFalse(tst.search("CAT"));
    }

    @Test
    void testLargeDataset() {
        String[] words = {"apple", "application", "apply", "appreciate", "approach", 
                         "appropriate", "approve", "approximate", "april", "area"};
        
        for (String word : words) {
            tst.insert(word);
        }
        
        for (String word : words) {
            assertTrue(tst.search(word));
        }
        
        assertTrue(tst.startsWith("app"));
        assertTrue(tst.startsWith("appr"));
        assertFalse(tst.startsWith("orange"));
    }
}