package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BoyerMooreTest {

    @Test
    public void testPatternFound() {
        BoyerMoore bm = new BoyerMoore("ABCDABD");
        String text = "ABC ABCDAB ABCDABCDABDE";
        int index = bm.search(text);
        assertEquals(15, index);
    }

    @Test
    public void testPatternNotFound() {
        BoyerMoore bm = new BoyerMoore("XYZ");
        String text = "ABC ABCDAB ABCDABCDABDE";
        int index = bm.search(text);
        assertEquals(-1, index);
    }

    @Test
    public void testPatternAtBeginning() {
        BoyerMoore bm = new BoyerMoore("ABC");
        String text = "ABCDEF";
        int index = bm.search(text);
        assertEquals(0, index);
    }

    @Test
    public void testPatternAtEnd() {
        BoyerMoore bm = new BoyerMoore("CDE");
        String text = "ABCDEFGCDE";
        int index = bm.search(text);
        assertEquals(2, index); // Primera ocurrencia de "CDE"
    }

    @Test
    public void testEmptyPattern() {
        BoyerMoore bm = new BoyerMoore("");
        String text = "Hello world";
        int index = bm.search(text);
        assertEquals(0, index);
    }

    @Test
    public void testStaticSearchMethod() {
        String text = "ABCDEFGCDE";
        int index = BoyerMoore.staticSearch(text, "CDE");
        assertEquals(2, index); // Primera ocurrencia de "CDE"
    }
}
