package main.java.com.thealgorithms.datastructures.trees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for TernarySearchTree.
 */
class TernarySearchTreeTest {

    @Test
    void testInsertAndSearchWords() {
        TernarySearchTree tst = new TernarySearchTree();

        tst.insert("cat");
        tst.insert("cap");
        tst.insert("bat");

        assertTrue(tst.search("cat"));
        assertTrue(tst.search("cap"));
        assertTrue(tst.search("bat"));

        assertFalse(tst.search("can"));
        assertFalse(tst.search("dog"));
    }

    @Test
    void testSingleCharacterWord() {
        TernarySearchTree tst = new TernarySearchTree();

        tst.insert("a");
        tst.insert("b");

        assertTrue(tst.search("a"));
        assertTrue(tst.search("b"));
        assertFalse(tst.search("c"));
    }

    @Test
    void testPrefixNotWholeWord() {
        TernarySearchTree tst = new TernarySearchTree();

        tst.insert("hello");

        assertFalse(tst.search("he"));     // prefix only
        assertFalse(tst.search("hell"));   // prefix only
        assertTrue(tst.search("hello"));   // complete word
    }

    @Test
    void testMultipleInsertionsOfSameWord() {
        TernarySearchTree tst = new TernarySearchTree();

        tst.insert("java");
        tst.insert("java");
        tst.insert("java");

        assertTrue(tst.search("java"));
        assertFalse(tst.search("javac"));
    }
}
