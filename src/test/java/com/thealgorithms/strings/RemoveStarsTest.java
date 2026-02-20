package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RemoveStarsTest {

    @Test
    void testExampleCase() {
        assertEquals("lecoe", RemoveStars.removeStars("leet**cod*e"));
    }

    @Test
    void testAllStars() {
        assertEquals("", RemoveStars.removeStars("abc***"));
    }

    @Test
    void testNoStars() {
        assertEquals("hello", RemoveStars.removeStars("hello"));
    }

    @Test
    void testSingleCharacter() {
        assertEquals("", RemoveStars.removeStars("a*"));
    }
}
