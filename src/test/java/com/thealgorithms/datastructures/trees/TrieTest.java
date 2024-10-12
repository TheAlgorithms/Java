package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrieTest {
    private static final List<String> WORDS = List.of("Apple", "App", "app", "APPLE");

    private Trie trie;

    @BeforeEach
    public void setUp() {
        trie = new Trie();
    }

    @Test
    public void testInsertAndSearchBasic() {
        String word = "hello";
        trie.insert(word);
        assertTrue(trie.search(word), "Search should return true for an inserted word.");
    }

    @Test
    public void testSearchNonExistentWord() {
        String word = "world";
        assertFalse(trie.search(word), "Search should return false for a non-existent word.");
    }

    @Test
    public void testInsertAndSearchMultipleWords() {
        String word1 = "cat";
        String word2 = "car";
        trie.insert(word1);
        trie.insert(word2);

        assertTrue(trie.search(word1), "Search should return true for an inserted word.");
        assertTrue(trie.search(word2), "Search should return true for another inserted word.");
        assertFalse(trie.search("dog"), "Search should return false for a word not in the Trie.");
    }

    @Test
    public void testDeleteExistingWord() {
        String word = "remove";
        trie.insert(word);
        assertTrue(trie.delete(word), "Delete should return true for an existing word.");
        assertFalse(trie.search(word), "Search should return false after deletion.");
    }

    @Test
    public void testDeleteNonExistentWord() {
        String word = "nonexistent";
        assertFalse(trie.delete(word), "Delete should return false for a non-existent word.");
    }

    @Test
    public void testInsertAndSearchPrefix() {
        String prefix = "pre";
        String word = "prefix";
        trie.insert(prefix);
        trie.insert(word);

        assertTrue(trie.search(prefix), "Search should return true for an inserted prefix.");
        assertTrue(trie.search(word), "Search should return true for a word with the prefix.");
        assertFalse(trie.search("pref"), "Search should return false for a prefix that is not a full word.");
    }

    @Test
    public void testCountWords() {
        Trie trie = createTrie();
        assertEquals(WORDS.size(), trie.countWords(), "Count words should return the correct number of words.");
    }

    @Test
    public void testStartsWithPrefix() {
        Trie trie = createTrie();
        assertTrue(trie.startsWithPrefix("App"), "Starts with prefix should return true.");
    }

    @Test
    public void testCountWordsWithPrefix() {
        Trie trie = createTrie();
        assertEquals(2, trie.countWordsWithPrefix("App"), "Count words with prefix should return 2.");
    }

    private Trie createTrie() {
        Trie trie = new Trie();
        WORDS.forEach(trie::insert);
        return trie;
    }
}
