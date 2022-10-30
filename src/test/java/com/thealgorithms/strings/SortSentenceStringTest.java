package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SortSentenceStringTest {

    @Test
    void test_one() {
        assertEquals("This is a sentence", SortSentenceString.sortSentence("is2 sentence4 This1 a3"));
    }

      @Test
    void test_two() {
        assertEquals("Me Myself and I", SortSentenceString.sortSentence("Myself2 Me1 I4 and3"));
    }

}
