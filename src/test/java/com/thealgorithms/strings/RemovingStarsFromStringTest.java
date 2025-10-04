package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class RemovingStarsFromStringTest {

    @Test
    void testEmptyString() {
        assertEquals("", RemovingStarsFromString.removeStars(""));
    }

    @Test
    void testNullString() {
        assertEquals(null, RemovingStarsFromString.removeStars(null));
    }

    @Test
    void testStringWithNoStars() {
        assertEquals("leetcode", RemovingStarsFromString.removeStars("leetcode"));
    }

    @Test
    void testBasicExample() {
        assertEquals("lecoe", RemovingStarsFromString.removeStars("leet**cod*e"));
    }

    @Test
    void testAllStars() {
        assertEquals("", RemovingStarsFromString.removeStars("abc***"));
    }

    @Test
    void testSingleCharacterWithStar() {
        assertEquals("", RemovingStarsFromString.removeStars("a*"));
    }

    @Test
    void testMultipleConsecutiveStars() {
        assertEquals("ac", RemovingStarsFromString.removeStars("abc**d*"));
    }

    @Test
    void testStarAtEnd() {
        assertEquals("leet", RemovingStarsFromString.removeStars("leetc*"));
    }

    @Test
    void testComplexPattern() {
        assertEquals("", RemovingStarsFromString.removeStars("a*b*c*"));
    }

    @Test
    void testNoRemoval() {
        assertEquals("hello", RemovingStarsFromString.removeStars("hello"));
    }
}
