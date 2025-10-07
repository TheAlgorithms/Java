package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class RemoveStarsTest {

    @Test
    void testExample() {
        assertEquals("lecoe", RemoveStars.removeStars("leet**cod*e"));
    }

    @Test
    void testMultipleStars() {
        assertEquals("c", RemoveStars.removeStars("ab*c*d**c"));
    }

    @Test
    void testEmptyInput() {
        assertEquals("", RemoveStars.removeStars(""));
    }

    @Test
    void testNullInput() {
        assertNull(RemoveStars.removeStars(null));
    }

    @Test
    void testNoStars() {
        assertEquals("hello", RemoveStars.removeStars("hello"));
    }
}
