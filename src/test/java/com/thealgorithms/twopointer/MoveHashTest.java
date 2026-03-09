package com.thealgorithms.twopointer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        assertNull(MoveHash.movehashtoend(null));
    }

    @Test
    void testNoHash() {
        assertEquals("hello", MoveHash.movehashtoend("hello"));
    }

    @Test
    void testAllHash() {
        assertEquals("#####", MoveHash.movehashtoend("#####"));
    }

    @Test
    void testHashAtEnd() {
        assertEquals("hello##", MoveHash.movehashtoend("hello##"));
    }

    @Test
    void testHashAtStart() {
        assertEquals("hello#####", MoveHash.movehashtoend("######hello"));
    }

    @Test
    void testSingleCharacter() {
        assertEquals("#", MoveHash.movehashtoend("#"));
    }

    @Test
    void testSingleCharacterNoHash() {
        assertEquals("a", MoveHash.movehashtoend("a"));
    }

    @Test
    void testSwapFunction() {
        char[] arr = {'a', 'b', 'c'};
        MoveHash.swap(0, 2, arr);
        assertEquals('c', arr[0]);
        assertEquals('a', arr[2]);
    }
}
    }
}