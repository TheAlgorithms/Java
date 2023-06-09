package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HorspoolSearchTest {

    @Test
    void testFindFirstMatch() {
        int index = HorspoolSearch.findFirst("World", "Hello World");
        assertEquals(6, index);
    }

    @Test
    void testFindFirstNotMatch() {
        int index = HorspoolSearch.findFirst("hell", "Hello World");
        assertEquals(-1, index);
    }

    @Test
    void testFindFirstPatternLongerText() {
        int index = HorspoolSearch.findFirst("Hello World!!!", "Hello World");
        assertEquals(-1, index);
    }

    @Test
    void testFindFirstPatternEmpty() {
        int index = HorspoolSearch.findFirst("", "Hello World");
        assertEquals(-1, index);
    }

    @Test
    void testFindFirstTextEmpty() {
        int index = HorspoolSearch.findFirst("Hello", "");
        assertEquals(-1, index);
    }

    @Test
    void testFindFirstPatternAndTextEmpty() {
        int index = HorspoolSearch.findFirst("", "");
        assertEquals(-1, index);
    }

    @Test
    void testFindFirstSpecialCharacter() {
        int index = HorspoolSearch.findFirst("$3**", "Hello $3**$ World");
        assertEquals(6, index);
    }

    @Test
    void testFindFirstInsensitiveMatch() {
        int index = HorspoolSearch.findFirstInsensitive("hello", "Hello World");
        assertEquals(0, index);
    }

    @Test
    void testFindFirstInsensitiveNotMatch() {
        int index = HorspoolSearch.findFirstInsensitive("helo", "Hello World");
        assertEquals(-1, index);
    }

    @Test
    void testGetLastComparisons() {
        HorspoolSearch.findFirst("World", "Hello World");
        int lastSearchNumber = HorspoolSearch.getLastComparisons();
        assertEquals(7, lastSearchNumber);
    }

    @Test
    void testGetLastComparisonsNotMatch() {
        HorspoolSearch.findFirst("Word", "Hello World");
        int lastSearchNumber = HorspoolSearch.getLastComparisons();
        assertEquals(3, lastSearchNumber);
    }

    @Test
    void testFindFirstPatternNull() {
        assertThrows(NullPointerException.class, () -> HorspoolSearch.findFirst(null, "Hello World"));
    }

    @Test
    void testFindFirstTextNull() {
        assertThrows(NullPointerException.class, () -> HorspoolSearch.findFirst("Hello", null));
    }
}