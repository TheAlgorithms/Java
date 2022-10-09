package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordLadderTest {
    @Test
    public void ladderLength() {
        String beginWord = "hit";
        String endWord = "cog";

        assertEquals(5,
                WordLadder.ladderLength(beginWord, endWord, List.of("hot","dot","dog","lot","log","cog")));

        assertEquals(0,
                WordLadder.ladderLength(beginWord, endWord, List.of("hot","dot","dog","lot","log","bug")));

    }
}
