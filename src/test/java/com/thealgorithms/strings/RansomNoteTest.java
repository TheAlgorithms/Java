package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RansomNoteTest {

    @Test
    public void testCanConstruct() {
        assertTrue(RansomNote.canConstruct("aa", "aab"));
    }

    @Test
    public void testCannotConstruct() {
        assertFalse(RansomNote.canConstruct("aa", "ab"));
    }

    @Test
    public void testEmptyRansomNote() {
        assertTrue(RansomNote.canConstruct("", "abc"));
    }

    @Test
    public void testEmptyMagazine() {
        assertFalse(RansomNote.canConstruct("a", ""));
    }

    @Test
    public void testExactMatch() {
        assertTrue(RansomNote.canConstruct("abc", "abc"));
    }
}
