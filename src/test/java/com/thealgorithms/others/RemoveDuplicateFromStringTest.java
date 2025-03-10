package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class RemoveDuplicateFromStringTest {

    @Test
    void testEmptyString() {
        assertEquals("", RemoveDuplicateFromString.removeDuplicate(""));
    }

    @Test
    void testNullString() {
        assertNull(RemoveDuplicateFromString.removeDuplicate(null));
    }

    @Test
    void testSingleCharacterString() {
        assertEquals("a", RemoveDuplicateFromString.removeDuplicate("a"));
    }

    @Test
    void testStringWithNoDuplicates() {
        assertEquals("abc", RemoveDuplicateFromString.removeDuplicate("abc"));
    }

    @Test
    void testStringWithDuplicates() {
        assertEquals("abcd", RemoveDuplicateFromString.removeDuplicate("aabbbccccddddd"));
    }

    @Test
    void testStringWithAllSameCharacters() {
        assertEquals("a", RemoveDuplicateFromString.removeDuplicate("aaaaa"));
    }

    @Test
    void testStringWithMixedCase() {
        assertEquals("abAB", RemoveDuplicateFromString.removeDuplicate("aabABAB"));
    }
}
