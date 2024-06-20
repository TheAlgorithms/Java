package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class WordLadderTest {

    /**
     * Test 1:
     * Input: beginWord = "hit", endWord = "cog", wordList =
     * ["hot","dot","dog","lot","log","cog"]
     * Output: 5
     * Explanation: One shortest transformation sequence is
     * "hit" -> "hot" -> "dot" -> "dog" -> cog"
     * which is 5 words long.
     */
    @Test
    public void testWordLadder() {

        List<String> wordList1 = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        assertEquals(WordLadder.ladderLength("hit", "cog", wordList1), 5);
    }

    /**
     * Test 2:
     * Input: beginWord = "hit", endWord = "cog", wordList =
     * ["hot","dot","dog","lot","log"]
     * Output: 0
     * Explanation: The endWord "cog" is not in wordList,
     * therefore there is no valid transformation sequence.
     */
    @Test
    public void testWordLadder2() {

        List<String> wordList2 = Arrays.asList("hot", "dot", "dog", "lot", "log");
        assertEquals(WordLadder.ladderLength("hit", "cog", wordList2), 0);
    }
}
