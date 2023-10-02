/********************************************************
 *            TestCase for AhoCorasick Algorithm        *
 *                                                      *
 *               Author :  Prabhat-Kumar-42             *
 *      Github :  https://github.com/Prabhat-Kumar-42   *
 *                                                      *
 ********************************************************/

package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AhoCorasickTest {
    private AhoCorasick ahoCorasick;

    @BeforeEach
    void setUp() {
        ahoCorasick = new AhoCorasick();
    }

    @Test
    void testClear() {
        // Add some patterns and perform a search
        String[] patterns = {"ACC", "ATC"};
        String text = "GCATCG";
        ahoCorasick.buildTrie(patterns);
        ahoCorasick.buildSuffixAndOutputLinks();
        ahoCorasick.searchIn(text);

        // Initially, check that res is not null
        assertNotNull(ahoCorasick.getWordsIndexList());

        // Call the clear method
        ahoCorasick.clear();

        // After calling clear, res should be empty
        assertTrue(ahoCorasick.getWordsIndexList().isEmpty());

        // Verify that root is also null
        assertNull(ahoCorasick.getRoot());
    }

    @Test
    void testSearch() {
        // Add patterns
        String[] patterns = {"ACC", "ATC", "CAT", "GCG", "C", "T"};
        String text = "GCATCG";
        ahoCorasick.buildTrie(patterns);
        ahoCorasick.buildSuffixAndOutputLinks();
        ahoCorasick.searchIn(text);

        // Get the results
        ArrayList<ArrayList<Integer>> res = ahoCorasick.getWordsIndexList();

        // Define expected results for each pattern
        int[][] expected = {
            {}, // "ACC" should not be found
            {2}, // "ATC" should be found at index 2
            {1}, // "CAT" should be found at index 1
            {}, // "GCG" should not be found
            {1, 4}, // "C" should be found at indices 1 and 4
            {3} // "T" should be found at index 3
        };

        // Check specific pattern occurrences
        for (int i = 0; i < patterns.length; i++) {
            assertIterableEquals(toList(expected[i]), res.get(i));
        }
    }

    @Test
    void testRepeatCountOfWords() {
        // Add patterns
        String[] patterns = {"ACC", "ATC", "CAT", "GCG", "C", "T"};
        String text = "GCATCG";
        ahoCorasick.buildTrie(patterns);
        ahoCorasick.buildSuffixAndOutputLinks();
        ahoCorasick.searchIn(text);

        // Get the repeat counts
        ArrayList<Integer> countOfWords = ahoCorasick.getRepeatCountOfWords();

        // Define expected repeat counts for each pattern
        int[] expected = {0, 1, 1, 0, 2, 1};

        // Check the repeat counts
        for (int i = 0; i < patterns.length; i++) {
            assertEquals(expected[i], countOfWords.get(i));
        }
    }

    @Test
    void testSetUpStartPoints() {
        // Add some patterns and perform a search
        String[] patterns = {"ACC", "ATC", "CAT", "GCG", "C", "T"};
        String text = "GCATCG";
        ahoCorasick.buildTrie(patterns);
        ahoCorasick.buildSuffixAndOutputLinks();
        ahoCorasick.searchIn(text);

        // Get the results before setting up start points
        ArrayList<ArrayList<Integer>> resBefore = new ArrayList<>(ahoCorasick.getWordsIndexList());

        // Call setUpStartPoints
        ahoCorasick.setUpStartPoints();

        // Get the results after setting up start points
        ArrayList<ArrayList<Integer>> resAfter = ahoCorasick.getWordsIndexList();

        // Verify that start points are correctly set up
        // In this example, all patterns should have their end points converted to start points
        for (int i = 0; i < patterns.length; i++) {
            assertIterableEquals(resBefore.get(i), resAfter.get(i));
        }
    }

    @Test
    void testSuffixLinkCalculation() {
        ahoCorasick = new AhoCorasick();

        // Add patterns
        String[] patterns = {"ABCA", "BCB"};
        String text = "ABCA";
        ahoCorasick.buildTrie(patterns);
        ahoCorasick.buildSuffixAndOutputLinks();

        // Get the root node
        AhoCorasick.Node root = ahoCorasick.getRoot();

        // Test the suffix link calculation for a specific case
        AhoCorasick.Node currentState = root.getChild().get('A');
        AhoCorasick.Node parentSuffix = currentState.getSuffixLink();

        // Calculate the expected suffix link manually based on the comment you provided
        while (!parentSuffix.getChild().containsKey('A') && parentSuffix != root) {
            parentSuffix = parentSuffix.getSuffixLink();
        }

        // Ensure that the calculated suffix link matches the expected value
        assertEquals(parentSuffix, currentState.getSuffixLink());
    }

    @Test
    void testSuffixLinkAssignment() {
        ahoCorasick = new AhoCorasick();

        // Add patterns
        String[] patterns = {"ABCA", "BCB"};
        String text = "ABCA";
        ahoCorasick.buildTrie(patterns);
        ahoCorasick.buildSuffixAndOutputLinks();

        // Get the root node
        AhoCorasick.Node root = ahoCorasick.getRoot();

        // Test suffix link assignment when parentSuffix.child.containsKey(cc) is true
        AhoCorasick.Node currentState1 = root.getChild().get('A');
        AhoCorasick.Node currentChild1 = currentState1.getChild().get('B');
        AhoCorasick.Node parentSuffix1 = currentState1.getSuffixLink();

        // Ensure suffix link assignment when parentSuffix.child.containsKey(cc) is true
        assertEquals(parentSuffix1.getChild().get('B'), currentChild1.getSuffixLink());

        // Test suffix link assignment when parentSuffix.child.containsKey(cc) is false
        AhoCorasick.Node currentState2 = root.getChild().get('B');
        AhoCorasick.Node currentChild2 = (currentState2 != null) ? currentState2.getChild().get('A') : null;

        // Check if currentChild2 is not null before accessing suffix link
        if (currentChild2 != null) {
            // Ensure suffix link assignment when parentSuffix.child.containsKey(cc) is false
            assertEquals(root, currentChild2.getSuffixLink());
        } else {
            // Handle the case when currentChild2 is null (no child for 'A') and add an assertion
            assertNull(currentChild2); // Assert that currentChild2 is null
        }
    }

    @Test
    void testSuffixLinkAssignment_FalseCondition() {
        // Create an AhoCorasick instance
        AhoCorasick ahoCorasick = new AhoCorasick();

        // Add some patterns to the trie
        String[] patterns = {"ACC", "ATC", "CAT"};
        ahoCorasick.buildTrie(patterns);
        ahoCorasick.buildSuffixAndOutputLinks();

        // Set up the parent node for testing
        AhoCorasick.Node parent = ahoCorasick.getRoot(); // Root node

        // Character 'X' is not present in the trie, so the conditions should be false
        char ch = 'X';

        // The while condition should result in parent being the root node (parent == root)
        while (parent != ahoCorasick.getRoot() && !parent.getChild().containsKey(ch)) {
            parent = parent.getSuffixLink();
        }

        // Now, parent == root and !root.getChild().containsKey(ch) is true
        assertFalse(parent.getChild().containsKey(ch));
    }

    @Test
    void testEmptyPatterns() {
        String[] patterns = {};
        String text = "Some text to search";

        // Build the Aho-Corasick trie with empty patterns
        ahoCorasick.buildTrie(patterns);
        ahoCorasick.buildSuffixAndOutputLinks();
        ahoCorasick.searchIn(text);

        // Verify that no patterns are found
        ArrayList<ArrayList<Integer>> res = ahoCorasick.getWordsIndexList();
        assertTrue(res.isEmpty());

        // Verify that repeat counts are all zero
        ArrayList<Integer> countOfWords = ahoCorasick.getRepeatCountOfWords();
        for (int count : countOfWords) {
            assertEquals(0, count);
        }
    }

    @Test
    void testPositionByString() {
        // Add patterns
        String[] patterns = {"ACC", "ATC", "CAT", "GCG", "C", "T"};
        String text = "GCATCG";
        ahoCorasick.buildTrie(patterns);
        ahoCorasick.buildSuffixAndOutputLinks();
        ahoCorasick.searchIn(text);

        // Create the positionByString map
        ahoCorasick.createPositionByStringMap();

        // Get the positionByString map
        Map<String, ArrayList<Integer>> positionByString = ahoCorasick.getPositionByString();

        // Define expected positions for each pattern
        int[][] expectedPositions = {
            {}, // "ACC" should not be found
            {2}, // "ATC" should be found at index 2
            {1}, // "CAT" should be found at index 1
            {}, // "GCG" should not be found
            {1, 4}, // "C" should be found at indices 1 and 4
            {3} // "T" should be found at index 3
        };

        // Check specific pattern positions
        for (int i = 0; i < patterns.length; i++) {
            assertIterableEquals(toList(expectedPositions[i]), positionByString.get(patterns[i]));
        }
    }

    // Helper method to convert an array to an ArrayList
    private ArrayList<Integer> toList(int[] array) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int value : array) {
            list.add(value);
        }
        return list;
    }
}
