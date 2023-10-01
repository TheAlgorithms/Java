// Test File for AhoCorasick

// Author: Prabhat-Kumar-42
// GitHub: https://github.com/Prabhat-Kumar-42

package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AhoCorasickTest {
    private AhoCorasick ahoCorasick;
    private ArrayList<ArrayList<Integer>> res; // Store the search results

    @BeforeEach
    void setUp() {
        ahoCorasick = new AhoCorasick();

        String[] patterns = {"ACC", "ATC", "CAT", "GCG", "C", "T"};
        String text = "GCATCG";
        // building Trie by using to pattern array
        ahoCorasick.buildTrie(patterns);
        // building Suffix links and output links
        ahoCorasick.buildSuffixAndOutputLinks();
        // Processing the text in Trie
        ahoCorasick.searchIn(text);
        // Populate the res ArrayList only once in setUp
        res = ahoCorasick.getWordsIndexList();
    }

    @Test
    void resSize() {
        // list size check for the list containing the start index of words from
        // from the dictonary/pattern array.
        assertEquals(6, res.size());
    }

    @Test
    void testSearch() {
        // Note - Indexing is zero based

        // Test that the first pattern "ACC" is not found in the text
        assertEquals(0, res.get(0).size());

        // Test that the second pattern "ATC" is found at index 2
        assertEquals(2, res.get(1).get(0));

        // Test that the third pattern "CAT" is found at index 1
        assertEquals(1, res.get(2).get(0));

        // Test that the fourth pattern "GCG" is not found in the text
        assertEquals(0, res.get(3).size());

        // Test that the fifth pattern "C" is found at indices 1 and 4
        assertEquals(2, res.get(4).size());
        assertEquals(1, res.get(4).get(0));
        assertEquals(4, res.get(4).get(1));

        // Test that the sixth pattern "T" is found at index 3
        assertEquals(3, res.get(5).get(0));
    }

    @Test
    void testRepeatCountOfWords() {
        ArrayList<Integer> countOfWords = ahoCorasick.getRepeatCountOfWords();

        // Test that the counts match the expected values
        assertEquals(0, countOfWords.get(0)); // "ACC" appears zero times
        assertEquals(1, countOfWords.get(1)); // "ATC" appears once
        assertEquals(1, countOfWords.get(2)); // "CAT" appears once
        assertEquals(0, countOfWords.get(3)); // "GCG" does not appear
        assertEquals(2, countOfWords.get(4)); // "C" appears twice
        assertEquals(1, countOfWords.get(5)); // "T" appears once
    }
}
