package com.thealgorithms.twopointer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MoveHashTest {

    @Test
    void testMoveHash() {
        assertEquals("hello#####", MoveHash.movehashtoend("h#e#l###l#o"));
    }

    @Test
    void testEmpty() {
        assertEquals("", MoveHash.movehashtoend(""));
    }

    @Test
    void testNull() {
        assertEquals(null, MoveHash.movehashtoend(null));
    }
}