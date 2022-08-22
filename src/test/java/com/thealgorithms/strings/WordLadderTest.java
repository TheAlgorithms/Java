package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordLadderTest {
    @Test
    void setNotContainEndWord() {
        WordLadder wordLadder = new WordLadder();
        assertEquals(0, wordLadder.ladderLength("hello", "funix", List.of("hello", "world", "welcome", "to", "FPT")));
    }

    @Test
    void setContainEndWord() {
        WordLadder wordLadder = new WordLadder();
        assertEquals(0, wordLadder.ladderLength("hello", "funix", List.of("hello", "world", "welcome", "to", "funix")));
    }

    @Test
    void setContainEndWord2() {
        WordLadder wordLadder = new WordLadder();
        assertEquals(2, wordLadder.ladderLength("a", "e", List.of("a", "b", "c", "d", "e")));
    }

    //hello world
}
