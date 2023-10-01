package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReverseWordInStringTest {

    @Test
    public void testCorrectReverseWordsInTheString() {
        assertEquals("blue is Sky", ReverseWordInString.reverseWords("Sky is blue"));
    }
}
