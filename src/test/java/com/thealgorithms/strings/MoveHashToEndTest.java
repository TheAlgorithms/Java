package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class MoveHashToEndTest {

    @Test
    void testBasicCase() {
        assertEquals("helllo###", MoveHashToEnd.moveHashToEnd("h#e#l#llo"));
    }

    @Test
    void testNoHash() {
        assertEquals("hello", MoveHashToEnd.moveHashToEnd("hello"));
    }

    @Test
    void testAllHashes() {
        assertEquals("###", MoveHashToEnd.moveHashToEnd("###"));
    }

    @Test
    void testHashAtEnd() {
        assertEquals("hello#", MoveHashToEnd.moveHashToEnd("hello#"));
    }

    @Test
    void testHashAtStart() {
        assertEquals("hello#", MoveHashToEnd.moveHashToEnd("#hello"));
    }

    @Test
    void testEmptyString() {
        assertEquals("", MoveHashToEnd.moveHashToEnd(""));
    }

    @Test
    void testNullInput() {
        assertNull(MoveHashToEnd.moveHashToEnd(null));
    }

    @Test
    void testSingleHash() {
        assertEquals("#", MoveHashToEnd.moveHashToEnd("#"));
    }

    @Test
    void testSingleNonHashChar() {
        assertEquals("a", MoveHashToEnd.moveHashToEnd("a"));
    }
}
