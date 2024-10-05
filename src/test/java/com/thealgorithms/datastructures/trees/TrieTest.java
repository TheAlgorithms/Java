package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

public class TrieTest {
    private static final List<String> WORDS = List.of("Apple", "App", "app", "APPLE");

    @Test
    public void testEmptyTrie() {
        Trie trie = new Trie();
        assertEquals(0, trie.countWords());
    }

    @Test
    public void testInsertIntoTrie() {
        Trie trie = createTrie();
        assertEquals(WORDS.size(), trie.countWords());
    }

    @Test
    public void testSearchInTrie() {
        Trie trie = createTrie();
        assertTrue(trie.search("APPLE"));
        assertFalse(trie.search("apple"));
    }

    @Test
    public void testDeleteFromTrie() {
        Trie trie = createTrie();
        trie.delete("APPLE");
        assertFalse(trie.search("APPLE"));
        assertEquals(WORDS.size() - 1, trie.countWords());
    }

    @Test
    public void testCountWords() {
        Trie trie = createTrie();
        assertEquals(WORDS.size(), trie.countWords());
    }

    @Test
    public void testStartsWithPrefix() {
        Trie trie = createTrie();
        assertTrue(trie.startsWithPrefix("App"));
    }

    @Test
    public void testCountWordsWithPrefix() {
        Trie trie = createTrie();
        assertEquals(2, trie.countWordsWithPrefix("App"));
    }

    private Trie createTrie() {
        Trie trie = new Trie();
        WORDS.forEach(trie::insert);
        return trie;
    }
}
