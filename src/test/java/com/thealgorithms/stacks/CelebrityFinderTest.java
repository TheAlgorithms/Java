package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CelebrityFinderTest {

    @Test
    public void testCelebrityExists() {
        int[][] party = {{0, 1, 1}, {0, 0, 1}, {0, 0, 0}};
        assertEquals(2, CelebrityFinder.findCelebrity(party));
    }

    @Test
    public void testNoCelebrity() {
        int[][] party = {{0, 1, 0}, {1, 0, 1}, {1, 1, 0}};
        assertEquals(-1, CelebrityFinder.findCelebrity(party));
    }
}
