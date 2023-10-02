package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReverseWordInStringTest {

    @Test
    public void testCorrectReverseWordsInTheString() {
        assertEquals("blue is Sky", ReverseWordsInString.reverseWordsInString("Sky is blue"));
    }

    @Test
    public void testCorrectReverseWordsInTheStringWithWhiteSpace() {
        assertEquals("blue is Sky", ReverseWordsInString.reverseWordsInString("Sky \n is \n \n blue"));
    }

    @Test
    public void testReverseWordsInStringForEmpty() {
        assertEquals("", ReverseWordsInString.reverseWordsInString(""));
    }
}
