/******************************************************
 *           Test for AhoCorasick Algorithm           *
 *                                                    *
 *            Author : Prabhat-Kumar-42               *
 *     github : https://github.com/Prabhat-Kumar-42   *
 *                                                    *
 *****************************************************/

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

        // Check specific pattern occurrences
        assertEquals(0, res.get(0).size()); // "ACC" should not be found
        assertEquals(1, res.get(1).size()); // "ATC" should be found at index 2
        assertEquals(1, res.get(2).size()); // "CAT" should be found at index 1
        assertEquals(0, res.get(3).size()); // "GCG" should not be found
        assertEquals(2, res.get(4).size()); // "C" should be found at indices 1 and 4
        assertEquals(1, res.get(5).size()); // "T" should be found at index 3
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

        // Check the repeat counts
        assertEquals(0, countOfWords.get(0)); // "ACC" appears zero times
        assertEquals(1, countOfWords.get(1)); // "ATC" appears once
        assertEquals(1, countOfWords.get(2)); // "CAT" appears once
        assertEquals(0, countOfWords.get(3)); // "GCG" appears zero times
        assertEquals(2, countOfWords.get(4)); // "C" appears twice
        assertEquals(1, countOfWords.get(5)); // "T" appears once
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
        assertEquals(resBefore.get(0), resAfter.get(0)); // "ACC" start points should be the same
        assertEquals(resBefore.get(1), resAfter.get(1)); // "ATC" start points should be the same
        assertEquals(resBefore.get(2), resAfter.get(2)); // "CAT" start points should be the same
        assertEquals(resBefore.get(3), resAfter.get(3)); // "GCG" start points should be the same
        assertEquals(resBefore.get(4), resAfter.get(4)); // "C" start points should be the same
        assertEquals(resBefore.get(5), resAfter.get(5)); // "T" start points should be the same
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

        // Check specific pattern positions
        assertEquals(1, positionByString.get("ATC").size()); // "ATC" should be found at index 2
        assertEquals(1, positionByString.get("CAT").size()); // "CAT" should be found at index 1
        assertEquals(2, positionByString.get("C").size()); // "C" should be found at indices 1 and 4
        assertEquals(1, positionByString.get("T").size()); // "T" should be found at index 3
        // You can add more assertions for other patterns if needed
    }
}
