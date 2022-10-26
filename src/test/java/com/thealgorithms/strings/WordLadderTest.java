package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class WordLadderTest {

    @Test
    public void testWordLadder() {
        String words1[] = { "hot", "dot", "dog", "lot", "log", "cog" };
        assertEquals(5, WordLadder.ladderLength("hit", "cog", Arrays.asList(words1)));
        String words2[] = { "hot", "dot", "dog", "lot", "log" };
        assertEquals(0, WordLadder.ladderLength("hit", "cog", Arrays.asList(words2)));
     }
}
